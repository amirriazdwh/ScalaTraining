// Note : object classname {} define a standalone object, if a class exists in same file with same name then
//        its called companion object.  classname is an independent object created by object classname command
//        you can define different name for object and still get the singleton object created see example below
// Note :case key word means that object contains packing and unpacking methods,  its contains apply and unapply method,
//       they are factory method which creates sington object with same name ,  they are called class companion classes
//        you cannot override the unapply method.  if you have to override unapply method use regular class
// Note:  a regular class with apply and unapply method will behave the same way as case class, you can pack and unpack
//        objects,  case keyword just generates the code automatically for you, if you can omit the {} for regular class
// Note:  a case is a regular class in all other aspects,  you can define methods, variable and auxilary contracutor,
//        case key word just generates the companion object,  apply and unapply method
// Note: scala classes have public visibity in package by default and all the scala class by default are inherited from "object" class which contain toString
// Note: all case classes are immutables ,means they have getter but no setters even if you dont specify val or var,
//       while normal classes has no setter/getter if var and val is not specified in their constructor
// Note: a scala companion object or class is a singleton object with same name as scala class and must be in same file
//       in which the scala class has been defined. the companion object is created at class loading time
// Note: val var and function evaluates when defined, def evaluate - when called,  so all var or val are executed once an object from a
//        class is created while def statments are kept for calling, please see the Person class
// Note : you dont need var/val to define variable inside () for loops, however, you can add var/val for getter/setters in () for classes
//        while you need var/val to define variable inside {}
// Note : anywhere where you dont specify access modified your access is public
// Note : there is only one primary constructor which is the class body, which contians () and {} so you can initialize, add access modeifies, val/var
// Note : auxulery constructor is defined by name  this and its first line should always be primary constructors call
// Note : companion class can call their private member of the class.  therefore they need to be in same file in which class is created
// Note:  if variable scope is private[this], it can only be accessed in its own class,  if private it can only be access in companion object
//        if protected it can be accessed in derived class while public scope can be accessed anywhere.
// Note :  // class methods can be public, protected and private.  public scope need not to be specified

class person_reg(firsname:String, lastName :String)
// all case classes are immutable,  just like all the other data types in scala.
case class person_imt ( firstname :String, lastname : String)
var pimt = new person_imt("annie","amir")
// this will not work as default access for firstname is val which only generates getter
//pimt.firstname=""
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// primary constructor is the whole Person class. var means both getter and setter available
// in a class () and {} are part of primary constructor. you dont need var to define variable inside () while you need var/val
// to define variable inside {}, in regular class if you dont define constructor with val/var no getter/setter generated
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Person(var firstName: String="", var lastName: String ="" /*default initialization*/) {
  println("the constructor begins")
  // some class fields. a val is executed at definition time
  private val HOME = System.getProperty("user.home")
  // age is a field whose getter and setter will be generated automatically
  var age = 0
  // def method are executed on call.
  override def toString = s"$firstName $lastName is $age years old"
  def printHome { println(s"HOME = $HOME") }
  // this is an pointer to object.  print cannot print it so it uses tostring method to print it.  this.tostring
  def printFullName { println(this) } // uses toString
   printHome
   printFullName
  println("still in the constructor")
}
val p0 = new Person("Adam", "Meyer")
p0.firstName="Sr. Adam"
p0.printFullName
// setter for age
// following method is generated age_$eq and called as p0.age_$eq(40)
// age_$eq is sugar coated sytax for p0.age=40
p0.age=40
// above and below methods are same.  the below one is the translated method in java
// please note the in scala $eq has values = so it become p0.age_=
p0.age_$eq(40)

// getter for age
println(p0.age)
println(p0.toString)
////////////////////////////////////////////////////
// case classes provides the following methods
// apply, unapply, =, hashcode, copy
// apply and unapply method exists in object class
// Note: case class constructors are val by defaults
// primary constructor of case class is similar to that of regular class
////////////////////////////////////////////////////

case class personCase ( var id:Int,
                    var name:String,
                    var Address:String,
                    var age:Int)


var p = personCase(1001, "Amir Riaz", "discovery", 20)
println(p.name + " "+ p.Address+" "+p.age.toString)
p.name ="Kashif Riaz"
println(p.name + " "+ p.Address+" "+p.age.toString)
val personCase(id , _, _,_) = p
var id01 = personCase.unapply(p)
// this is equivalent to  var id =personCase.unapply(p)
// pattern in unapply is matched with personCase and id and id is returned
// a sealed class generates all the possible patterns and in case a few
// patterns missing gives warning message.
// you can ignore that message with case _ => None
println(id)
println(id01.get)

// class extractors
val tuple = ('a', 1)
val (char, digit) = tuple
////////////////////////////////////////////////////////////////////////////////////////////
// Utility class. class may not needed. an object without class is called stand alone object
////////////////////////////////////////////////////////////////////////////////////////////
object FileUtils {
  def readFile (filename:String ): String ={
    ""
  }
  def writeToFile(filename:String, contents:String ): Unit ={

  }
}

val contents = FileUtils.readFile("input.txt")
FileUtils.writeToFile("output.txt",contents)

////////////////////////////////////////////////////////////////////////////////////////////////////////////
// How object works.  in scala all object are extened from a trait which contains apply and unapply method
// when the object is called without new operator compiler adds apply method to it
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
object addOne extends Function1[Int, Int] {
  def apply(m: Int): Int = m + 1
}
println(addOne(1))
println(addOne.apply(1))
  /////////////////////////////////////////////////
// auxilary constructor for regular class
////////////////////////////////////////////////
// primary constructor
class Pizza01 (var crustSize: Int, var crustType: String) {
  var price =0
  // one-arg auxiliary constructor
  def this(crustSize: Int) {
    this(crustSize, Pizza01.DEFAULT_CRUST_TYPE)
  }
  // one-arg auxiliary constructor
  def this(crustType: String) {
    this(Pizza01.DEFAULT_CRUST_SIZE, crustType)
  }
  // zero-arg auxiliary constructor
  def this() {
    this(Pizza01.DEFAULT_CRUST_SIZE, Pizza01.DEFAULT_CRUST_TYPE)
  }
  //
  def this(crustSize: Int,crustType: String, price:Int){
     this(crustSize, crustType)
     this.price=price
  }
  override def toString = s"A $crustSize inch pizza with a $crustType crust"
}
// companion creates a sington object,  so DEFAULT_CRUST_SIZE is a variable with getter
object Pizza01 {
  val DEFAULT_CRUST_SIZE = 12
  val DEFAULT_CRUST_TYPE = "THIN"
}

val p11 = new Pizza01(Pizza01.DEFAULT_CRUST_SIZE, Pizza01.DEFAULT_CRUST_TYPE)
val p21 = new Pizza01(Pizza01.DEFAULT_CRUST_SIZE)
val p31 = new Pizza01(Pizza01.DEFAULT_CRUST_TYPE)
val p41 = new Pizza01

////////////////////////////////////////////////////////
//Generating auxiliary constructors for case classes
////////////////////////////////////////////////////////
case class People (var name: String, var age: Int)

// apply is like a static method in with static class here
object People {
  def apply():People = new People("<no name>", 0)
  def apply(name: String) = new People(name, 0)
}

val a = People() // corresponds to apply()
val b = People("Pam") // corresponds to apply(name: String)
val c = People("William Shatner", 82)

// behind the scenes, the Scala compiler converts it into this:
// val p = People.apply("William Shatner", 82)

println(a)
println(b)
println(c)
// verify the setter methods work
a.name = "Leonard Nimoy"
a.age = 82
println(a)

// case keyword generate apply and unapply method,  i
// if you define auxilary contractor dont use case as you cannot
// override the unapply method.

case class Person_case (fname:String, age:Int){
  var lname:String =""
  def this(fname:String, age:Int, lname:String){
    this(fname,age);
    this.lname=lname
  }
}
// in case you dont define the apply method in companion object you must use new operator to create object
object Person_case {
  //def apply(fname: String, age: Int): Person_case = new Person_case(fname, age)
  def apply(fname: String, age: Int, lname:String): Person_case = new Person_case(fname, age, lname )

  //def unapply(obj: Person_case): Option[(String, Int, String)] = Some(obj.fname, obj.age, obj.lname)
}
// this is only method through which you can override the unapply method
object Person_case_ {
  //def apply(fname: String, age: Int): Person_case = new Person_case(fname, age)
  //def apply(fname: String, age: Int, lname:String): Person_case = new Person_case(fname, age, lname )

  def unapply(obj: Person_case): Option[(String, Int, String)] = Some(obj.fname, obj.age, obj.lname)
}
var personcase = Person_case("Hello",76,"Word")
var Person_case_(fname, _,lname)=personcase
/////////////////////////////////////////////////////////////////////////////////////////
// val , var generates getters and setters
// default is public,  means you access getters and setters everywhere
// private lets you access getters/setters in companion class,
// protected lets you access getters/setters in derived class
// public lets you access getters/setters everywhere
////////////////////////////////////////////////////////////////////////////////////////

class animal (private var name :String, /*public*/ var sex:String, protected var age:Int){
  println(s"name of animal is : $name $sex")
}
val animals = new animal("goat", "Male", 3)
//println(animals.name) name generates getter and setters but their access is
// just in object companion due to private keyword
println(animals.sex)  // here we can access the var through public keyword
//println(animals.age) not access in public only accessable in derived class

////////// private keyword cannot applies to case class//////////////////
case class Human( name: String)
val human = Human("Dale Cooper")
println(human.name)
// heman.name ="Amir Riaz"
///////////////////////////////////////////////////////////////////////////////////////////
// define private primary constructor when you dont want to create an object instance
// when you want to create a singleton object, private constructor can only be accessed on
//  companion object
////////////////////////////////////////////////////////////////////////////////////////////
class Brain private {
  override def toString = "This is the brain."
  def printBrain { println(" The brain")}
}
// object with class defination is called companion object
object Brain {
  val brain = new Brain
  def getInstance = brain
}

Brain.brain.printBrain

// this won't compile
// val brain = new Brain


//////////////////////////////////////////////////////////////////////
//  Providing Default Values for Constructor Parameters
/////////////////////////////////////////////////////////////////////
class Socket (var timeout:Int =1000)
var sock = new Socket()
println(sock.timeout)
//
val sock0 = new Socket(5000)
println(sock0.timeout)

class SocketM(val timeout: Int = 1000, val linger: Int = 2000) {
  override def toString = s"timeout: $timeout, linger: $linger"
}

// here it seems like we have multiple constructor but we are just providing default values
var sockm = new SocketM
println(sockm.toString)
var sockm1 = new SocketM(3000)
println(sockm1.toString)
var sockm2 = new SocketM(3000, 4000)
println(sockm2.toString)

///////////////////////////////////////////////////////
// companion object can access the private members of a class
// the private scope will not be accessable in standalone object as show below
///////////////////////////////////////////////////////

class Foo {
  private val secret:Int =4
  def sum() ={secret+1}
}
// its a companion object since the name of object is same as its class
object Foo {
  def objcount(foo:Foo) ={
    // Foo object secret is defined as private val.  so no accessor or mutator available but
    // we are able to access the secret. the reason is static is part of class as in java
    foo.secret+2
  }
}
// the private scope is not visiable in standalone object
object ZooFoo{
  def testSecret(foo:Foo)={
   // foo.secret+2  here
      foo.sum()
  }
}

var foo1 = new Foo {
  override def sum(): Int = super.sum()
}

println(foo1.sum())
/////////////////////////////////////////////////////////////////////////////////////////////////////////
// private[this] mean that variable scope is only in its class only,  its not access in companion class,
// object or inherited class or anywhere else,  its not most limited scope in scala
////////////////////////////////////////////////////////////////////////////////////////////////////////
class Foo2 {
  private val fool :Int = 10
  private[this] val secret:Int =4
  def showSecret (foo:Foo2)= fool> this.secret
}

object Foo2 {
  def objcount(foo:Foo2) ={
    // Foo object secret is defined as private val.  so no accessor or mutator available but
    // we are able to access the secret in class but not in object. the reason is static is part of class as in java
    // foo.secret is not accessable here since here the variable is not access in companion class
    print(foo.fool) // defined as private so accessable
  }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////
// in same way, here we defined a static method in F2 and access it in class
// companion objects are create at load time so you can acess their members in class
// since these objects are created before class objects compile or their object are being created
/////////////////////////////////////////////////////////////////////////////////////////////////
class F2 {
  def printobj: Unit = {
    println(s" I can see ${F2.obj}")
  }
}
object F2{
  private val obj ="Foo's object"
}

class FooUnLazy {
  val text =
    io.Source.fromFile("/etc/passwd").getLines.foreach(println)
}
object Test01 extends App {
  val f = new FooUnLazy
}

// with lazy keyword we force the variable to get initialized when we access it.
class FooLazy01 {
  lazy val text =
    io.Source.fromFile("/etc/passwd").getLines.foreach(println)
}
object Test extends App {
  val f = new FooLazy01
}
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

//////////////////////////////////////////////////////////
//auxiliary constructor
/////////////////////////////////////////////////////////
// In Scala, every auxiliary constructor must invoke another
// constructor of the same class as its first action. The net effect
// of this rule is that every constructor invocation in Scala will
// end up eventually calling the primary constructor of the class.
// The primary constructor is thus the single point of entry of a
// class.

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
/// case classes automatically generated method
// copy, hashcode, apply, unapply, equal
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

////////////////////////////////////////
//          summary
///////////////////////////////////////
//Visibility    Accessor?                  Mutator?
//var                Yes                      Yes
//val                Yes                      No
//Default visibility (no var or val) No       No
//Adding the private keyword to var or val No No
/////////////////////////////////////////////////////
class PandorasBox {
  case class Thing (name: String)
  var things = new collection.mutable.ArrayBuffer[Thing]()
  things += Thing("Evil Thing #1")
  things += Thing("Evil Thing #2")
  def addThing(name: String) { things += new Thing(name) }
}
val pp = new PandorasBox
pp.things.foreach(println)