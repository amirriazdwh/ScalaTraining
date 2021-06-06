// Note:  method is defined as  def add(a:Int , b:Int):Int={a+b}
//        this method return a function def add(a:Int , b:Int)=(a:Int,b:Int)=>{a+b}
//        curried methods def add(a:Int)(b:Int) ={a+b} where (a:Int) and (b:Int) are partical parts of this function
//        when they are called as add(4) or add(4)(_) or add(4,_) they are called as partically applied function.
//        all function containing multiple parameters are curried function since functional programming allows only one parameter
//        so add(4,5) is applied as add(4)(5)
// Note:  def add(a: Int)(b: Int):Int = a + b is currying method. define as Int=>Int=>Int, means it return as function
//        val addcur:Int=>Int =add(4) here add(4) return function Int=>Int or val addcon =add _ will return Int =>Int=>Int

// Note:  Function is defined as     val divide = (num: Double, den: Double) =>{ num / den}
//        curried formation          val divide = (num:Double)=>(den:Double) => {num/den}
//        anymous function           (num:Double)=>(den:Double) => {num/den}

// Note:  val div =divide.curried convert a function div(a,b) into curry function div(a)(b) who assigned variable type Int=>Int=>Int
// Note   => is apply method while left hand side is input parameters and right hand side is output values
//        each anymmous function is a function object defined as below, compilter sugar coats the syntax as above
//        which is val divide = new Function2[Double,Double,Double] { def apply(num:Double, den:Double):Double ={ num / den }  }
// Note:  A function is an object with Trait Function1[Int,Int] and Function1 means it take one input and has one output
// Note:   a function call is convert by compiler as divide.apply(9,4) but we write it as divide(9,4)
// Note:  A method can be convert to function by _  like val addfnc =add _  here add is a method, its return a function class
//        _ call method add from apply method of function2 object like, the new function types are derived from method definition
//       new Function2[Double,Double,Double] { def apply(num:Double, den:Double):Double =add(num,den)  }
// Note:  A difference between _ and (_),  _ return a function by looking at the type definition of method, while (x,_)
//        infer its type from the high order function to which its been passed,  since its a lambda function
//         same applied to currying functions (_)(_).   we must specify type explicity in case of   var x:int=>int=add since
//         add is a method.  for example,  val x:(Int,Int)=>Int = _+_  _ tells parameter type derived from x
//        val x:Int=>Int = add(10,_) if you dont specify x:Int=>Int the function will not compile, note add(_,_) is equal to add _
// Note:  if a method define as def mth(f:Int=>Int)={ f(x)} and second as def sum(x)={} if we pass sum to fnc its convert to function
// e.g   def something(x:Int) = { println("calling something") ;return x }  // takes Int=>Int
//       def callByName(x:Int => Int) = { println("x1=" + x); println("x2=" + x) }
//       callByName(something(786))  method "something" has been implcity converted to function and called as x inside callbyname body
// Note:  def printAll(strings: String*) { strings.foreach(println)} /Use _* to adapt a sequence As shown in the following example,
//         you can use Scala’s _* operator to adapt a sequence(Array, List, Seq, Vector, etc.) so it can be used as an argument
//         for a varargs field: a sequence of strings, called by printAll(fruits: _*) where val fruits = List("apple", "banana", "cherry")
//         here note that _ says derive the type from higher order function to which its being passed and * says its a sequence
//         so types derived from sequence can be passed. which are Array, List, vector

// Note:  new PartialFunction[Int, Option[String]] { def isDefinedAt(d: Int) = d == 1 def apply(v: Int) = Some("one")}
// Note:  above PF is written as  case (v:Int) if v==1 => Some("one")
// Note:  A partical function is a subtype of function and can be assigned to function variable
// Note:   AU constructor is defined as,  def this(crustType: String) {this(Pizza.DEFAULT_CRUST_SIZE, crustType)}

  val divide0 = (num: Double, den: Double) => { num / den }
  println(divide0(9,4))
  println(divide0.apply(9,4))

// the compiler interprete it as divide.apply(9,4)
// (Double,Double)=>Double tells us about the apply function since a function is an object containing apply function
// , its a variable function takes two Double parameter
// and return on double values. this type matches with right hand side which is
// (num:Double, Den:Double) => num/den  note => means apply method
// in case input is a function,  you can define function parameter as
// f:(Double,Doube)=>Double here => Double shows the return type of {}
// if a function is Function0 its been accessed by Divide0
//  if a function is Function1 to Function22 as below.  its been refrenced by
//  Divide0(2,4).  once we use () with variable name. the function is access
//  if function contains  variablename ->Function2 ->Function2
//  then object will be access by varname ()()

  val divide1:(Double,Double) => Double = (num: Double, den: Double) =>{ num / den }:Double
  println (divide1(9,4))

// function parameters are passed by datatype and by position
//  this is nearly same like as in PL/SQL
// since (_) is used the type will be infered from the assignment variable or from higher order function to which its passed

  val f2:(Int,Int)=>String = "The first int is " + (_) + " and the second int is " + (_)
  val f3 = "The first int is " + (_:Int) + " and the second int is " + (_:Int)

// returns "The first int is 1 and the second int is 2"
// here position of 1 is 1 and type is Int so its been assigned to first and 2
// is assigned to second (_:Int)

  f2(1, 2)

// here divide11 is being assigned  a partical function coming from case. there is not function here
// its apply method contains (num, den) of input type (double, double)

  val divide11:(Double,Double) =>Double = { case (num, den)=> num / den }
  val divide1111:Function2[Double,Double,Double] = { case (num, den) => num / den }
  println(divide1111(2,4))

// here we have assinged a function to divide111 and partical function to
// function,  here a partical function is being assigned to a function based on condition satsified

  val divide111:(Double,Double)=>Option[Double]= {
  // a case statement is like an if statement in Functional programming since functional programing is based on
  // calculus where element has type with condition where a function is defined as
    // f(x) ={ div(x) for all value of v belong to tuple and divisor!=0  }

  case (num, den) if den!=0 => Some(num / den)
  }

// cannot put more that one case statement as case statement is used for unpacking not for pattern matching
//case(_, den) if den==0 => None
  println(divide111(2,0))

// here the partical function is being assigned based on matching x values

val g:PartialFunction[Int,Int]= (x: Int) => {x match { case 2 => 3 }}
val g11:Function1[Int, Int]= (x: Int) => {val x1=x+1; x1 match { case 2 => 3 }}
val g12:Int=>Int = (x: Int) => {val x1=x+1; x1 match { case 2 => 3 }}
val g13 = (x: Int) => {val x1=x+1; x1 match { case 2 => 3 }}
def g14 = (x: Int) => {val x1=x+1; x1 match { case 2 => 3 }}

println (divide11(9,4))
//  in case we want a different values,  we have to convert
val divide2:(Double,Double) =>Int = (num: Double, den: Double) =>{
  val fnc = num / den
  fnc.toInt
}
println (divide2(9,4))

// compiler translate the above functions to this.
//
val divide3 = new Function2[Double,Double,Double] {
  def apply(num:Double, den:Double):Double ={
    num / den
  }
}

//sc.map(line=>line.split())
val divide4:(Double, Double)=>Double = new Function2[Double,Double,Double] {
  def apply(num:Double, den:Double):Double ={
    num / den
  }
}

/******************************************
*  function declaration different ways
* *****************************************/
// in case function parameters are not defined. their
// parameter type is determined by Int=>Int.
val m5_4 = {n=> n*5}:Int=>Int
val m5_3_3: Function1[Int,Int] = n => n * 5
val m5_3: Int => Int = n => n * 5
val m5_5: Int => Int = _ * 5
val m5_1: Int => Int = { (n: Int) => n * 5 }
val m5_2: Int => Int = n => n * 5
//val m5_2_1 = n => n * 5  there is not parameter type given
val m5_2_2 = (n: Int) => { n * 5 } : Int
val m5_6 = (n: Int) => {
  val res = n * 5: Int
  res
}

/****************************************
*  currying functions
  *   in lambda calculus a function can have only one variable.
  *   to use multiple variable we use currying function.  we apply arguemnts one by one
 *   in lambda calculs function are not completed if there arguments are missing. once all arguments are defined function executes
****************************************/
// value function can be defined as curry functions
// => says, function takes a double parameter and return a function parameter
// this is a function of function.  means two function1 objects were create
// first function object is being created and then second,  the refrence of
// first object is assigned to second,  the second object hold the code
// to execute that code you have to refrence the object two times
// for example dividecurr(4) will return a function. which is assigned to a variable halfOfCurr3
// and when halfOfCurr3(2) is refrenced the function will be executed

val dividecurr0 =(num: Double) => (den:Double)=>{ num / den }

println("****************************")
println(dividecurr0(2)(4))
println("****************************")

//  value function can be converted into curry function

val divideCurr:Double => Double => Double= divide0.curried

// *** val divide0 = (num: Double, den: Double) =>num / den ***
// adding _ adds another function object to halfOfCurr0 and it
// becomes () => Double => (Double => Double)
// with halfOfCurr0 _ is not needed since it create apply function inside apply

val halfOfCurr0= divideCurr _

// the right code is,  it just assign the object to the variable without _
// this is because divideCurr is already an anyonmous function
//  in case of def we must add _ to convert method to aynonmous function

val halfOfCurr5= divideCurr

// first object is refrenced so from  Double => (Double => Double)
// it become  Double => Double

val curr =halfOfCurr5(2)

// final function object is refrence with parameter and we get result

println(curr(4))

// divideCurr: Double => (Double => Double)
// once first function applied the second function returned
// double => double

/****************************************
*  partically applied function are function in which first  we decomposed the function and
*  then on part of function has been applied and other is not the function which is
  *  not applied is given by _
***************************************/
  // here val divideCurr:Double => Double => Double= divide0.curried is decompose into two parts
  // the only first part is applied.
  // as explain in start _ means None. function takes arguments by type and position
  // if it finds certain parameter e.g 4 and _ , its been applied as nothing to poistion 1
  /// 4 to position 2.  on furction where nothing is applied,  its object is returned to
  //  variable.  in others works its function type is return which is Double => Double

val halfOfCurr1:(Double)=>Double = divideCurr(_)(4)

// divideCurr(2)(_) = divideCurr(2)
// this is a difference between (_) and _
// (_) return on function part containing () while _ returned the whole

val halfOfCurr2:(Double)=>Double = divideCurr(2)(_)
val halfOfCurr3:Double=>Double = divideCurr(2)

// here function1[Int,Int] applied therefore second
// function1[Int,Int] returned

val halfOfCurr4 = dividecurr0(2)
//
// variable type divideCurr return types define halfOfCurr5 type
val halfOf1: (Double) => Double = divide0(_, 2)
val halfOf2: (Double) => Double = divide3(_, 2)

// The difference between them is, that a val is executed when it is defined whereas
// a lazy val is executed when it is accessed the first time.

val x = { println("x"); 15 }
lazy val y = { println("y"); 13 }

//halfOf(20)
halfOf2(20)

/***********************************
  *  scala methods
  **********************************/
def add3(a:Int, b:Int) =a+b

// (_,_) is equivalent of _

val testadd = add3(_,_)

// is same as _ convert method into function

val testadd01 =add3 _

// without the variable type it will give error
val comadd01:Int=>Int =add3(3,_)
// following function is add4 - function object - function object
//  (a:Int , b:int) define a function object Function2
// def add4 (a:Int, b:Int) says create a variable and
// variable should have function2[Int, Int],  this means (Int, Int)=>Int is just a type like double and Int

def add55:(Int, Int )=>Int={case (a,b)=>a+b}
def add5 = new Function2[Int, Int, Int] {
  def apply(a:Int, b:Int): Int ={
    a+b
  }
}
def div6(a:Double, b:Double)=divide0(a, b)
// function which return a function
def add0(a:Int)=(b: Int) => a + b
def add1(a:Int)(b:Int)= a+b

//println(add4(4,2))
println(add55(4,2))
println(add5(4,2))
println(div6(4,2))
//Let’s make two aptly-named functions:
def f(s: String) = "f(" + s + ")"
def g(s: String) = "g(" + s + ")"

val assignF = f(_)
assignF("Hello world")
// compose needs a function since compose, andThen and tostring
// are part of Function0 to Function22. Def does not have these
// functions.  so we use _ to convert f into function, this is the
// reason (_) does not work here.
// compose makes a new function that composes other functions f(g(x))
val fComposeG = f _  compose g _

fComposeG("yay")

//andThen is like compose, but calls the first function
//and then the second, g(f(x))

val fAndThenG = f _  andThen g _
fAndThenG("yay")

// how curried function and partically applied function are being used.
def add(a: Int)(b: Int):Int = a + b
val onePlusFive = add(1)(5) // 6
// _ means get type from parent here parent type is a function
// _ converts a method into aynonmous function
val addFour1 = add(4) _
// used in case of method overloading
val addFour2 = add(4) _:Int=>Int // (Int => Int) is defined as new Function1[Int,Int]
val addFive3 = add(4)(_:Int)
val addFive0 = add _
// this will not work for methods def addNew =add(4)
val addFiveDf:Int => (Int=>Int)=add
println(addFive3(1))
println(addFiveDf(1))

val twoPlusFour = addFour1(2) // 6
assert(onePlusFive == twoPlusFour) // true

// map takes function with one parameter only.  you can provide one value
// then return a function note the _ after function always return function
// this is called partically applying currying fuction
List(1,2,3,4).map(addFour1)
List(1,2,3,4).map(add(4) _)
List(1,2,3,4).reduce(_+_)

//List((1,2),(3,4),(5,6),(7,8)).map()
// this is awesome
for(i<- 1 to 2) yield List(1,2,3,4).map(add(i)_)

// yield is a generator which pipes they values of i to another expression. which is being access in expression by i
// [x for x in rang(1,10)] in python here value of x is yield into another x expression
for(i<- 1 to 2) yield { List(1,2,3,4).map(add(i)_) }

def addTest1(a: Int)(b: Int) = a + b
// how currying function works
def addTest2(x:Int) = (y:Int) => x + y
// how curry function work.  the above addTest1 function is like addTest3
// the x is passed as cloure.  since closure is passed a value the x+y become 1+y
//  when the y parameter is provided. the function executes.
// scala does not complain errors when parameters are incomplete
def addTest3(x:Int)=new Function1[Int,Int] {
  def apply(y:Int):Int ={
    x+y
  }
}

addTest2(1)(9)
/***********************************************************
*  Functions which returns a function
*
* **********************************************************/

// this is very interesting to understand
// def greeting(language:String) create an object which takes one
// parameter its like function1. but can take Int, String and Function0 to 22
// objects.  here we assign it Function1 so
// it becomes function of Function. in case of def its
// return types is represented by String =>String

def greeting(language: String) = (name: String) => {
  val english = () => "Hello, " + name
  val spanish = () => "Buenos dias, " + name
  (language, name ) match {
    case (_,"english") => println("returning 'english' function")
                      english()
    case ("spanish",_) => println("returning 'spanish' function")
                      spanish()
    case (language, name )=> println(s"$language$name")
  }
}

val hello = greeting("english")
val buenosDias = greeting("spanish")
hello("Al")
buenosDias("Lorenzo")


/********************************************
  * partically applied functions
  * ************************************/
def process[A](filter:A=>Boolean)(list:List[A]):List[A] = {
  lazy val recurse = process(filter) _

  list match {
    case head::tail => if (filter(head)) {
      head::recurse(tail)
    } else {
      recurse(tail)
    }

    case Nil => Nil
  }
}

val even = (a:Int) => a % 2 == 0
val numbersAsc = 1::2::3::4::5::Nil
val numbersDesc = 5::4::3::2::1::Nil

process(even)(numbersAsc)   // [2, 4]
process(even)(numbersDesc)  // [4, 2]

/***************************************************
*  method call types,  call by value, call by
* **************************************************/
def something(x:Int) = {
  println("calling something")
  x // return value
}
//////////////////////////////////////////////////////////////////////////////////////
//Now we are going to define two function that accept Int arguments that are
//exactly the same except that one takes the argument in a call-by-value style (x: Int)
//and the other in a call-by-name style (x: => Int).
///////////////////////////////////////////////////////////////////////////////////////////////
def callByValue(x: Int) = {
  println("x1=" + x)
  println("x2=" + x)
}

def callByName(x: => Int) = {
  println("x1=" + x)
  println("x2=" + x)
}

callByValue(something(2))
callByName(something(786))
////////////////////////////////////////////////////////////////////////
def add7(n: Int): Int = n + 1
List(1,2,3).map(add7 )
// case of aynonmous function _ is not needed compiler do this for us
// since the map parameter type are function  f:x=>x
List(1,2,3).map(add7 _)
val z0=add7 _
val z: Int => Int = add7

def x001 = println("hi")
val H = x001
val H0 =x001 _
H0()

def plus(a: Int)(b: Int): Int = a + b
val p= plus _
val r = p(2)

def m1(x:Int) = x+3
// this convert method in function and calls apply method
println((m1 _).apply(3))
println((m1 _)(3))

// Functions cannot be overloaded in Scala, that is correct. But println is not a function,
// it is a method, and
// methods most definitely can be overloaded. The reason why function overloading isn't
// supported is simple: technically speaking, the term doesn't even make sense, since
// "overloading" basically means (very broadly) "different bindings for the same name
// depending on context". But functions don't have a name, so the whole concept of having
// different implementations bind to the same name is nonsensical

// _ converts a method into a function  example  def a = b _ where b is a function
// however when _ applied to a function,  it returns.  val afnc = bfnc _
//  however the right way is val afnc =bfnc
// the _ is not needed for fnc since they are not overloaded,  the methods needs to be over
// load therefore _ is needed
/***********************************************************
  *  types in scala and summary
  * **************************************************/

//(Int, Int)=>Int  aynomymous function  type, whenever you assign this  function type to def it
// will be aynonymous function.  you cannot assigned a def function to another function
// for assigning def to another function you have to convert it into aynonymous function by _
// f:(Int, Int)=>Int name function type,  you can access the function by f
// other types,  Int, Double,  String etc f:(Int, Int)=>Int is same as var f:(Int, Int)=>Int
//  generic types are given as follows  trait name[type]

// (num: Double, den: Double) =>{ num / den}
// create a new Function2 and => is converted to apply method with
// these parameter
//  in case input and output parameter are not given
//  they were determined by Int=>Int or by Function[Int, Int]


val test1: () => Int = {
  val r = util.Random.nextInt
  () => r
}
// * says zero or more arguments
def printAll(strings: String*) {
  strings.foreach(println)
}

//Use _* to adapt a sequence
//  As shown in the following example, you can use Scala’s _* operator to adapt a sequence
//(Array, List, Seq, Vector, etc.) so it can be used as an argument for a varargs field:
// a sequence of strings
val fruits = List("apple", "banana", "cherry")
// pass the sequence to the varargs field
var upperFruits=fruits.map( item=>{ if (item!="apple") item.toUpperCase })
printAll(fruits: _*)
//printAll(fruits)

//If you come from a Unix background, it may be helpful to think of _* as a “splat” operator.
//This operator tells the compiler to pass each element of the sequence to printAll as a
//separate argument, instead of passing fruits as a single argument.


  "hello".foreach(i => println(i))
  "hello".foreach(println(_))
  // here method   def println(x: Any) is convert to function
  // since the foreach takes a function def foreach[U](f: A => U)
  "hello".foreach(println)
///////////////////////////////////////////////////////////////////////////////////////////
// Since scala is a functional programming language it support recursion more and loop less
///////////////////////////////////////////////////////////////////////////////////////
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

