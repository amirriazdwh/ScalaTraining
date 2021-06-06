////////////////////////////////////////////////
//  How to pass tuples to a normal function
///////////////////////////////////////////////
def displayItem (x:Int,y:Int, z:Int) ={
  print(s"x:$x y:$y z:$z")
}

var item =(1,2,3)
// converts the method of def displayItem((x:Int,y:Int, z:Int))
(displayItem _).tupled(item)

def foo(bar1: Int, bar2: Int, bar3: Boolean = false, bar4: Int = 1) = {
  println(f"bar1=$bar1 bar2=$bar2 bar3=$bar3 bar4=$bar4")
}

val x = (1, 2, true, 9)
(foo _).tupled(x)
/////////////////////////////////////////
// Stirng* means Seq[String]
////////////////////////////////////////
def foo(os: String*) = os.toList.foreach(println)
val args = Seq("hi", "there")
foo(args:_*)
foo("hi", "there")

val colors = Map("red" -> "#FF0000", "azure" -> "#F0FFFF")

def parse(options: (String, String)*) = println (options.toMap)
parse("red"->"#FF","azure"->"#F0FFFF")
parse(colors.toSeq:_*)

def foo(names: (_, _)*) = names.foreach(println)
val folks = Map("john" -> "smith", "queen" -> "mary")
foo(folks.toSeq:_*)


val p = Map("a" -> 1, "b" -> 2, "c"->3)
def unpackMap(a:Int,b:Int, c:Int) ={ print(s"a:$a b:$b c:$c") }
unpackMap(a=1,b=2,c=3)

(unpackMap _).tupled(p.toSeq.map{case (a,b )=>b})



def timer[A](blockOfCode: => A) = {
  val startTime = System.nanoTime
  val result = blockOfCode
  val stopTime = System.nanoTime
  val delta = stopTime - startTime
  (result, delta/1000000d)
}

val (result, time) = timer(println("Hello"))
