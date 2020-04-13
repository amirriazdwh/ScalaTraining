// 1.  A function in Lambda calculus is a pure function, if,  it has no
//     1.a  side effects.  means external OR global variable dont change its output given the same input
//          in short it does not accesses the global variable,
//          if we have to access global variable in a function we can still accomplish this by making state value
//          (global variable) immutable in class or in tuples
//     1.b  Referential transparency ( for a given values of X we get a value Y) means argument determine result of a pure
//          function.  ie x->Y. in mathematical terms such functions are called idempotents
//     ---------------------------------
//     A pure function characteristic
//       1.  a pure function does not change global state
//       2.  does not change function arguments
//       3.  result depends on arugments
/////////////////////////////////////////////////////////////////////
// 2.  Higher order functions take other functions as parameters or return a function as a result
//     they help in writting a better call back functions
//     a function can take arguments and function as parameter. if we pass one function as parameter to another function
//     we need to know which function will execute first.  means we have to maintain an order.  this is called order of
//     function.  if a function takes another function as argument then the function take another function will execute first
//     for all the function being passed into it as argument.  therefore the order of that function is highiest.  thats why
//     we called such functions as higher order functions. with same logic if a function returns a function,  the returning
//     function will not execute before the function its contains.  therefore calling function is higher order function
//     example,  map, filter, reduce.
//////////////////////////////////////////////////////////////////////////
// 3.  anonymous and Lambda functions
//     (num: Double, den: Double) => { num / den} is defined as the function which dont have any name
//     f(2)=2**2+2  is a pure function but can be written as (2**2+2 )(2) here 2**2+2 anonymous function
//     or lambda function.  so Lambda = 2**2+2.  Lambda is a name given to a unname function.  the name can be anything
//     so a Lambda function is any function which has no name and can be given a name
//     val div = (num: Double, den: Double) => { num / den}.  div is a lambda function and
//     (num: Double, den: Double) => { num / den} is an anonymous function
//////////////////////////////////////////////////////////////////////////////
//     used as a tool to perform different placces.  like pattern matching or partial functions or map or reduce calculations
// 4.  Higher order functions take other functions as parameters or return a function as a result
//     they help in writting a better call back functions
// 5.  in Lambda calculus function cannot be evaluated until its bind to a certain
//     variable.  so if a function has two variable f(x, y) we can delay its execution
//     till end by using _,  the phenonmon is called partically applied functions
// 6.  in lambda calculus a function can have only one variable,  to use multiple variable we use currying g(x)(y)
//     currying also being used for creating partically applied functions g(x)(_) or g(x) with (y) missing
// 7.  partial function to pack and unpack a values.  suppose a function is on value 1..10,  partial will be from 1..5
//////////////////////////////////////////////////////////////////////////////
//    as in lambda calculus they are defined as
//    lambda:x => express x
//    where lambda x is input and right side of => is expression
//////////////////////////////////////////////////////////////////////////////
// this is not a pure function as mySum(0) not= mySum(4)
////////////////////////////////////////////////////////////


def mySum(n:Int):Unit ={
  if (n==0) println("Got Zero")
  else
    n+1
}
// this is also not a pure function as it has side effects.   it changing the value of a total variable
var total =10
def addTototal01(x:Int) ={
  total+=x
  total
}

def addTototal02(x:Int) ={
  total+=x
}
// in same way any function return Unit is not a pure function. means a function which does not return any value
/////////////////////////////////////////////////////////////////////////////////
/// however the same function is pure function because totalM is immutable
/////////////////////////////////////////////////////////////////////////////////
val totalM =10
def addTototalM(x:Int)={
  x+totalM
}