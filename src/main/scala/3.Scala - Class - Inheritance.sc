// difference between extend and with clause
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// In Scala, you can inherit from classes (concrete or abstract) and traits. In a similar way to how you can         ///
// extend only one class, but implement as many interfaces as you'd like in Java, you're only allowed to inherit     ///
// from one class, but as many traits as you'd like                                                                  ///
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

trait T1
trait T2
class P1
class P2

class C1 extends T1
class C2 extends T1 with T2
class C3 extends T2 with T1
class C4 extends P1 with T1
/// class C5 extends T1 with P1 // invalid
/// class C6 extends P1 with P2 // invalid
class C7 extends P1 with T1 with T2
class C8 extends P1 with T1 with T2

/////////////////////////////////////////////////////////////////////////////////////////////////
// 4.12. When to Use an Abstract Class
// Abstract classes can have constructor parameters as well as type parameters. Traits can have only type parameters.
// There was some discussion that in future even traits can have constructor parameters
// Abstract classes are fully interoperable with Java. You can call them from Java code without any wrappers.
// Traits are fully interoperable only if they do not contain any implementation code
// The are many uses of abstract clasees, the main purpose of abstract classes
// is to function as base classes which can be extended by subclasses to create a full implementation.
/////////////////////////////////////////////////////////////////////////////////////////////////
abstract class AnimalGen(var name:String) {
  def walk { println(name+" is walking") }
  def Eats:String
}

class Doggy(name:String, var home:String, var food:String) extends AnimalGen(name )
{
  def Eats=name +" Eats "+food +" and lives in "+home
}

println(new Doggy("Dog", "discovery Garden","bones").Eats)


/////////////////////////////////////////////////////////////////////////////////////////////////
// Traits
/////////////////////////////////////////////////////////////////////////////////////////////////
trait Human {
 def hello = "the Human trait"
}

trait Swim {
  def swim = println("Swimming!")
}
////////////////////////////////////////
// in scala with new operator we can extend the class with with class with another trait
// we cannot use extend clause here.
// in new operator we can create variable, create method or override method.
// here Human with Swim is type and can be using in variable also but when you defined it with variable method hello me
// become unaccessable
// Human with  Swim cannot be written as (Human with  Swim)
///////////////////////////////////////
var hu = new Human with  Swim {
  var age = 9
  def hellome = "I am defined inside trait new operator method"
  override def hello: String = "this is real human in years " + age.toString
}
println(hu.hello)
println (hu.swim)
println (hu.hellome)

trait Mother extends Human {
  override def hello = "Mother ->"+super.hello
}
trait Father extends Human {
  override def hello = "Father ->"+super.hello
}

// [Human, Father, Human, Mother, Human ]
// [Father, Mother, Human]
class Child extends Mother with Father with Human {
  def printnormal =hello
  def printSuper = super.hello
  def printMother = super[Mother].hello
  def printFather = super[Father].hello
  def printHuman = super[Human].hello
}

var child = new Child()
println(child.printnormal)
println(child.printSuper)
println(child.printMother)
println(child.printFather)
println(child.printHuman)

// Scala allows to mix in a trait (creating an anonymous type) when
// creating a new instance of a class.
//This means that with is usable outside of the top line of a class
// definition. Example:

//trait Swim {
//  def swim = println("Swimming!")
//}

class Person

val p1 = new Person  // A Person who can't swim
val p2 = new Person with Swim  // A Person who can swim

//p2 here has the method swim available to it, while p1 does not.
// The real type of p2 is an "anonymous" one, namely Person with Swim.
// In fact, with syntax can be used in any type signature:

def swimThemAll(ps: Seq[Person with Swim]): Unit = {
  ps.foreach(_.swim)
}

//The following won't compile:
// each `x` has no swim method
//def swim(xs: Seq[_ >: Person with Swim]): Unit = { xs.foreach(_.swim) }

//Meaning that in terms of lexical precedence, with binds eagerly.
// It's _ >: (Person with Swim), not  (_ >: Person) with Swim.

//A class can inherit from multiple traits but only one abstract class.
//Abstract classes can have constructor parameters as well as type parameters.
//Traits can have only type parameters. For example, you can’t say trait t(i: Int) {};
// the i parameter is illegal.
//Abstract classes are fully interoperable with Java. You can call them from Java code
// without any wrappers. Traits are fully interoperable only if they do not contain any implementation code.



trait Car {
  val brand: String=""
}

trait Shiny{
  val shineRefraction: Int
}

trait Miles{
  val miles: Int
}

abstract class fourWheeler {
  def name ="fourWheeler"
}
// note that Car , Shiny and Mile are interface. with = equal to implemnts clause in java
class BMW extends fourWheeler with Car with Shiny with Miles{
 // override val name="S3 Class"
 // we can override def with val.
  // the override will work
  override def name  ="S3 Class"
  override val brand = "BMW"
  val shineRefraction = 12
  val miles = 500
}

var obj:Car = new BMW
println(obj.brand)

var obj1:fourWheeler = new BMW
println(obj1.name)

var obj2 = new BMW
println(obj2.brand)
println(obj2.name)


var vCar = new Car {
  override val brand: String = "Custom Car"
  def steering ="power"
}
println (vCar.steering)
println (vCar.brand)

trait Animal {
  def walk { println("Animal is walking") }
}
class FourLeggedAnimal extends Animal {
  override def walk { println("I'm walking on all fours") }
}

class Dog extends FourLeggedAnimal with Animal{
  def walkThenRun {
    super.walk // works
    super[FourLeggedAnimal].walk // works
    super[Animal].walk // error: won't compile
  }
}
///////////////////////////////////////////////////////////////////////
//  How variables are overrides
///////////////////////////////////////////////////////////////////////
// a variable, class and method defined as final cannot be override
// only immutable variable define as val or def can be override
// var variables cannot be override
//  object of overriding a field is to override the values in immutable variables,  mutable variable cannot be override

class Vehicle{
  //var speed:Int = 60 is mutable so cannot be override
  val speed:Int = 60
  def modelNum:Int =1906
}
class Bike extends Vehicle{
  override val speed:Int = 100
  // a variable define with def can be override with val
  // however a variable define with val can be override with def
  //override val modelNum:Int =2018 both val and def with work
  override def modelNum:Int =2018
  def show(){
    println(speed+"  "+modelNum)
  }
}
var b = new Bike()
b.show()
///////////////////////////////////////////////////
// scala method override
//////////////////////////////////////////////////
// any concetrete implement if required a reimplementation needs to be override
// abstract implementation are not override

trait human01 {
  def greeting:String
  def voice =println("I have no voice")
}

trait father01 extends human01{
  def greeting="I am the father "
  override def voice =println("I am the father of a child")
}

class child01(var name:String ) extends father01 {
  def show ={
    greeting
    println(voice)
  }
}

println (new child01("Ali").voice)
println (new child01("Ali").name)

///////////////////////////////////////////////////////////////////
// Multiple inhertance method resolution
////////////////////////////////////////////////////////////////////
//   A -> B  A->C  B->D C->D is the diamond problem
//  Scala allows multiple instantiation of traits, which allows for multiple inheritance by adding a
//  distinction between the class hierarchy and the trait hierarchy. A class can only inherit from a
//  single class, but can mix-in as many traits as desired. Scala resolves method names using a
//  right-first depth-first search of extended 'traits', before eliminating all but the last
//  occurrence of each module in the resulting list. So, the resolution order is: [D, C, A, B, A],
//  which reduces down to [D, C, B, A].


