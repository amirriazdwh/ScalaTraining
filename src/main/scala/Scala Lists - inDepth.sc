//////////////////////////////////
// List of Strings
/////////////////////////////////
// :: - adds an element at the beginning of a list and returns a list with the added element
// in scala  [] define the type of an object thats why you canot define list as
// val a =[] there is no sugar coating here
// a list has an index and an iterators
// <- means iterator
// a list is immutable
// :x means list null to left
// x: means convert to list null to right so x:+2 means add 2 to right
// and x+:2 means convert :2 to list add to list
// x:::y   x: convert to list :Y convert to list : appends

//Another important idea illustrated by this example will give you insight into
// why arrays are accessed with parentheses in Scala. Scala has fewer special cases
// than Java. Arrays are simply instances of classes like any other class in Scala.
// When you apply parentheses surrounding one or more values to a variable, Scala
// will transform the code into an invocation of a method named apply on that
// variable. So greetStrings(i) gets transformed into greetStrings.apply(i).
// Thus accessing an element of an array in Scala is simply a method call like any
// other. This principle is not restricted to arrays: any application of an object
// to some arguments in parentheses will be transformed to an apply method call.
// Of course this will compile only if that type of object actually defines an
// apply method. So it's not a special case; it's a general rule.

// create an empty list
// scala.collection.mutable and scala.collection.immutable
//////////////////////////////////////////////
List(List(1,2,3),List(4,5,6),List(7,8,9)).flatten
// flatMap, first apply transformation and then flatten the list
List(1, 2, 3).map(x => List(x, x + 0.5)) // create list of list
List(1, 2, 3).flatMap(x => List(x, x + 0.5)) // List(1.0, 1.5, 2.0, 2.5, 3.0, 3.5)

// With map/reduce,  it create a list of list and the appends the lists togather
List(1, 2, 3).map(x => List(x, x + 0.5)).reduce(_++_)// Same result as above!


val list = 1 :: 2 :: 3 :: Nil
// access static method
val x = List.range(1, 10)
x.filter(_>5)

// here <- get the external iterator of list and then iterate over the list
for (x <-list){
  print(s"the number is: ${x}")
}

//At a high level, Scala’s collection classes begin with the Traversable and Iterable traits,
//and extend into the three main categories of sequences (Seq), sets (Set), and maps (dictionary)
//(Map). Sequences further branch off into indexed and linear sequences. Traversable are foreach
// while iterator is for loop

// sequence class are index sequence and linear sequences.
// index sequence can be accessed by index while linear sequence are linked list with head tail

//filter have to evaluate the whole Stream.
List(1, 2, 3, 4).filter(_ % 2 == 1)
//takeWhile() stop evaluation immediately after condition is not met.
List(1, 2, 3, 4).takeWhile(_ % 2 == 1)
// drop the letter where condition is true it true for 1 and thats where
// the calculation is stopped
List(1, 2, 3, 4).dropWhile(_ % 2 == 1)
// contain method
List(1, 2, 3, 4).contains(4)
//
//List(1, 2, 3, 4).copyToArray()


List(1, 2, 3).foldLeft(0)(_ + _)
// ((0 + 1) + 2) + 3
// = 6
List(1, 2, 3).foldRight(0)(_ + _)
// 1 + (2 + (3 + 0))
// = 6
List(1, 2, 3).fold(0)(_ + _)

List(1, 2, 3).reduce(_+_)
List(1, 2, 3).reduceLeft(_+_)
//(3+2)+1
List(1, 2, 3).reduceRight(_+_)
//(1+2)+3
List(1, 2, 3).min
List(1,2,3).max
//
List.fill(3)("foo")
List.tabulate(5)(n => n * n)
// add element to right : means list
val x1=x.:+(9)
// the sugar coated version are, x: is list
val xx =x:+0
val y = 0 +: x
// append list
val yy = xx++list
/////////////////////////////////////////////////
//empty list
val elist =List()
elist.contains("")
elist.exists(_==List.empty)
// :: means append list. which is more like elist.::(1)
val elist1 =1 :: elist
// define more like
elist1.::(3)

val strlist = "1" :: elist1
// ::: append two lists
val strlist2 = strlist:::elist1
// define more like
strlist.:::(elist1)
//////////////////////////
// access list
////////////////////////
//print(elist(2))
//elist actually use apply method
//print(elist.apply(2))

///////////////////////////////////
//
//
///////////////////////////////////

List(1,2,3,3,3,4).distinct

val countries = List("France", "Switzerland", "Germany", "Spain", "Italy", "Finland")
countries.head
countries.headOption
// this is list - head
countries.tail
countries.last
countries.lastOption
countries.lastIndexWhere(_=="Spain")
countries.lastOf
countries.filter(_.length > 6)
countries.filterNot(_.length>6)

countries.reduceRight(_+_)
countries.reduceLeft(_+_)
//Taking, droping and splitting,  take first 2 from left
countries.take(2)
// take from right
countries.takeRight(2)
// drop first 2 from left
countries.drop(2)
// drop from right
countries.dropRight(2)
// split at 2
countries.splitAt(2)
// zip with index
countries.zipWithIndex
// zip the array
countries.zip(List("Paris", "Bern", "Berlin", "Madrid", "Rome", "Helsinki"))
val zip =countries.zip(List("Paris", "Bern", "Berlin", "Madrid", "Rome", "Helsinki"))
// unzip the list
zip.unzip
zip.slice(2,4)
println(countries.diff(List("Paris", "Bern", "Berlin", "Germany", "Spain", "Italy", "Finland")))
//
countries.foldLeft("<<<<<<")((concat, x) => concat + x.toUpperCase)
//
countries.fold("||")((concat, x) => concat + x.toUpperCase)
countries.exists(_=="Germany")
countries.contains("Germany")
// very important function
List((3,1),(2,2),(2,3)).forall{
  _ match {
            case (x,_) if x==2  => true
              // without this exception will be thrown
            case (x,_) if x!=2 => false
  }
}
//countries.foldRight("||")((concat, x) => concat + x.toUpperCase)
// map function with transformation function
countries.map(_.toUpperCase)

// countries.zipWithIndex creates list of tuples
for ((v,i)<-countries.zipWithIndex)
  {
    println(s"${i} has value ${v}")
  }
// note here == operator will not work
countries.takeWhile(_.equals("Germany"))
countries.dropWhile(_=="Germany")
//
val s = "abba.aadd"
val (beforeDot,afterDot) = (s takeWhile (_!='.'), s dropWhile (_!='.'))

// list of strings [] define the list type
val fruit: List[String] = List("apples", "oranges", "pears")

// access through iterator
for(x <- fruit) yield (print(x))
// List of Integers
val nums: List[Int] = List(1, 2, 3, 4)

// Empty List.
val empty: List[Nothing] = List()

// Two dimensional list
val dim: List[List[Int]] =
  List(
    List(1, 0, 0),
    List(0, 1, 0),
    List(0, 0, 1)
  )

val  l = List(1,2,3,4,5,6,7)
// map function contains transformations
l.map(x=>x*2)

// any method is taken as mapping.
def fMethod(x:Int) ={ if(x>2) Some(x) else None }
// any function is taken as transformation.  a transformation is passed into a mappings
val f:Int=>Option[Int] =(x :Int) => { if(x>2) Some(x) else None }
// a mapping containing a transformations
l.map((x :Int) => { if(x>2) Some(x) else None })


val add:(Int, Int)=>Int=(a,b)=>a+b
var addTup =add.tupled
val addT:((Int, Int))=>Int={case (a,b)=>a+b}


var tip = List((1,2),(3,5),(5,6))
var resT =tip.map(x=>add(x._1, x._2))



// list the flat map
def StrColl(list: List[Any]) = list.collect {
  case st: String => st ;
  case _ => None;
}

StrColl("hi" :: 1 :: "world" :: 4 :: Nil)

//def StrTest(list: List[Any]) = list.match {
//  case st: String => st ;
//  case _ => None;
//}
// list the flat map
def StrMap(list: List[Any]) = list.map {
  case st: String => st ;
  case _ => None;
}

StrMap("hi" :: 1 :: "world" :: 4 :: Nil)

//var anyList7="hi" :: 1 :: "world" :: 4 :: Nil map {case s:String => s}

val women = List("Kim", "Julia")
val men = List("Al", "Terry")
val couples = women zip men
val uncouples =couples.unzip


//////////////////////////////
// difference between ::: and ++
////////////////////////////

//Legacy. List was originally defined to be functional-languages-looking:

1 :: 2 :: Nil // a list
val mlist =x ::: y  // concatenation of two lists

mlist match {
  case head :: tail => "non-empty"
  case Nil          => "empty"
}
//Of course, Scala evolved other collections, in an ad-hoc manner.
//When 2.8 came out, the collections were redesigned for maximum code reuse
// and consistent API, so that you can use ++ to concatenate any two
// collections -- and even iterators. List, however, got to keep its original
// operators, aside from one or two which got deprecated.

val mlist1 =x++y
mlist1 match {
  case head :: tail => "non-empty"
  case Nil          => "empty"
}

val m1= List(1,2,3) ++ List(4,5)
val m2 =List(1,2,3) ::: List(4,5)
// how two list are matched
m1 ==m2
///////////////////////////////////////////////////////////////////////
//:: prepends a single item whereas ::: prepends a complete list.
// So, if you put a List in front of ::
// it is taken as one item, which results in a nested structure.
///////////////////////////////////////////////////////////////////////

val list1 = List(1,2)
val list2 = List(3,4)
// here list1: uses : as append operator to add element of list2
list1::list2
2 :: list1
2+:list1
list1:+2
// list: uses : as append operator to add :list
list1:::list2
//////////////////////////////////////////////////////
//Deleting Elements from a List (or ListBuffer)
//////////////////////////////////////////////////////////
// in case of immutable
var x12 = List(5, 1, 4, 3, 2)
val x111 = x12.filter(_ !=2)
// second mehtod
var bufflist =x12.toBuffer-=(2)
bufflist.toList

////////////////////
import scala.collection.mutable.ListBuffer
val lmutable  = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9)
lmutable-=2
// x-y,  element belong to list 1 but not in list 2
lmutable-=(4,5)
lmutable.remove(1)
lmutable.remove(1, 4)
//You can also use --= to delete multiple elements that
// are specified in another collection:
//--= remove multiple elements elements and take seq as all collection is being derived from seq you can use seq also
lmutable --= Seq(7,8,9)

///////////////////////////////////////////////////////
//  exists vs contains
//////////////////////////////////////////////////////
//Don’t use equality predicate to check element presence
  // Before
lmutable.exists(_ == 6)
// use exists for x>6
//  After,  use contains for == operator only
lmutable.contains(6)
//The second expression is semantically equivalent, yet more concise.
//When those expressions are applied to Set classes, performance might be different as night and day,
// because sets offer close to O(1) lookups (due to internal indexing, which is left unused within exists calls).

//contains takes any data type. it retruns false
lmutable.contains("6")
List(4,5,6).contains(4)

// list contains another list, here contains is a method which is
// being convert to function and then applied
List(1, 2).forall(List(1, 2, 3, 4).contains)
List(1, 2).exists(List(1, 2, 3, 4).contains)
////////////////////////////////////////////////////////////////////////
"thisis9Amir0Riaz1good".split("[0-9]").maxBy(_.length)
// this will group the elements by key which is length and length will be part of array
"thisis9Amir0Riaz1good".split("[0-9]").groupBy(_.length).toList
"thisis9Amir0Riaz1good".split("[0-9]").sortBy(_.length).toList
"thisis9Amir0Riaz1good".split("[0-9]").sortWith(_>_)
"thisis9Amir0Riaz1good".split("[0-9]").sortWith(_<_)

val xs = Seq(1, 5, 3, 4, 6, 2)
// Sort using Natural ordering as defined for Integers in Scala Library
xs.sorted //1,2,3,4,5,6
// Sort 'with' a comparator function
xs.sortWith(_<_) //1,2,3,4,5,6
xs.sortWith(_>_) //6,5,4,3,2,1
xs.sortWith((left,right) => left > right) //6,5,4,3,2,1


//returns false why it return false see the reason below
"thisis9Amir0Riaz1good".split("[0-9]").containsSlice(Seq("good","Riaz"))
///////////////////////////////////////////////////////////////////////////////
val rushSeq = Seq("Dirk", "Lerxst", "Pratt")
val rushA = Array("Dirk", "Lerxst", "Pratt")
val rushL = List("Dirk", "Lerxst", "Pratt")
val rushV = Vector("Dirk", "Lerxst", "Pratt")

val gotGene = rushSeq contains "Gene"
// gotGene: Boolean = false
val gotNeil = rushA contains "Pratt"
// gotNeil : Boolean = true
val gotAlex = rushL contains "Lerxst"
// gotAlex : Boolean = true
val gotPeter = rushV contains "Peter"
// gotPeter : Boolean = false
val gotGedAndAl = rushSeq containsSlice Seq("Dirk", "Lerxst")
// gotGedAndAl: Boolean = true

//Sequences and the like also define a containsSlice method that takes a sequence
// and checks it is found in the collection. Order does matter, though:

// rushSeq has "Dirk" before "Lerxst", so the following fails
val gotAlAndGed = rushSeq containsSlice Seq("Lerxst", "Dirk")
// gotAlAndGed: Boolean = false

val got1 =rushL.containsSlice(List( "Dirk", "Lerxst"))
val gotXs = "Lerxst" containsSlice "xs"








