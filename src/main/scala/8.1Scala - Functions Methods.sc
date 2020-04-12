// Note:  High order function take function as arguments or return function
// Note:  Partically applied function uses _ to indicate that argument is defined inside the higher order function.
//        def mapCustom(x:Int => Int) higher order function tells that it take function with one parameter and you can
//        define that parameter as _,  _ takes its type and number of parameters from x:Int=>Int.
//        def reduceCustom(x:(Int, Int)=>Int) take two parameter both are defined inside the body so we dont their names
//        so we can use _+_
// Note:  method def add(a:Int, b:Int) is passed as anymous function into reduceCustom.  the method is convert to function
//        implicity and we dont give parameters as they are derived from function parameter defination
// Note:  In mathematics and computer science, currying is the technique of translating the evaluation of a function that takes
//       multiple arguments into evaluating a sequence of functions, each with a single argument.

// note def is nearly the same as val.  def method is called on call while val method is called on definition
def adda=(a:Int,b:Int)=>{a+b}
def addb(a:Int,b:Int)=(a:Int,b:Int)=>{a+b}

def add(a:Int, b:Int) = a+b
var p = for(i<- 1 to 2) yield List(1,2,3,4).map(add(i,_))
p.foreach(println)
// how you can pass two or more parameters into map
var l=List((1,2),(3,4),(5,6)).map{case (x,y)=>(x,y,x+y)}
l.foreach(print)

// higher order function with clousure.
def mapCustom(x:Int => Int) = {
  val p1=786
  x(p1)  // p1 is a bounded function in method and x(p1) is a clousure function, since the scope of p1 is closed in this method
         //  therefore its called closure function
}
// with partically applied function we can pass two parameter where we supposed to send one
// _ means that only one parameter will bind value to this function inside higher order function
// since its definition is x:Int =>Int
print(mapCustom(add(4,_)
  /*return a function x:Int => Int The variable _ is defined inside higher order function*/
))

def reduceCustom(x:(Int, Int)=>Int)={
  val p1=10; val p2=20;
  x(p1,p2) // x is a clouser function since it takes two parameters from outside environments.
}

print(reduceCustom(_+_))
// here add has two parameter function
// it can be called as
var cfadd1=(a:Int,b:Int)=>a+b
//how method of different format is assigned
// (_,_) IS EQUAL TO _
// (10,_) REQUIRE Int =>Int
var x1:(Int)=>Int=add(10,_)
// x2 require (Int,Int)=>Int
var x2:(Int,Int)=>Int=add
// x3 does not require any type since _ infer the type from add definition
var x3=add _

println(s"Add with ${reduceCustom(add)}")
// its defined as var x:(Int,Int)=>Int=add(_,_)
println(s"Add with _,_ ${reduceCustom(add(_,_))}")
println(s"Add with _ ${reduceCustom(add _)}")

// high order function which return a function
def doSome() = {
  val x = 10
  // f is an clousure function with x free variable，y bound variable
  val f = (y: Int) => x + y
  f
}

val foo = doSome()
println(foo(20))    // 30
println(foo(30))    // 40


/////////////////////////////////////////////////////////////////////////////
// the currying function usage
////////////////////////////////////////////////////////////////////////////
def wrap(prefix: String)(html: String)(suffix: String) = {
  prefix + html + suffix
}
// in case (_) the type is infered from function to which the method is passed or assigned.
//  in case of _ the type is informed from them method definition
// the usage of currying functions
val wrapWithDiv:String=>String = wrap("<div>")(_)("</div>")
//val wrapWithDiv:String=>String = wrap("<div>")(_:String)("</div>")

print(wrapWithDiv("Hello"))
print(wrapWithDiv("How are you"))

def cadd(a:Int)( b:Int) = a+b
//curried functions are used when when higher order functions takes one function
// take one parameter and we have function which has
print(mapCustom(cadd(4)))
print(mapCustom(cadd(4)(_)))

val cfadd = add _
val fadd =new Function2[Int,Int,Int] { def apply(num:Int, den:Int):Int =add(num,den)  }
print(fadd) // return a function
print(cfadd) // return a function
//print(add) will not compile
print(fadd(2,3))
print(cfadd(2,3))
///////////////////////////////////////////////////////////////////////////////////

def doOther() = {
  var x = 10
  val f = () => { x -= 1; x }
  f // return the function
}

val f1 = doOther()
val f2 = doOther()

println(f1())    //
println(f2())    //

// this method return an anymous function
def doSome(x: Int) = (a: Int) => x + a
val f11 = doSome(100)    //
val f21 = doSome(200)    //
println(f11(10))    //110
println(f21(10))    // 210

def swap(number: Array[Int], i: Int, j: Int) {
  val t = number(i)
  number(i) = number(j)
  number(j) = t
}

def selection(number: Array[Int], order: (Int, Int) => Boolean) {
  def mm(m: Int, j: Int): Int = {
    if(j == number.length) m
    else if(order(number(j), number(m))) mm(j, j + 1)
    else mm(m, j + 1)
  }
  for(i <- 0 until number.length -1; m = mm(i, i + 1)
      if i != m
      ) swap(number, i, m)
}

val arr1 = Array(2, 5, 1, 7, 8)
selection(arr1, (a: Int, b: Int) => a < b)
println(arr1.mkString(","))                  //



trait PlaceholderExample {
  def process[A](f: A => Unit)

  val set: Set[_ => Unit]

 // set.foreach(process _) // Error
  set.foreach(process(_)) // No Error
}

//In the first case, process _ represents a method; Scala takes the polymorphic method and attempts to make
// it monomorphic by filling in the type parameter, but realizes that there is no type that can be filled in for
// A that will give the type (_ => Unit) => ? (Existential _ is not a type).

//In the second case, process(_) is a lambda; when writing a lambda with no explicit argument type, Scala infers
// the type from the argument that foreach expects, and _ => Unit is a type (whereas just plain _ isn't), so it can
// be substituted and inferred.//
//
val x:Int=>Int = add(10,_)

print(x(2))


