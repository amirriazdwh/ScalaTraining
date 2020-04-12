
//Method def even evaluates on call and creates new function every time (new instance of Function1).

def even1: Int => Boolean = _ % 2 == 0
// even eq even
//Boolean = false

val even2: Int => Boolean = _ % 2 == 0
//even eq even
//Boolean = true
//With def you can get new function on every call:

val test1: () => Int = {
  val r = util.Random.nextInt
  () => r
}

test1()
// Int = -1049057402
test1()
// Int = -1049057402 - same result

def test2: () => Int = {
  val r = util.Random.nextInt
  () => r
}

test2()
// Int = -240885810
test2()
// Int = -1002157461 - new result

//val evaluates when defined, def - when called:

//val even3: Int => Boolean = ???
//scala.NotImplementedError: an implementation is missing

//def even4: Int => Boolean = ???
//even: Int => Boolean

//even3
///scala.NotImplementedError: an implementation is missing
// Note that there is a third option: lazy val.
// It evaluates when called the first time:

//lazy val even5: Int => Boolean = ???
//even: Int => Boolean = <lazy>

//even5

//scala.NotImplementedError: an implementation is missing
// But returns the same result (in this case same instance of FunctionN) every time:

  lazy val even: Int => Boolean = _ % 2 == 0
//  even eq even
  //Boolean = true

  lazy val test: () => Int = {
  val r = util.Random.nextInt
  () => r
  }

  test()
  // Int = -1068569869
  test()
  // Int = -1068569869 - same result
//  Performance
//val evaluates when defined.
//def evaluates on every call, so performance could be worse than val for multiple calls. You'll get the same
// performance with a
// single call. And with no calls you'll get no overhead from def, so you can define it even if you will not use it in some branches.
//With a lazy val you'll get a lazy evaluation: you can define it even if you will not use it in some branches, and
// it evaluates once or never, but you'll get a little overhead from double check locking on every access to your lazy val.
//As @SargeBorsch noted you could define method, and this is the fastest option:
def even(i: Int): Boolean = i % 2 == 0
//But if you need a function (not method) for function composition or
// for higher order functions (like filter(even)) compiler will generate a function from your method every time
// you are using it as function, so performance could be slightly worse than with val

val even100: (Int => Boolean) = {
  println("val");
  (x => x % 2 == 0)
}

println( if (even100(1)) "******Even*****" else "******Odd******")
println( if (even100(1)) "******Even*****" else "******Odd******")

def even101: (Int => Boolean) = {
  println("def");
  (x => x % 2 == 0)
}

println( if (even101(1)) "******Even*****" else "******Odd******")
println( if (even101(1)) "******Even*****" else "******Odd******")