//////////////////////////////////////////////
// val Uninitialized: Nothing = null
/////////////////////////////////////////////
case class Address007(city: String, state: String, zip: String)

case class Person007(var username: String, var password: String) {
  var age = 0
  var firstName = ""
  var lastName = ""
  var address = None: Option[Address007]
}

val p01 = Person007("alvinalexander", "secret")
p01.address = Some(Address007("Talkeetna", "AK", "99676"))

p01.address.foreach { a =>
  println(a.city)
  println(a.state)
  println(a.zip)
}

//////////////////////////////////////////////////////////////
// Handling Constructor Parameters When Extending a Class
/////////////////////////////////////////////////////////////
class Person008 (var name: String, var address: Address007) {
  override def toString = if (address == null) name else s"$name @ $address"
}
// name and address mutator are already generated
class Employee008 (name: String, address: Address007, var age: Int)
  extends Person008 (name, address) {
  // rest of the class
}
//////////////////////////////////////////////////////////
//auxiliary constructor
/////////////////////////////////////////////////////////
// In Scala, every auxiliary constructor must invoke another
// constructor of the same class as its first action. The net effect
// of this rule is that every constructor invocation in Scala will
// end up eventually calling the primary constructor of the class.
// The primary constructor is thus the single point of entry of a
// class.

// in case of no parameter Employee and Employee() both can be used
// there constructors can by call as this() in both cases

// Note: you specifiy var and private in primary constructor since
// they are created as field in class body.

// Each Auxiliary Constructor must call a previously defined constructor:
// it may be either Primary Constructor or Auxiliary Constructors.
// This call should be first statement in that Constructor.

/*******************************
 *  all constructors are accessed by this
 *  a this is reference to object structure in memory created by new
 *  which contains methods and fields. this is a pointer to that memory location
 *  we access fields by this.fieldname and constructor as this()
 *  on completion of contructor call this is assigned to variable.
 ******************************* */

class Pizza (var crustSize :Int, var crustType:String){
  // one-arg auxiliary constructor
  // var and private are not needed here as crustSize is method parameter
  // these fields are already created in class body.
  def this(crustSize:Int) =this(crustSize, Pizza.DEFAULT_CRUST_TYPE)

  // one-arg auxiliary constructor
  def this(crustType: String) {
    this(Pizza.DEFAULT_CRUST_SIZE, crustType)
  }

  def this() = this(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
  override def toString = s"A $crustSize inch pizza with a $crustType crust"
  // return this from a class
  def returnThis:this.type = {
    this }
}

object Pizza {
  val DEFAULT_CRUST_SIZE = 12
  val DEFAULT_CRUST_TYPE = "THIN"
}

val p1 = new Pizza()
val p2 = new Pizza
val p3 = new Pizza(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
val p4=  new Pizza(Pizza.DEFAULT_CRUST_SIZE)
val p5 = new Pizza(Pizza.DEFAULT_CRUST_TYPE)

// below primary constructor can written as Employee instead of Employee()
class Employee() {
  var name = "John Q. Public"
  var salary = 0.0
  // auxiliary constructor
  def this(n: String, s: Double) {
    // call to primary constructor
    this();
    name = n;
    salary = s; }
}
//////////////////////////////////////////////////////
// static methods,  this creates a singleton object
///////////////////////////////////////////////////
object Employee {
  private var emp = new Employee("Amir", 16000)
  emp.name="Mr. Amir Riaz"
  val v_name =emp.name
  def getpro:String  = emp.name + emp.salary.toString
}

var emp0 = new Employee
var emp1 = new Employee("Amir", 16000)
println(emp1.name)
println(emp1.salary)

// static method call in scala
println(Employee.getpro)
// static property call
println(Employee.v_name)

//Primary constructor parameters with val and var are public.
// However, because vals are immutable, you can’t write the following.

////////////////////////////////////////////
// override getter and setters in scala
///////////////////////////////////////////
//  1. declare variable with private access modifier this will not create getter or setter
//  2. define variable with _ as _x,  this is just for naming convention
//  3. create methods in body of class
//////////////////////////////////////////////////

class Point {
  // private prevents access and setters being generated
  private var _x = 0
  private var _y = 0
  // private prevents access being generated
  private val bound = 100

  def x = _x

  def x_=(newValue: Int): Unit = {
    if (newValue < bound) _x = newValue else printWarning
  }

  def y = _y

  def y_=(newValue: Int): Unit = {
    if (newValue < bound) _y = newValue else printWarning
  }

  private def printWarning = println("WARNING: Out of bounds")
}

object Point {
  // static variable,  its a singleton variable
  var check = 99
  // static private variable defined in scala
  private var Ls = 1 to 10

  // static method defined in scala
  def getSum = {
    Ls.reduce(_ + _)
  }
}

val point1 = new Point
point1.x = 99
point1.y = 101 // prints the warning
println(Point.check)
println(Point.getSum)
println(Point.check)

//////////////////////////////////////////////////////////////////
// intentionally left the 'private' modifier off _symbol
//////////////////////////////////////////////////////////////////
class StockH(private var _greetings : String) {
  def greetings = _greetings
  // in case of default setter the greetings variable create greetings_$eq method in java
  // scala sugar coat it as greetings ="".  once we over we have to define the method as
  // greetings_= which is equivalent to greetings_$eq
  def greetings_= (aString: String) {
    // that variable is assigned as _greetings
    _greetings=aString}
}

var sto = new StockH("How Are you")
sto.greetings="what is your name"
println(sto.greetings)
////////////////////////////////////////////////////////////////


class StockQ {
  // a private field can be seen by any Stock instance
  // private field limits setter and mutators
  // _ after variable declaration initialize the variable to a default value
  private var price: Double = _
  // here we used java bean style getter and setters
  def setPrice(p: Double) { price = p }
  def getPrice = price
  //any instance of a Stock class
  //can access a private field of any other Stock instance
  def isHigher(that: StockQ): Boolean = this.price > that.price
}

val s1 = new StockQ
println(s1.getPrice)
s1.setPrice(20)

val s2 = new StockQ
s2.setPrice(100)

import scala.math._

case class Circle(radius: Double) {
  import Circle._
  def area: Double = calculateArea(radius)
}

object Circle {
  def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)
}

val circle1 = new Circle(5.0)

//////////////////////////////////////////
// Object-private fields
//////////////////////////////////////////
class Stock {
  // a private[this] var is object-private, and can only be seen
  // by the current instance
  private[this] var price: Double = _
  def setPrice(p: Double) { price = p }
  // error: this method won't compile because price is now object-private
  // def isHigher(that: Stock): Boolean = this.price > that.price
}

///////////////////////////////////
/// case classes
///////////////////////////////////
case class Book(title: String, pages: Int)

val b1 = Book("Scala book", 150)

b1.title //Scala book
b1.pages //150

val b2 = b1.copy(pages = 220)
b2.title //Scala book
b2.pages //220

b1.productArity //2
b1.productPrefix //Book
b1.productElement(0) //Scala book
b1.productElement(1) //150

b1.eq(b2) //false
b1.eq(Book("Scala book", 150)) //false
b1.equals(Book("Scala book", 150)) //true
b1 == b2 //false
b1 != b2
b1 == Book("Scala book", 150) //true
//////////////////////////////////////////////////////
// 4.15. Defining an equals Method (Object Equality)//
//////////////////////////////////////////////////////

// scala object class(AnyRef) has following method
// equal method
// hashcode
// toString
// every class in scala is being extending from AnyRef

class PersonEQ (name: String, age: Int) {
  // check that classes are of same type
  def canEqual(a: Any) = a.isInstanceOf[PersonEQ]
  // compute hash code
  override def hashCode:Int = {
    val prime = 31
    var result = 1
    result = prime * result + age;
    result = prime * result + (if (name == null) 0 else name.hashCode)
    return result
  }
  // compute equal method
  override def equals(that: Any): Boolean =
    that match {
      case that: PersonEQ => that.canEqual(this) && this.hashCode == that.hashCode
      case _ => false
    }
}

val nimoy = new PersonEQ("Leonard Nimoy", 82)
val nimoy2 = new PersonEQ("Leonard Nimoy", 82)
val shatner = new PersonEQ("William Shatner", 82)

if (nimoy.canEqual(shatner)) println ("Equal")

/////////////////////////////////////
// Inner classes
/////////////////////////////////////
class OuterClass {
  class InnerClass {
    var x = 1
  }
}

val oc1 = new OuterClass
val ic1 = new oc1.InnerClass
println(s"ic1.x = ${ic1.x}")

object OuterObject {
  class InnerClass {
    var x = 1
  }
}

class OuterClass9 {
  object InnerObject {
    val y = 2
  }
}

// new will create object which has ()
println(new OuterObject.InnerClass().x)
// object inside class
println(new OuterClass9().InnerObject.y)

/*****************************
 *  Inheritence in scala
 ***************************** */
case class Address (city: String, state: String)

case class caseSum (var a:Int,var b:Int){
  var c:Int =9
  def getSum ={a+b+c}
}

var sum = caseSum(2,3)
println (sum.getSum)

class caseExtend (var d:Int, a:Int, b:Int )
  extends caseSum(a,b){
  override def getSum: Int = {
    // how to call a super class method in scala
    super.getSum+d
  }
}

var sumExten = new caseExtend(4,2,3)
println(sumExten.getSum)

// note Address is a type case class.
// in scala all classes have been inherited from anyref
// AnyRef is the root class of all reference types
// AnyRef is translated to java Object class.
// AnyRef contain 	toString, ==, eq, hashCode.  since these are concrete methods we have to use
// override clause
// Person01 primary constructor or auxilary constructor must connect with subtype
// class primary constructor in extend clause
class Person01 (var name: String, var address: Address) {
  // toString method is defined a concrete in AnyRef so override clause is needed
  // in case method is define as abstract it does not needs override clause.
  // abstract methods exists in abstract classes
  override def toString = if (address == null) name else s"$name @ $address"
  // a auxiliary constructor must call primary constructor as first line of code
  // thats because only primary constructor can define the fields of class
  def this(name:String )= this(name , Address("discovery garden", "Dubai"))
}

class Person02 (var name: String, var address: Address) {
  // constructor defines the variable,  if the variable is not in constructor
  // parameter list it can be defined in the body of class
  var street :String =_
  override def toString = if (address == null) name else s"$name @ $address"
  // a auxiliary constructor must call primary constructor as first line of code
  // thats because only primary constructor can define the fields of class
  def this(name:String, address:Address, street:String  )= {
    this(name ,address)
    // here despite street is part of constructor its not defined by the auxilary construct
    // thats why var street:String defines the field and initialized in constructor
    this.street=street
  }
}


// here primary constructor of Employee has been connected with primary
// constructor of Person01
// in same way auxiliary constructor must be connected auxilary constructor
// note that no getter and setters were generated for name
// and address they came from super class

class Employee1 (name: String, address: Address, var age: Int)
// constructor call after extend
// call can be to primary constructor or auxilary constructor
  extends Person01 (name) {
  var phone:String =_
  // connect Employee auxiliary constructor with Person auxiliary Constructor
  def this (name: String, age:Int, phone:String)={
    this(name, Address("",""),age)
    this.phone=phone
  }
}

val teresa = new Employee1("Teresa",  25, "0(971)502736477")
teresa.name
teresa.address
teresa.age
teresa.phone

// inheritance is compiled as followings,  at compile time,  compiler create the list of
// classes,  extends or with clause links subtype class with super class.  extends also tells the
// compiler that a constructor method in this class exists which will initialize the variables
// of super class.  compile finds the class from list and looks for matching types and number
//  of arguments of constructor and then calls it. values from subtype are passed to super class
//  Note: only constructor can create variables. if your auxilary constructor contains
//  more parameters,  you have to define those parameters in class

// constructor call after extend
// call can be to primary constructor or auxilary constructor
class Employee02 (name: String, address: Address,street:String, var age: Int)
  extends Person02 (name,address,street) {
  // var and val controls the getters and setters on this variable
  var phone:String =_
  // connect Employee auxiliary constructor with Person auxiliary Constructor
  def this (name: String, address:Address,street:String, phone:String, age:Int )={
    this(name, address,street,age)
    this.phone=phone
  }
}
//In Scala, the way you express calling the super constructor is in the extends Bar(...) portion
// of your class definition

class Bar(s: String) {
  def this() = this(Bar.defaultString)
}
object Bar {
  val defaultString: String = "bar"
}


// you can store the default values in a companion object
final class Foo1(x: Int, y: Int, s: String) extends Bar(s) {
  def this(x: Int) = this(x, 1/*Foo's default*/, Bar.defaultString)
}

////////////////////////////////////////
//          summary
///////////////////////////////////////
// no getter and setter available  anil.name will not compile

//Visibility    Accessor?                  Mutator?
//var                Yes                      Yes
//val                Yes                      No
//Default visibility (no var or val) No       No
//Adding the private keyword to var or val No No

//case class constructors are val by default
// case classes are immutables
// object define as private in object class are only accessable inside the object class

// class methods can be public, protected and private.  public scope need not to be specified
// classes with objects classes are called companion objects
// classes without object are called standalone objects.
// in case class you dont need new operator to create object
// case class can have private/public/projected variable with case t (p:Int ){ s:Int }
// case class have build in extrators their auxilary constructor is defined in apply method
// when you define auxilary constructor the primary constructor should be first line
// variables and methods are being created with val, var and def
// the name of auxilary constructor is this and primary constructor is being called by this
// inheritance: if super class is defined with var or val,  the sub class does not need var/val on these parameters

/****************************************************
 Option 1: define name and address as 'var'
class Employee (var name: String, var address: Address)
  extends Person (name, address) { ... }

 Option 2: define name and address without var or val
class Employee (name: String, address: Address)
  extends Person (name, address) { ... }
Because Scala has already generated the getter and setter methods for the name and
address fields in the Person class, the solution is to declare the Employee constructor
  without var declarations:

  this is correct
class Employee (name: String, address: Address)
  extends Person (name, address) { ... }
 *********************************************************/
class PandorasBox {
  case class Thing (name: String)
  var things = new collection.mutable.ArrayBuffer[Thing]()
  things += Thing("Evil Thing #1")
  things += Thing("Evil Thing #2")
  def addThing(name: String) { things += new Thing(name) }
}
val pp = new PandorasBox
pp.things.foreach(println)