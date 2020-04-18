////////////////////////////////////////////////////////////////////////////////////////////
// case (x,y) unpacks x and y,  so after unpack you can use it in different ways in partical function and in filter/map methods etc
//  A partical function is an object of type PartialFunction[Int, Option[String]]
//  ,  containing isDefinedAt and apply methods, its been represented by case statement as show below
//  case (x) if x==0 => 10 compiler adds x==0 to isDefineaAt and 10 to apply
//  isDefinedAt is called by differnt,  match method take one argument and calls isDefinedAt only once
//  collect method take collections and calls isDefinedAt for every element of collect.  map method cannot call isDefinedAT
//  match method also call isDefinedAt for one element to which its been matched
//  case statement is a type of partical function, multiple case statements build a list of partical objects whose methods
//  are called for each element of collection
// isDefinedAt is a method on PartialFunction that can be used to determine if the PartialFunction will accept a given argument.
// Note PartialFunction is unrelated to a partially applied function.
//  Partical Function are subtype of Functions so we can pass partial functions to a method where
// we have to send a function.  this is demonstrated below
//////////////////////////////////////////////////////////////////////////////////////////////////

case class PhoneExt(name: String, ext: Int)
case class Phone(name: String, address: String, number: Int)

val extensions = List(PhoneExt("steve", 100), PhoneExt("robey", 200))

// use {} when case is being used.
extensions.filter { case PhoneExt(name, extension) => extension < 200}
extensions.filter { case PhoneExt(name, extension) => extension < 200
                    case _ => false}

// a case statement is actually a Partical function. multiple case statements are being checked for each element
// isDefinedAt function and their values if true the respective function in apply being called
// a partical function contains two methold idDefinedAt and apply
// once you define a case(x) if x==0 => true.  compiler adds x==0 in isDefinedAt and apply in true

val oneCase: PartialFunction[Any, Option[String]] = {
  case 1 => Some("one")
 // case 2 => Some("two")
 // case d: Int => None
  case PhoneExt(name, ext) if ext == 100 => Some(name)
  case PhoneExt(_, _) => Some("Empty")
  case _ => None
}

// each case statement has been translated as partical function object
val one: PartialFunction[Int, Option[String]] = new PartialFunction[Int, Option[String]] {
  def isDefinedAt(d: Int) = d == 1
  def apply(v: Int) = Some("one")
}

val two: PartialFunction[Int, Option[String]] = new PartialFunction[Int, Option[String]] {
  def isDefinedAt(d: Int) = d == 2
  def apply(v: Int) = Some("two")
}

println(oneCase(PhoneExt("steve", 200)))

// in case of Option,  the Some or None object is returned which has getOrElse function
// note the parameter must be given to function since some or none class is required
// if getOrElse is give the error message or alternative function can be given.
//  if not given  None will be displayed
println(oneCase(Phone("steve", "Milson Stress Australia", 222)))
println(oneCase(Phone("steve", "Milson Stress Australia", 222)).getOrElse("Error"))

// multiple case statement are being tested by isDefinedAt and separated by orElse
// means if first partical function fails the other works
// here is mixed case statement and partical function
val pfcase = oneCase.orElse(two)
println(pfcase(1))
println(pfcase(2))

// here i am going to prove that we can call isDefinedAt function from case statement
// and that case statement is compiled into two function isDefinedAt and apply function
// note the => represents apply function in scala
val testIsDefine: PartialFunction[Int, String] = { case 1 => "one" }

// calling isDefinedAt from the case guard statement, here statement isDefinedAt(x) where values of x is matched to case 1
testIsDefine.isDefinedAt(1)

// PartialFunction is a trait which has 22 types its a subtype of function
val convert1to5: PartialFunction[Int, String] = new PartialFunction[Int, String] {
  val nums = Array("one", "two", "three", "four", "five")
  def apply(i: Int) = nums(i - 1)
  def isDefinedAt(i: Int) = i > 0 && i < 6
}

val convert6to10: PartialFunction[Int, String] = new PartialFunction[Int, String] {
  val nums = Array("six", "seven", "eight", "nine", "ten")
  def apply(i: Int) = nums(i - 6)
  def isDefinedAt(i: Int) = i > 5 && i < 11
}

// note the flatMap does not return single object to be returned by by abstract function
// it requires a list of objects.  thats why list[String] has been added to partical function
//flatMap() assumes you are returning a collection of values rather than a single element.
// Thus these would work:
val phoneExt = new PartialFunction[PhoneExt, List[String]] {
  def apply(i: PhoneExt)= List(i.name)
  def isDefinedAt(i:PhoneExt) = i.ext<200
}

// a partical function being passed to where a function is required.   note that flatMap and collect
//  return list
val interleaved4 = extensions.flatMap{phoneExt}
val interleaved5 = extensions.collect{phoneExt}

val handle1to10 = convert1to5 orElse convert6to10
println("***************************************************************************************")
val handleAndThen =convert1to5 orElse convert6to10
println("***************************************************************************************")
handle1to10(10)
handleAndThen(10)

val l = List(1,2,3)
val t = List(-1,-2,-3)

//val pairs = l.zip(t).flatMap()
// flatMap takes an abstract method.  since partical function is subtype of function
//  we can pass partical function to flatMap
val interleaved2 = l zip t flatMap { case (x,y) => List(x,y)
 // case _ => List()
}

// flatMap return List of type.
val interleaved3 = extensions.flatMap {case PhoneExt(x,_)=>List(x)}


for (i<-interleaved3) yield println(i)

val dList =for (i <- List(1,2,3); y=i*i) yield { y*i}

def strings(list: List[Any]) = list flatMap {
  case st: String => Some(st)
  case _ => None
}
// list the flat map
def strings2(list: List[Any]) = list.collect {
  case st: String => st
 // case _ => None
}

// here the partical function isDefinedAt method checks weather s is string or not and
// then perform execution.  the method is called in collect which is like map or flatmap
strings2("hi" :: 1 :: "world" :: 4 :: Nil)

// here we are using flatmap but since flatmap does not call isDefinedAt we have to put case _
// which return None and some or none techniques take care of unmatched type
strings("hi" :: 1 :: "world" :: 4 :: Nil)
// returned list is a List[Any] (not as useful)
"hi" :: 1 :: "world" :: 4 :: Nil filter {_.isInstanceOf[String]}

val anyList = List ("hi",1,"world", 4, Nil)
val anyList1= "hi" :: 1 :: "world" :: 4 :: Nil
val anyList2=anyList.filter{_.isInstanceOf[String]}
val anyList3=anyList.filter(_.isInstanceOf[String])

// collect returns List[String]
val anyList5="hi" :: 1 :: "world" :: 4 :: Nil collect {case s:String => s}

// this will give match error since 1 does not match anything and there is no default method is there
// in case of collect this isDefinedAt method takes care of this.
// if anyList6 is defined as val.  we get error scala.collection.immutable.List.map(List)
// if we defined it as var we get scala.MatchError: reason is map does not have any isDefinedAt method
//var anyList6="hi" :: 1 :: "world" :: 4 :: Nil map {case s:String => s}

var anyList6="hi" :: 1 :: "world" :: 4 :: Nil collect {case s:String => s}
// wrong implementation
var anyList7="hi" :: 1 :: "world" :: 4 :: Nil map {case s:String => s
case _ => }

// use of lift function in in list
// the function avoids index out of bound option
val pets = List("cat", "dog", "frog")
val maybePet = pets lift _
maybePet(0)
maybePet(42)


val positive: PartialFunction[Int, Int] = {
  case x if x >= 0 => x
}

val odd: PartialFunction[Int, Boolean] = {
  case x if x % 2 == 1 => true
}

val even: PartialFunction[Int, Boolean] = {
  case x if x % 2 == 0 => true
}
// the number is positive and even
val evenCheck: PartialFunction[Int, Boolean] = positive andThen even
//  the number is negative and odd
val oddCheck: PartialFunction[Int, Boolean] = positive andThen odd

evenCheck.isDefinedAt(-2)
evenCheck.isDefinedAt(2)


val greaterThan20: PartialFunction[Any, Int] = {
  case (i: Int) if i > 20 => i
}

List(1, 45, 10, "blah", true, 25) collect greaterThan20

/*
Convert flatMap with partial function to collect
  // Before
  seq.flatMap {
    case P => Seq(???) // x N
    case _ => Seq.empty
  }

// After
seq.collect {
  case P => ??? // x N
}
 */








