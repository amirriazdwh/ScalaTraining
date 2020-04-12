// 1.  A function in Lambda calculus is a pure function, if,  it has no
//     1.a  side effects.  means external variable dont change its output given the same input,
//          (we accomplish this by making state value immutable in class or in tuples)
//     1.b  Referential transparency ( for a given values of X we get a value Y)
//     ///////////////////////////////////////////////////////////////////
//     A pure function is given one or more input parameters.Its result is based solely off of those parameters and its algorithm.
//     The algorithm will not be based on any hidden state in the class or object it’s contained in.
//     It won’t mutate the parameters it’s given. It won’t mutate the state of its class or object.
//     It doesn’t perform any I/O operations, such as reading from disk, writing to disk, prompting for input, or reading input
//      ///////////////////////////////////////////////////////////////////
// 2a. anonymous function and variables.  see "Scala Tuples - indepth.sc"
// 3.  anonymous (num: Double, den: Double) => { num / den} is defined as the function which dont have any name.
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
/// however the same function is pure function because total is immutable
/////////////////////////////////////////////////////////////////////////////////
val totalM =10
def addTototalM(x:Int)={
  x+totalM
}