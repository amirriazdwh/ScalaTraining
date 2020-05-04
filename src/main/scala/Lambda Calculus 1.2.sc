//////////////////////////////////
//  What is a case class and its methods
/////////////////////////////////
//  A case class is called name tuple.   case class Tuple2[A, B](_1: A, _2: B)
//  A case class is used to define a part of disjoint set.  if C is a set and A and B are joint.  then A and B
//  will be selected under certain conditions.
//  The A part is defined as  Case class A(x:Type) and B is defined as case Case class B(x:Type) where A and B extend C
//  A and B can be disjoint based on equivalence or logic.  Thats why they are called cases because each set has its own case environment
// -------------------------------------------------------------------------------
//  equivalence means {x. x belong to R such the x =A where A is some constant}
//  logic means       {x, x:R such that x> or <= A where is some constant}
// --------------------------------------------------------------------------------
//  A case class parameters are immutable.  which ensure that methods inside the case class are deterministic or pure.
//  that is why case class parameter are defined with val by default
//
//  case class name (f1:Type, f2:Type) is equivalent to case class anme(val f1:Type, val f2:Type)
//
//  A case class is part of object orient paradigm as well as Function programming.  while a simple class is just part
//  object orient paradigm. The reason encapsulation is used in object orient programming is to decrease the side effects
//  just like in closure we eliminate the side effects by encapsulating the variable inside the function.
//
case class personCase ( var id:Int,
                        var name:String,
                        var Address:String,
                        var age:Int){
  def incrId = {this.id=this.id+1}
}

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
def findid(x:personCase):Option[Int] ={
  x match {
    case personCase(1001, _,_,_) => Some(x.id)
    case personCase(1002,_,_,_) =>Some( x.id)
    case i:personCase => Some( i.id)
    case _ => None
  }
}

print("the value is "+findid(p))
// a case class with parameter defined as var
// can contains pure as well as impure function. that is why
// a case class can be part of functional programming as
// well as object orient programming as well
p.incrId
print("the value is "+findid(p))

List
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//////////////////////////////////////////////////////////
// A common definition of function between two sets (or between two classes, when working in GBN) is based on the notion of
// "ordered pair". An ordered pair is some set-theoretic construction, denoted "(a,b)" where a and b are sets, with the property
// that (a,b)=(c,d) if and only if a=c and b=d. There are many ways of achieving this; the most common is the Kuratowski
// definition of ordered pair:
//
//  (a,b)  is called tuple
//  (a,_)  is called singleton
//   ()    is called unit
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Disjunction in types
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//  it means that function types are different in a function.
//
//  for example Option is a base type with of two disjunction types Some and None.   it means Either (Some , None)
//  Means Option class give program an option to choose either Some or None but not the both.
//  the matching will be done as  Option match {case Some => Some(value), Case None}