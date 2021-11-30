/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuentapalabras;

/**
 *
 * @author Benavides
 */
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class CuentaPalabras {
    
    //Clase Tokenizer sirve para separar en palabras
    
    public static class TokenizerMapper
        //asigna pares clave-valor de entrada
        extends Mapper<Object, Text, Text, IntWritable>
    {
        //Como mapreduce funciona con params clave-valor, estas clases deben ser serializables por el framework así que se implenta la
        //intefaz writable para facilitar la clasificación que hace el framework
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();
        
        
        //La implementación de Mapper atraves del metodo map procesa una linea a la vez de lo que proporcionamos 
        //a traves del TextInputForm que en este caso en un archivo de texto plano
        
        public void map(Object key, Text value, Context context)
                    throws IOException, InterruptedException
        {
            //Divide la linea en tokens separados por espacios y genera un par de llaves con clave-valor, ejemplo: <<palabra>, 1>
            StringTokenizer itr = new StringTokenizer(value.toString());
            while(itr.hasMoreTokens()){
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }    
    public static class IntSumReducer extends 
            Reducer<Text,IntWritable,Text, IntWritable>
    {
        private IntWritable result = new IntWritable();
        
        //La implementacion de reducer
        //atraves del metodo reduce resume los valores de los recuentos de ocurrencias para cada clave
        public void reduce(Text key, Iterable<IntWritable> values, 
                            Context context) throws IOException, InterruptedException 
        {
            int sum = 0;
            for(IntWritable val : values){
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }//el metodo main especifica las etapas del trabajo o los jobs, como rutas de entrada o salida
    //los tipos de pares clave-valor, los formatos de entrada, etc..
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "cuenta palabras");
    job.setJarByClass(CuentaPalabras.class);
    //las implementaciones del mapper se pasan a trabajar atraves de este metodo
    job.setMapperClass(TokenizerMapper.class);
    //Combinador por el cual la salida de cada mapa pasa atraves de el para la agregación y ordenarse en pares clave-valor 
    job.setCombinerClass(IntSumReducer.class);
    //Pasa las implementaciones del reduce
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    //indice el conjunto de archivos de entrada
    FileInputFormat.addInputPath(job, new Path(args[1]));
    FileOutputFormat.setOutputPath(job, new Path(args[2]));
    //el job de completion se usa para enviar el trabajo y supervisar el progreso
    System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}