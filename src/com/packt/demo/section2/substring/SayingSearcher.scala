package com.packt.demo.section2.substring

import scala.io.Source

object SayingSearcher {

  def prefixTable(pattern: String): Vector[Int] = {
    pattern.drop(1).foldLeft(0, Vector(0)) {
      case ((f, prefixT), c) =>
        val lowerF = Stream.iterate(f)(f => prefixT(f - 1))
          .find(f => f == 0 || pattern(f) == c).get
        val newF = if (pattern(lowerF) == c) lowerF + 1 else lowerF
        (newF, prefixT :+ newF)
    }._2
  }

  def substringSearch(text:String, pattern:String):Int = {
    val prefixT = prefixTable(pattern)
    text.indices.foldLeft(-1, 0) {
      case ((foundI, m), i) if foundI > 0 => (foundI, 0)
      case ((foundI, m), i) =>
        val stepsM = Stream.iterate(m)(m => prefixT(m - 1))
        val lowerM = stepsM.find(m => m == 0 || pattern(m) == text(i)).get
        val newM = if (pattern(lowerM) == text(i)) lowerM + 1 else lowerM
        if (newM == pattern.length) (i - newM + 1, 0) else (-1, newM)
    }._1
  }


  def main(args: Array[String]): Unit = {
    val text = Source.fromFile("sayings.txt").getLines().mkString

    val pattern = "True communication depends upon our being straightforward with one another. " +
      "But the best way to communicate may be just to"

    val i = substringSearch(text, pattern)

    println(s"Index found at $i")

    println(text.substring(i, i + 183))
  }

}
