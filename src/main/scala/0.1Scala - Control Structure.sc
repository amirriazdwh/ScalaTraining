///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//1. since scala is a functional programming language every statement is an expression, while in non functional language
// every line of structure is a statement.
// a functional language cannot read and write to presistence storage you needed non functional elements,  do and while loops do that
// {var c=1; var b=c+1 var d=c+b+1} the structure will return the last statment,  the same is true with
// while (c=read.file() ; c!=-1) since the last boolean its return true false,  means every element of scala (), {} return something
//  in functional programming
// do and while support multiline expression while ({}) you can place multiple statements in {} and last expression will return a value
// scala has no break keyword, its been implemented by scala.util.control._ for break the loop is inside breakable
//  for continue the loop is outside breakable
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var list = List(1,2,3,4,5)
val x = if (list.nonEmpty) list(1) else list(2)

// in case of java statement will be like this
// if () else if () else
var t =0
if (list.nonEmpty) {
  t=list(1)
  }else {
  t=list(2)
}
// compiler converts a for loop into following
1.to(10).map(((i) => i))


// the reason we use yield in scala programming is yield is a pure function, a pure function must return a value and should not
// have side effect.  side effect means it does not access variable outside it scope
// anything for () {} makes has side effect and may not return anything which is against functional programming
var x3=for (x <- List(1,2,3,4,5); y=x*x) yield (y*x)
// the above sytax is compiled as
List(1,2,3,4,5).map{x=> var y= x*x ;y*x}

// () is replacable with {} {} is used for multiple statements in scala
//curly braces { } are meant for multiline statements
// a function call will always contain funct(arg)
var x4=for {x <- List(1,2,3,4,5); y=x*x; z=y+1} yield {y*x*z}

for {x<-List(List('x', 'y', 'z'),List(1,2,3))} print (x)
for {x<-List('x', 'y', 'z').zip(List(1,2,3))} print (x)

// multiple for loop in scala {x {y}} yield (x,y) y is not accessable
for{x <- Range(1,10)} for{y<-Range(1,x)} print(x,y)
// right way to create the loop non function form
for {x<-Range(1,10); y<-Range(1,x)} print (x,y)
// right way functional form.  with function form each statement is an expressio
// and its result can be put into a variable.  the yield ensure this.
var double = for {x<-Range(1,10); y<-Range(1,x)} yield (x,y)

val a = Array("apple", "banana", "orange")
for (e <- a) println(e)

// in case of multiple lines use { } which
for {e <- a;
     // any calculation can be put here with =
     x=e.toUpperCase()
     } println(x)

val newArray = for (e <- a) yield {
  // imagine this requires multiple lines
   val s = e.toUpperCase;
  // s is a return.
  s}

// more concise functional way is
var up =a.foreach(_.toUpperCase())

// very important syntax,  its will not return a value as its a non function syntax
a.foreach{ x=>
          var v =x.toUpperCase()
          print(v)
        }


for (i <- 0 until a.length) {
  println(s"$i is ${a(i)}")
}

for ((e, count) <- a.zipWithIndex) {
   println(s"$count is $e")
  }
// how to use guards in scala
for (i <- 1 to 10 if i < 4) println(i)

for {
  i <- 1 to 10
  // first if is evaluate then the second if
  if i != 1
      if i % 2 == 0
} println(i)

for {
  i <- 1 to 10
  if i > 3
    if i < 6
      if i % 2 == 0
} println(i)

val tuples = List(("fname","Robert"), ("lname" , "Goren"))
for ((k,v) <-tuples) println(s"key: $k, value: $v")

// map contains a tuple so access method is same
//Looping over a Map
val names = Map("fname" -> "Robert", "lname" -> "Goren")
for ((k,v) <- names) println(s"key: $k, value: $v")
/////////////////////////////////////////////////////////////////////
// while and do loop is not a functional construct
// they are used in scala for persistence,  means for reading and
// writing to databases or files, functional constructs could not write
// to presistence storage.
/////////////////////////////////////////////////////////////////////
var sum = 0
var i = 0
while (i <= 1000 && sum <= 1000) { sum += 1; i += 1 }

var sid = 4;

do {
      //println("Student Id is:"+sid);
      sid +=  1
}while(sid < 10)
// this also dont return any values
var dowhile =do {
  //println("Student Id is:"+sid);
  sid +=  1
  sid
}while(sid < 10)




// define import
import java.io._

var in = None: Option[FileInputStream]
var out = None: Option[FileOutputStream]

try {
  in = Some(new FileInputStream("/tmp/Test.class"))
  out = Some(new FileOutputStream("/tmp/Test.class.copy"))
  var c = 0
  // the while loop is very interesting here, non functional construct uses () and dont support {} but
  // we can use {} inside ()
  while ({ c = in.get.read; c != -1}) {
    out.get.write(c)
  }
} catch {
  case e: IOException => e.printStackTrace
} finally {
  println("entered finally ...")
  if (in.isDefined) in.get.close
  if (out.isDefined) out.get.close
}

///////////////////////////////////
// try and catch block
///////////////////////////////////
val s = "Foo"
try {
  val i = s.toInt
} catch {
  case e: Exception => e.printStackTrace
}

try {
  val i = s.toInt
} catch {
  case _: Throwable => println("exception ignored")
}

// nothing required here
def toInt(s: String): Option[Int] =
  try {
    Some(s.toInt)
  } catch {
    case e: Exception => throw e
  }

//If you prefer to declare the exceptions that your method throws, or you need to interact
//with Java, add the @throws annotation to your method definition:
@throws(classOf[NumberFormatException])
def toInt1(s: String): Option[Int] =
  try {
    Some(s.toInt)
  } catch {
    case e: NumberFormatException => throw e
  }

/////////////////////////
// break loop valid after 2.8
/////////////////////////////////////
import scala.util.control._

val numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
val loop = new Breaks

loop.breakable {
  for( a <- numList){
    println( "Value of a: " + a )

    if( a == 4 ){
      loop.break;
    }
  }
}
println( "After the loop" );

///////////////////////////////////////////////
for( a <- numList) {
  println("Value of a: " + a);

  loop.breakable {
    for (b <- numList) {
      println("Value of b: " + b);

      if (b == 12) {
        loop.break
      }
    }
  } // inner breakable

  ////////////////////////////////////
  // breaking the nest loop
  ////////////////////////////////////
  val numList1 = List(1, 2, 3, 4, 5)
  val numList2 = List(11, 12, 13)

  val outer = new Breaks;
  val inner = new Breaks;

  outer.breakable {
    for (a <- numList1) {
      println("Value of a: " + a);

      inner.breakable {
        for (b <- numList2) {
          println("Value of b: " + b);

          if (b == 12) {
            inner.break;
          }
        }
      } // inner breakable
    }
  } // outer breakable.
}
///////////////////////////////////////////
// how to implement continue
////////////////////////////////////////
val searchMe = "peter piper picked a peck of pickled peppers"
  var numPs = 0
  for (i <- 0 until searchMe.length) {
    loop.breakable {
      if (searchMe.charAt(i) != 'p') {
        loop.break // break out of the 'breakable', continue the outside loop
      } else {
        numPs += 1
      }
    }
  }
  println("Found " + numPs + " p's in the string.")

