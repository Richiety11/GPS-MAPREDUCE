//const arr = [5, 1, 3, 2, 6];

/*Map

double = [10, 2, 6, 4, 12]
triple = [15, 3, 9, 6, 18]
binary = ["101", "1", "11", "10", "110"]

function double(x){
    return x * 2;
}

function triple(x){
    return x * 3;
}

function binary(x){
    return x.toString(2);
}

const output = arr.map(binary);

const output = arr.map((x) => {
    return x.toString(2);
});  */


/*Filter

//Filter odd values
function isOdd(x) {
    return x % 2;
}

//Filter even values
function isEven(x) {
    return x % 2 === 0;
}

//Filter > values
function greaterThan(x) {
    return x > 4;
}

//Filter < values
function lessThan(x) {
    return x < 3;
}

const output = arr.filter(lessThan);

console.log(output); */

/* Reduce

/*Sum

function findSum(arr) {
    let sum = 0;
    for (let i = 0; i < arr.length; i++) {
        sum = sum + arr[i];
    }
    return sum;
}

console.log(findSum(arr));

const output = arr.reduce(function(acc, curr) {
    acc = acc + curr;
    return acc;
}, 0);

console.log(output);

/*Max

function findMax(arr) {
    let max = 0;
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    return max;
}

console.log(findMax(arr));

const output = arr.reduce(function (max, curr){
    if (curr > max) {
        max = curr;
    }
    return max;
}, 0);

console.log(output); 
*/


const users = [
    { firstName: "Ricardo", lastName: "Benavides", age: 24 },
    { firstName: "Kokun", lastName: "PelosLokos", age: 43 },
    { firstName: "Pancho", lastName: "Villa", age: 45 },
    { firstName: "Carmen", lastName: "Salinas", age: 82 },
    { firstName: "Doña", lastName: "Meche", age: 24 },
];


//Lista de nombres completa
// ["Ricardo Benavides", "Kokun PelosLokos" ....]

//const output = users.map(x => x.firstName + x.lastName);

//console.log(output); */


/*// acc = { 24 : 1, 43 : 1, 45 : 1, 82 : 2 }

const output = users.reduce(function (acc, curr){

    if(acc[curr.age]){
        acc[curr.age] = ++ acc[curr.age];
    }
    else {
        acc[curr.age] = 1;
    }
    return acc;
}, {});

console.log(output);*/

//Resultado esperado ["Ricardo", "Benavides"]
/*const output = 

users.filter((x) => x.age < 30)
.map((x) => x.firstName + " " + x.lastName);

console.log(output); */

const output = users.reduce((acc, current) => {

    if(current.age < 30){
        acc.push(current.firstName + " " + current.lastName);
    }
    
    return acc;

  }, []);

  console.log(output);