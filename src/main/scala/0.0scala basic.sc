// the reason we use yield in scala programming is yield is a pure function
// anything for () {} makes has side effect and may not return anything which is against functional programming
var dList =for (i <- List(1,2,3); y=i*i) yield (y*2)
var x3=for (x <- List(1,2,3,4,5); y=x*x) yield (y*x)


//def average = for { s <- sum[Double]; c <- count } yield s/c
//for {
//  ch <- "abcd"
//  ch2 <- List(ch.toUpper, ch)
//} yield ch2

/*
def theLongest(s: String) = {
  s.split("[0-9]")
 //   .filter(str => str.exists(ch => ch.isUpper))
 //   .maxBy(str => str.length)
}

theLongest("amir0riaz1")
*/
// find element not in second list
//case class Book(var title: String,var pages: Int)
﻿//val books = List( Book("Future of Scala developers", 85),
//  Book("Parallel algorithms", 240),
//  Book("Object Oriented Programming", 130),
//  Book("Mobile Development", 495) )
//Book(Mobile Development,495)
//books.maxBy(book => book.pages)
//Book(Future of Scala developers,85)
﻿//books.minBy(book => book.pages)

//"Amir0Riaz1good".split("[0-9]")
