//////////////////////////////////////////////////////////////////////////////////////////////
//  tuples in scala represents row. you can apply transformation on tuples collection through map and collect.
//  in case you only want to match one tuple use match.  in case you want to unpack tuple use  case ()
//  you can apply aggregation on on tuple collection by reduce.
//  reduce function works vertically,  they work with aggregates vertically
////////////////////////////////////////////////////////////////////////////////////////////////
//  var real = (1,2) is translated as
var real =Tuple2(1,2)

// a map in scala, its different from tuple
val keyv =(1->"who an i")

val (a1,b1) = keyv
println(s"${a1} has key ${b1}")
//  the procedure is the call""ed packing the tuples, the Tuple2 class is added at compile time
//  and is not require in code.  as show below
var a = (1,2)

//////////////////////////////////////////////////////////////////////////////////////////
// here are unpacking the tuple,  as per Lambda calculas to decompose a variable you needed
//  partial function.  here is actually how compile decompose the tuple for example
// (x**2 -9)= (x+3)(x-3)  where (x-3) are partial functions,  togather (x-3)(x+3) are called
//  composite functions.
////////////////////////////////////////////////////////////////////////////////////////////
var avar = a match { case (a,_) => a}
var bvar = a match { case (_, b:Int) => b}  // match only works with tuples why?

// another way for writing the above,  here variable name is anynomous in the same way the class
// name is anymous.  so var avar class(a,b)=a  is the defination which variable name ans class
// name is anynomous.  we access class members as avar.a  since avar is aynomous we can access
// it as "a"
var Tuple2(b,c)=a
var (e,g)=a

// the real way of writing the funciton,  the reason is same as described above.  for proof i have
//  provided the data typs as well
var (x:Int, y:Int)=a

// another way of writting the above
var cvar = real._1
var dvar = real._2

//////////////////////////////////////////////////////////////////////////////////////////////////
// note match will work with tuples and single values,  while map and collect with work with list
//////////////////////////////////////////////////////////////////////////////////////////////////
var tip = List((1,2),(3,5),(5,6))
// here case statement create an anynonmous case class,  which is just like
// the name case class   like  case test(a, b),  the  case class members are accessed as
//  object.a,  since here class has no name so we can access it as "a" and "b"
//  the reason tip.map{ ()=>a+b} cannot be used is,  this will a Tuple2 but here we need to define
//  custom case class,  this is another reason why case defined in { } you cannot define a object
//  inside () in scala
tip.map{case (a:Int,b:Int)=>a+b+1}
tip.collect{case (a,b)=>a+b+1}
/// this does not works with List why
// tip.match{case (a:Int,_)=>a},  match expects a tuple
var mVar = List((1,2),(3,4)) map { x=> x match {case (a:Int,_)=>a}}

val divisor = 13
//val divide =(denominator:Int)=> { divisor=divisor-1
//                                  divisor/denominator }

//print(divide(2))

//def divide2 (denominator:Int):Float ={ divisor=divisor-1
//  divisor/denominator}

//print(divide2(2))
for ((x,y) <- List((1,2),(3,4))) {
  println(s"${x} has value ${y}")
}
///////////////////
// tuple assignment formats
/////////////////////////
var complex = (1, (2,4))
var (c1, c2) = complex
var (c3,(c4,c5))=complex
var (c6,_)=complex
var (c7,c8)=(None,2)

import scala.util.Try
val txt = Array("ABC", "DEF", "GHI")

List(0,1,3,2,4,5).foreach(x => println(Try(txt(x)).getOrElse(println("Index ouf of bounds"))))
