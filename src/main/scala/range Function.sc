import scala.collection.immutable.Range

// range is an object in scala which generates integrers
val rang = new Range(1,10,2)
val lrang =rang.toList

// the compile has sugar coating here, the below syntx compile to
// a Range function
val a =1 to 10
// here we are calling the object function of Array
val a1 = Array.range(1, 10)


val b = 1 until 10
val b1 = Vector.range(1, 10)


val c = 1 to 10 by 2
val c1 = List.range(1, 10, 2)

//Array of Strings
//Array has length, iterator
val args = Array("Hello", "world", "it's", "me")
// convert to list
val largs =args.toList
val targs =args.iterator
 val string = args.mkString(" ")

val numbers = Array(1,2,3)
val string1 = numbers.mkString(", ")



val d = 'a' to 'c'
val d1 = collection.mutable.ArrayBuffer.range('a', 'd')

val x =a.toList
val str =d.toString()
print(str)
val y = a.toArray
val z = a.toSet

// please note the <- is iterator in scala
// scala compiler converts the 1 to 10 or 1 until 10 to range
// then it converts it to list and gets the iterator using <- operator
// which helps in looping

for (x <- (1 to 10))
{
  println(x)
}
// is converted to a list by scala compiler to get iterator
for (x <- (1 to 10).toList)
{
  println(x)
}

for (i <- 0 until args.length) {
  println(s"$i is ${args(i)}")
}


val x11 = List.tabulate(5)(_-1)
val x12 = List.tabulate(5)(_-5)

