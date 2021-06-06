//  if _ is after a method,  its performing conversion of method to a function
//  if (_) means derived the data types from the function call arguments
//  _=> means a function who apply method input parameter can take argument of type derived from call
//  * means zero or many expressions
//  case _ => means take some type as arugment and it will match all the times

// The _ (underscore) means that the digits after S must be followed immediately by only an underscore character
// in the zone name. In this example, the zone naming convention uses the underscore to separate the zone name
// from the host name.

// We would like to use the underscore syntax _ to stand for an anonymous type parameter, aligning it with its
// meaning in value parameter lists. So, just as f(_) is a shorthand for the lambda x => f(x),
// in the future C[_] will be a shorthand for the type lambda [X] =>> C[X]. This makes higher-kinded
// types easier to use. It also removes the wart that, used as a type parameter, F[_] means F is a type
// constructor whereas used as a type, F[_] means it is a wildcard (i.e. existential) type. In the future,
// F[_] will mean the same thing, no matter where it is used.

// NOTE:  _ means anonymous. means it has no name,   which can be type, value, function, argument, packages, class,
//          to understand the meaning of _ we must look into the section where its been defined.
//          if the _ is in variable section,  its anonymous variable.    if its in function arugment its
//          says argument name is unknown. if its in type place,  we say type is anonymous.
//          if its method _ means method is anonymous.  _*  means sequence iterator whose elements can be of any type
// NOTE:  _ means sequence,  in mathematics means zero or more elements

import scala._                    // Wild card -- all of Scala is imported
import scala.{ Predef => _, _ }   // Exception, everything except Predef
def f[M[_]]                       // Higher kinded type parameter
def f(m: M[_])                    // Existential type
_ + _                             // Anonymous function placeholder parameter
m _                               // Eta expansion of method into method value
m(_)                              // Partial function application
_ => 5                            // Discarded parameter
case _ =>                         // Wild card pattern -- matches anything
val (a, _) = (1, 2)               // same thing
for (_ <- 1 to 10)                // same thing
  f(xs: _*)                       // Sequence xs is passed as multiple parameters to f(ys: T*) its using regular
                                  // expression * means zero and many and _ means derive the type from Higher Order Functions
                                  // function
                                  // _ means datatypes as define in input call
case Seq(xs @ _*)                 // Identifier xs is bound to the whole matched sequence
var i: Int = _                    // Initialization to the default value
def abc_<>!                       // An underscore must separate alphanumerics from symbols on identifiers
t._2                              // Part of a method name, such as tuple getters
