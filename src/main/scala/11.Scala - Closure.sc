//The term closure comes from the fact that a piece of code (block, function) can have free variables that are
//closed (i.e. bound to a value) by the environment in which the block of code is defined.

//Take for example the Scala function definition:

def addConstant(v: Int): Int = v + k
//In the function body there are two names (variables) v and k indicating two integer values. The name v is bound because
//it is declared as an argument of the function addConstant (by looking at the function declaration we know that v will
//be assigned a value when the function is invoked). The name k is free wrt the function addConstant because the function
// contains no clue as to what value k is bound to (and how).

//In order to evaluate a call like:

val n = addConstant(10)
// we have to assign k a value, which can only happen if the name k is defined in the context in which addConstant is defined.
// For example:

def increaseAll(values: List[Int]): List[Int] =
{
  val k = 2

  def addConstant(v: Int): Int = v + k

  values.map(addConstant)
}

// Now that we have defined addConstant in a context where k is defined, addConstant has become a closure because all its
// free variables are now closed (bound to a value): addConstant can be invoked and passed around as if it were a function. Note
// the the free variable k is bound to a value when the closure is defined, whereas the argument variable v is bound when the
// closure is invoked.
// So a closure is basically a function or code block that can access non-local values through its free variables after these
// have been bound by the context.

// In many languages, if you use a closure only once you can make it anonymous, e.g.

def increaseAll(values: List[Int]): List[Int] =
{
  val k = 2

  values.map(v => v + k)
}

// Note that a function with no free variables is a special case of a closure (with an empty set of free variables).
// Analogously, an anonymous function is a special case of an anonymous closure, i.e. an anonymous function is an anonymous
// closure with no free variables.

