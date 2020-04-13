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
//     anonymous function is defined as the function which dont have any name.
//
//     for example y=f(x) is a pure function here x is input f is the function name and y is output variable
//    if we represent this function as anonymous we represent x determine y.  in mathemtical terms is written as
//     x => y
//
//     val div = (num: Double, den: Double) => { num / den}.  div is a lambda function name and
//     (num: Double, den: Double) => { num / den} is an anonymous function
//
//     in exact terms the definition is:
//
//     variableName: inputType => {}:outputType
//
//     anonymous functions are also called apply functions as they have a apply(input):output method represented by
//     notations as input:type => output:type
//////////////////////////////////////////////////////////////////////////////
// 4.  in lambda calculus a function can have only one variable,  to use multiple variable we use currying g(x)(y)
//     currying also being used for creating partically applied functions g(x)(_) or g(x) with (y) missing
//
//     f(x,y) =x + y**2 is a function. a currying function translate into f(x,y) =h(x)(y) then
//     h(2)(y)= 2 + y**2 is called partially applied function.  while f(2,3) =2+3**2 is fully applied function or pure function
//     a partial function gets its value from implicits automatically
//     a function to give result all its values must be applied. so partically applied function is also used to delay
//     the execution.
////////////////////////////////////////////////////////////////////////////////////////
// 5.  in Lambda calculus function cannot be evaluated until its bind to a certain
//     variable.  so if a function has two variable f(x, y) we can delay its execution
//     till end by using _,  the phenonmon is called partically applied functions
///////////////////////////////////////////////////////////////////////////////////////
// 6   used as a tool to perform different tasks.  like pattern matching and if else logic
//     a partial function is defined as,  f:R->R
//             { x**2  if x<0   }
//      f(x) = { x     if x=0   }
//             { x*x   if x >0  }
//
//     for different values of x different functions are selected.  this is like pattern matching in scala and
//     the case statement. where x match { case x if x=0 => x**3}
//
// 7.  partial function to pack and unpack a values.
//    (a,_) =tuple(2,3)
//////////////////////////////////////////////////////////////////////////////
//8.  The term closure comes from the fact that a piece of code (block, function) can have free variables that are
//   closed (i.e. bound to a value) by the environment in which the block of code is defined.
//
//   Take for example the Scala function definition:
//
//   def addConstant(v: Int): Int = v + k
//   In the function body there are two names (variables) v and k indicating two integer values. The name v is bound because
//   it is declared as an argument of the function addConstant (by looking at the function declaration we know that v will
//   be assigned a value when the function is invoked). The name k is free wrt the function addConstant because the function
//   contains no clue as to what value k is bound to (and how).
//
//   In order to evaluate a call like:
//
//    val n = addConstant(10)
//    we have to assign k a value, which can only happen if the name k is defined in the context in which addConstant is defined.
//   For example:
//
//   def increaseAll(values: List[Int]): List[Int] =
//   {
//     val k = 2
//
//     def addConstant(v: Int): Int = v + k
//
//     values.map(addConstant)
//   }
//
//////////////////////////////////////////////////////////////////////////////
//    as in lambda calculus they are defined as
//    lambda:x => express x
//    where lambda x is input and right side of => is expression
//////////////////////////////////////////////////////////////////////////////
// this is not a pure function as mySum(0) not= mySum(4)
////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  SCALA LANGUAGE LEARNING
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// var variableName: Type
// why we use :  in english : means second explains first.  here Type explains what is variableName
//
/////////////////////////////////////////////////////
// function definition grammar in the light of scala
////////////////////////////////////////////////////
// in a pure functions argument cannot be modified. that why we cannot give var or val in function parameters
// in pure functions parameters neither you can set values or you can get value
// in case of method def define an immutable variable to which function is assigned. in case of Lambda function val is
//  used to defined immutable variable to assign aynonmous function. that why we have = sign
//  function return type comes with def name: Int  just like val name:Int
//////////////////////////////////////////////////////////////////////////

// function with side effects
var g:Int =10

def sumg(i:Int)={
  g=i+g
  g
}
var a = sumg(2)
var b = sumg(2)

// output will not be same same input.  so it not a function by methatical definition.   refer to ball diagram
// for pure function was value of x should map on the same value to y  y=f(x)

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
///////////////////////////////////////////////////////////////////////
// an anonymous function has no name as variable while it has return type and body represented by {} and body is
// being assigned to anonymous variable by = as it case of method.   means  name:type = {}.  since in anonymous no name
// present it become type={}
// variable type is represented by inputType => outputType. this is because function are actually objects which contain apply
//  method with input and output
// Int=>Int=>Int means function of order 2.  means currying function.
// (Int, Int) =>Int  means an anonymous function as parameter definition of a higher order function
// a Lambda function has a name so its defined as
//  var variableName:Type ={}.  where type is like function. that is Int=>Int
////////////////////////////////////////////////////////////////////////

// anonymous function assigned to a variable.  here the anonymous function is (num: Double, den: Double) =>{num / den}
// (num: Double, den: Double) is input type
val divide0 = (num: Double, den: Double) => ={
  num / den
}
println(divide0(9,4))
println(divide0.apply(9,4))
// in same way any function return Unit is not a pure function. means a function which does not return any value
/////////////////////////////////////////////////////////////////////////////////
/// however the same function is pure function because totalM is immutable
/////////////////////////////////////////////////////////////////////////////////
val totalM =10
def addTototalM(x:Int)={
  x+totalM
}