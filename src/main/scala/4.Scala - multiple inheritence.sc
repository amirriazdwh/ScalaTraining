trait WithLegs {
  def legs: String = "Base"
}

trait TwoLegged extends WithLegs {
  override def legs: String = "Two -> " + super.legs
}
trait FourLegged extends WithLegs {
  override def legs: String = "Four -> " + super.legs
}
trait SixLegged extends TwoLegged {
  override def legs: String = "Six -> " + super.legs
}

//[FourLegged, WithLegs, TwoLeggged, WithLegs]
// [FourLegged, TwoLeggged, WithLegs]
class A extends TwoLegged with FourLegged {
  override def legs = "A -> " + super.legs
}

//[TwoLegged, WithLegs, FourLegged, WithLeggs]
//[TwoLegged, FourLegged, WithLeggs]
class B extends FourLegged with TwoLegged {
  override def legs = "B -> " + super.legs
}
// eliminate duplicate in left side and keep right hand side
//[TwoLegged,WithLegs, FourLegged,WithLegs, SixLegged,TwoLegged,WithLegs  ]
//[FourLegged, SixLegged,TwoLegged,WithLegs]
class C extends SixLegged with FourLegged with TwoLegged  {
  override def legs = "C -> " + super.legs
}
//[WithLegs, TwoLegged, WithLegs, SixLegged, TwoLegged, WithLegs, FourLegged, WithLegs]
//[ SixLegged, TwoLegged,  FourLegged, WithLegs]
class D extends FourLegged with SixLegged  with TwoLegged with WithLegs {
  override def legs = "D -> " + super.legs
}
// [FourLegged, WithLegs, TwoLegged, WithLegs, SixLegged, TwoLegged, WithLegs, WithLegs]
// [FourLegged,   SixLegged, TwoLegged, WithLegs]
class E extends WithLegs  with SixLegged  with TwoLegged with FourLegged {
  override def legs = "E -> " + super.legs
}
//[FourLegged, WithLegs, SixLegged, TwoLegged,WithLegs]
//[FourLegged, SixLegged, TwoLegged,WithLegs]
class F extends SixLegged with FourLegged{
  override def legs = "F -> " + super.legs
}

new A().legs
//res0: String = A -> Four -> Two -> Base
new B().legs
//res1: String = B -> Two -> Four -> Base
new C().legs
//res2: String = C -> Four -> Six -> Two -> Base
new D().legs
//res3: String = D -> Six -> Two -> Four -> Base
new E().legs
//res4: String = E -> Four -> Six -> Two -> Base
new F().legs
//res5: String = F -> Four -> Six -> Two -> Base

///////////////////////////////////////////////////////////////////////
// take the trait which is on most left hand side
// reach the base of trait
// then take the second trait which comes after that
// reach the base of that trait, if that relation is part of
// above relation, ignore it.
// in the end take the class/trait which comes after extend
//  you must remove duplicate which is on left side.
//  never remove the duplicate on right side
//  in class definition you will start with trait/class on right side
//   and then move through the heirarchy in base
// ////////////////////////////////////////////////////////////////////
