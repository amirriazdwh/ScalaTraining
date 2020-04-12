//if _ is after a method,  its performing conversion of method to a function
// if (_) means derived the data types from the function call arguments
// _=> means a function who apply method input parameter can take argument of type derived from call
// * means zero or many expressions
// case _ => means take some type as arugment and it will match all the times

import scala._    // Wild card -- all of Scala is imported
import scala.{ Predef => _, _ } // Exception, everything except Predef
def f[M[_]]       // Higher kinded type parameter
def f(m: M[_])    // Existential type
_ + _             // Anonymous function placeholder parameter
m _               // Eta expansion of method into method value
m(_)              // Partial function application
_ => 5            // Discarded parameter
case _ =>         // Wild card pattern -- matches anything
val (a, _) = (1, 2) // same thing
for (_ <- 1 to 10)  // same thing
  f(xs: _*)         // Sequence xs is passed as multiple parameters to f(ys: T*) its using regular expression * means zero and many
                     // _ means datatypes as define in input call
case Seq(xs @ _*) // Identifier xs is bound to the whole matched sequence
var i: Int = _    // Initialization to the default value
def abc_<>!       // An underscore must separate alphanumerics from symbols on identifiers
t._2              // Part of a method name, such as tuple getters
