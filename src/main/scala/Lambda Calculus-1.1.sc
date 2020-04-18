// 1.  A function in Lambda calculus is a pure function, if,  it has no
//     side effects.  means external OR global variable dont change its output given the same input
//     in short it does not accesses the global variable,
//     if we have to access global variable in a function we can still accomplish this by making state value
//     (global variable) immutable in class or in tuples
//     Referential transparency means for each values of x there is a value of y ie x=>Y.
//     ---------------------------------
//     A pure function characteristic
//       1.  a pure function does not change global state
//       2.  does not change function arguments,  any argument in () is immutable, variable can be changed in { }
//           since variable are immutable in () we dont specify var or val inside ()
//       3.  result depends on arguments
//
//      let there be a function  y=f(x)  where x belong R (giving type). x is an independent variable, y is dependent variable and
//      f is a function name.  this can be written in scala as
//
//      def f(x:Type)={}   where {} is y   means
//      def sqr(x:Int) ={x**2}
//
//      Lamdba calculus has only one indepedent variable.
//      in a pure functions argument cannot be modified. that why we cannot give var or val in function parameters
//      in pure functions parameters neither you can set values or you can get value.  Therefore
//      def sqr(val x:Int) ={x**2} is a syntax error
//
//      in Functional programming all functions are first class first class functions means
//      1.  They can be assigned to a variable
//      2.  they can be passed as argument to a function
//      3.  they can be returned as function.
//
//      in scala def add(a,b) =a+b is a method not function.  its not part of functional programming its part of scala
//      object oriented system.  means add method cannot be assigned to variable. its not a first class function,  while
//      (a,b) => a+b is a function and can be assigned to a variable since its part of functional programming
/////////////////////////////////////////////////////////////////////
// 2.  Higher order functions take other functions as parameters or return a function as a result
//      h(x) = f(g(x))
//
//     they help in writting a better call back functions
//     a function can take arguments and function as parameter. if we pass one function as parameter to another function
//     we need to know which function will execute first.  means we have to maintain an order.  this is called order of
//     function.  if a function takes another function as argument then the function take another function will execute first
//     for all the function being passed into it as argument.  therefore the order of that function is highiest.  thats why
//     we called such functions as higher order functions. with same logic if a function returns a function,  the returning
//     function will not execute before the function its contains.  therefore calling function is higher order function
//     example,  map, filter, reduce.
//
//     let there be a function  z=h(x,y) where y=f(x).   then z=h(x, f(x)) then H function is higer order function
//     because it order of execution comes after the execution of y=f(x).  note h function make be called first but once
//     in execution y=f(x) function execution must complete before h function execution completes. that is why f(x) is called
//     first order function
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 3.  anonymous and Lambda functions
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//     anonymous function is defined as the function which dont have any name.
//
//     for example z=f(x,y) is a pure function here x is input f is the function name and z is output variable
//    if we represent this function as anonymous we represent (x,y) determine z.  in mathemtical terms is written as
//     (x,y) => z  where z=x**2+y.   z part always gives definition of the function
//
//    in scala the definition of anonymous function becomes.
//    (x:Int, y:int) =>{}.   here z is represented as {} which gives definiton of funciton
//
//     var div = (num: Double, den: Double) => { num / den}.  div is a lambda function (since assigned to variable)
//     (num: Double, den: Double) => { num / den} is an anonymous function.
//
//     in exact terms the definition is:
//     (variableName: inputType) => {}:outputType   where {} is how function is defined
//
//     an anonymous function is being created by Function object line Function[Int, Int, Int] which is represented by
//     (Int,Int)=>Int so if this anonymous function is assigned variable its type will be
//     val x:(Int,Int)=> Int is same as val x:Function[Int,Int,Int].
//
//     if an anonymous function is assigned to a variable its called a lambda function
//
//     val varName:(Type,Type)=>Type =(x:Type)=>{}:Type
//
//      in Functional programming all functions are first class functions while method are not
//      1.  They can be assigned to a variable
//      2.  they can be passed as argument to a function
//      3.  they can be returned as function.
//
//     Note:  that def add(a,b) = a+b is a method and is not part of functional programming but part of object orient system.
//     therefore its not a first order function.  therefore it cannot be assigned to a variable.  however we can convert
//     a method into function and assigned it to a variable.  this is being done by _ Eta function.
//     val fadd = add _  now fadd is a first order function.
//     in short y=f(x) is method in scala and x=>y is function. they both are same thing in mathematics so y=f(x) can be
//     converted into x=> y
//
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 4. Currying Functions and Partially Applied Functions (all functions are currying functions)
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//     in lambda calculus a function can have only take one argument ,  to use multiple argument we use currying
//     let z=f(x,y) is a function and f(x,y)=h(x)(y) implies z=h(x)(y) which means (x)(y)=>z where x=>y
//     so it can be represented as  x=>y=>z.   z=h(x)(y) is a method and x=>y=>z a function in pure form
//
//     currying also being used for creating partially applied functions g(x)(_) or g(x) with (y) missing
//
//     z = f(x,y) =x + y**2 is a method. a currying function translate into f(x,y) =h(x)(y) then
//     h(2)(y)= 2 + y**2 is called partially applied function.  while f(2,3) =2+3**2 is fully applied pure function
//     a partially applied function gets its value from implicits automatically
//     a function to give result all its values must be applied. so partially applied function is also used to delay
//     the execution.
//
//     z =h(2)(_)=f(2,3) means h(2)(_)=2 + _**2 and h(2)(3) =2+3**2
//
//     z=f(x,y) is a function  and f(x,y)=h(x)(y) implies z=h(x)(y) which means (x)(y)=>z where x=>y
//     so it can be represented as  x=>y=>z.   z=h(x)(y) is a method and x=>y=>z a function form
//
//     so scala curry method is defined as    f(x:Type)(y:Type)={}        means  def f(x:Int)(y:Int)={x+y}
//     a anonymous function is defined as     (x:Type)=>(y:Type)=>{}:Type  means (x:Int)=>(y:Int)=>{x+y}
//
//     Note:  _ converts a partially applied method to a partially applied function.  means
//     val x = f(2) _  is a valid expression
//     f(2) _ is a currying partially applied method which is being converted into partially applied
//     currying function by _ eta expression
//     in case of currying function.  we can assign it variable.  as currying functions are first class functions.
//     val x = f(2)   is a valid expression as f(2) is a functions and can be assigned to a variable
//     ////////////////////////////////////////////////
//      USE OF Curry And Partially Applied Functions
//     ////////////////////////////////////////////////
//     in mathematics all function are expression so they can be assigned to a variable.  in mathematics  we can create
//     new function or specialized functions using generalized function.  the currying function converts a generalized
//     function into specialized function.
//
//      The following two are the Generalized solution of division and addition
//
//      val div =(a:Double)=>(b:Double) => a/b
//      def add(a:Int)(b:int) = a+b
//
//      in mathematics general function creates concrete functions add is general but
//      increment is its specialized form. This is the use of currying functions
//      _ converts y=f(x) to x=>y which is a mathematical function and can be assigned to variable
//
//      val incOne = add(1) _
//
//      in same way,  the division with one parameter =1 become inverse function.
//
//      val inv = div(1.0)
//
//      Functions can be called as
//      val tenPlusOne = incOne(10)
//      val invTen = inv(10)
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 5.     Partially Applied Functions and methods
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//     in same way Partially applied function as defined
//     let f(x,y) is a function on f:R->R
//     f(x+y) =f(x)+f(y) then partially applied function is defined as  f(* + y) = f(*)+f(y)
//
////////////////
//  Methods
////////////////
//
//    def add(a:Int,b:Int) = a+b
//
//    a method is not a pure function so cannot be assigned to variable
//    _ converts the method to function which can be assigned to variable _ = (_,_)
//
//    val inc =add _                //
//    val inc:Int=>Int = add(1,_)   // _ is for place holder ,  Int=>Int convert method to function its an explict form
//    val inc =add(1, _:Int)        // _ is place hold and conversion with :Int indicating the conversion type implicit form
//
//    def add(a:Int)(b:Int)=a+b
//    val inc =add(1) _            // here second function return type known so _ converts method to function
//
////////////////
//  Functions
///////////////
//
//   val add= (a:Int, b:Int)=>a+b
//   val inc:Int=>Int = add(1,_)    // here _ is a place holder of argument
//
//  val add= (a:Int)=>(b:Int)=>a+b
//   val inc = add(1)              // function is assigned to variable to _ not needed for conversion or as place holder
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 6.  Partial Function
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  H(t)a,b  is a Partial function with case environment a and b.  if a=5 and b=8 then partial case function H(t)58 is selected
//  when a=9 and b=1 then partial case function H(t)91 is select and so on. in short, partial function is actually created a
//  conditional programming environments.  they are used for if and else functionality
//
//              { H(t)58  where a=5 and b=8 }
//    H(t)a,b  ={ H(t)91  where a=9 and b=1 }
//              { H(t)33  where a=3 and b=3 }
//
//    H(t)58 is a case function because its selection is based on case environments. therefore a partial function
//    contains multiple case functions.  H(t)a,b is a function,  in same way, H(t)58,H(t)91 and H(t)33 are also functions
//
//     partial functions are used as a tool to perform different tasks.  like pattern matching and if else logic
//     a partial function is defined as,  f:R->R
//             { x**2  if x<0   }
//      f(x) = { x     if x=0   }
//             { x*x   if x >0  }
//
//     for different values of x different functions are selected.  this is like pattern matching in scala and
//     the case statement.
//
//    a function can only contain one pure function,  to add multiple partial function we add case keyword and logically
//    // if condition
//    def parFun(x:Int ) = x match { case x:Int if x<0 => x*2
//                                   case x:Int if x==0 => x
//                                   case x:Int if x>0 => x*x
//                                  }
//  case class PhoneExt(name: String, ext: Int)
//  val extensions = List(PhoneExt("steve", 100), PhoneExt("robey", 200))
//
//  unpacking method 1
//  extensions.filter { (I:PhoneExt) => I.ext < 200}
//
// case just tell the compiler that function has multiple partial functions based on environment case
// if we remove case we can only give one function which will be a pure function. if you have multiple
// matching based on type and environment case.   then you can use case keyword
//
//  unpacking method 2
//  extensions.filter { case PhoneExt(name, extension) => extension < 200
//                      case _ => false
//                      }
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 7. pack and unpack a values.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  Packing a value in type
////////////////////////////
//
//  two int are being packed in tuple.  a tuple is a case class so it does not need new operator to create an object
//  case class represents types in scala
//
//  val t = (2,3)
//
//  // define a custom type
//  case class PhoneExt(name: String, ext: Int)
//
//  val phone = PhoneExt("Amir",0971)  // apply method in companion class in case a class create object by new operator
//
///////////////////////////////
//  Unpacking a value of type
///////////////////////////////
//
//  A common definition of function between two sets (or between two classes, when working in GBN) is based on the notion
//  of "ordered pair". An ordered pair is some set-theoretic construction, denoted "(a,b)" where a and b are sets,
//  with the property that (a,b)=(c,d) if and only if a=c and b=d.
//
//    val (a,_) =(2,3)
//    val (a,b) =(2,3)  // means a=2 and b=3 as per theorem  where (a,b) belong to tuple
//
//   case class PhoneExt(name: String, ext: Int)
//
//   here name ="Amir" and extension =0971 and type PhoneExt
//   val PhoneExt(name, extension) = PhoneExt("Amir",0971)
//
//   PhoneExt is with (name,extension) to show the type. in case of tuple this type is implicit
//
//  unapply method in case class unpacks the object
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
//   a value of a function is bounded if its part of set and value is free if its not part of the set
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
//     def addConstant(v: Int): Int = v + k
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

val divide0 = (num: Double, den: Double) => { num / den }
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

///////////////////////////////////////////////////////
// curry function syntax as per mathematical definition
//////////////////////////////////////////////////////

//     z=f(x,y) is a function  and f(x,y)=h(x)(y) implies z=h(x)(y) which means (x)(y)=>z where x=>y
//     so it can be represented as  x=>y=>z.   z=h(x)(y) is a method and x=>y=>z a function form
//
//     so scala curry method is defined as    f(x:Type)(y:Type)={}        means
       def f(x:Int)(y:Int)={x+y}
//     a anonymous function is defined as     (x:Type)=>(y:Type)=>{}:Type  means
       (x:Int)=>(y:Int)=>{x+y}

//here conversion from method to function occur due to variable type given and it happens without Eta function
// if Int => (Int=>Int) is not given then conversion will not happen

def add(a: Int)(b: Int):Int = a + b
val addFiveDf:Int => (Int=>Int)=add



///////////////////////////////////////////////////////////////////////////////////////////
// Since scala is a functional programming language it support recursion more and loop less
///////////////////////////////////////////////////////////////////////////////////////
// a recursive function is a partical function which is cyclic
def factorial(n: Int): Int = if (n == 0) 1 else n * factorial(n-1)

// the recursion work by add the calucation elements building a function untill all values are available.
//  once all values are available the value is calculated,  here is how it works

factorial(5)
//  if (5 == 0) 1 else 5 * factorial(5-1)
//   5 * factorial(5-1)
//     5 * factorial(4)
//       5 * (4 * factorial(3))
//         5 * (4 * (3 * factorial(2)))
//           5 * (4 * (3 * (2 * factorial(1))))
//             5 * (4 * (3 * (2 * (1 * factorial(0))))
//               5 * (4 * (3 * (2 * (1 * 1))))
//                 120
//
// in function programming there is no for loop ,  the looping is being done by recursion.   in same way,  FP
//  dont have if else.  conditional logic is being processed by partial function.  which is a like a case statement.
//
//
