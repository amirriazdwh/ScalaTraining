import PolyTest.dup
//Example showing why foo(_) and foo _ are different:

//  This example comes from 0__:

trait PlaceholderExample {
  def process[A](f: A => Unit)
  //def process(f: Int => Unit)

  val set: Set[_ => Unit]

  //set.foreach(process _) // Error
  set.foreach(process(_)) // No Error
}
//In the first case, process _ represents a method;
// Scala takes the polymorphic method and attempts to make it
// monomorphic by filling in the type parameter, but realizes
// that there is no type that can be filled in for A that will give
// the type (_ => Unit) => ? (Existential _ is not a type).

//In the second case, process(_) is a lambda; when writing a lambda
// with no explicit argument type, Scala infers the type from the
// argument that foreach expects, and _ => Unit is a type (whereas
// just plain _ isn't), so it can be substituted and inferred.
//This may well be the trickiest gotcha in Scala I have ever
// encountered.

object PolyTest  {
  def dup[T](x: T, n: Int): List[T] =
    if (n == 0) Nil
    else x :: dup(x, n - 1)
  println(dup[Int](3, 4))
  println(dup("three", 3))
}

println(dup[Int](3, 4))
println(dup("three", 3))

def listOfDuplicates[A](x: A, length: Int): List[A] = {
  if (length < 1)
    Nil
  else
    x :: listOfDuplicates(x, length - 1)
}
println(listOfDuplicates[Int](3, 4))  // List(3, 3, 3, 3)
println(listOfDuplicates("La", 8))  // List(La, La, La, La, La, La, La, La)

case class MyPair[A, B](x: A, y: B);
object InferenceTest3 extends App {
  def id[T](x: T) = x
  val p = MyPair(1, "scala") // type: MyPair[Int, String]
  val q = id(1)              // type: Int
}

val x: MyPair[Int, String] = MyPair[Int, String](1, "scala")
//val y: Int = id[Int](1)






