package com.packt.demo.section5.closestpair

import scala.util.Random

object ClosestDistance1D {
  val rand = Random

  def generatePoints(i: Int): List[Point] = (0 until i).map(_ => Point(rand.nextInt(Int.MaxValue), 0)).toSet.toList

  def closestDistance(ptsByX: List[Point]): Double = {
    if (ptsByX.size <= 3) closestDistanceBrute(ptsByX)
    else {
      val (left, right) = ptsByX.splitAt(ptsByX.size / 2)
      val l = right.head.x
      val delta = math.min(closestDistance(left), closestDistance(right))
      val ptsInBoundary = ptsByX.filter(p => p.x >= l - delta && p.x <= l + delta)
      val deltaInBoundary = if (ptsInBoundary.size > 1) closestDistanceBrute(ptsInBoundary) else Int.MaxValue
      Math.min(deltaInBoundary, delta)
    }
  }

  def closestDistanceBrute(pts: List[Point]): Double = {
    val distances = for ((pti,i) <- pts.zipWithIndex.dropRight(1); ptj <- pts.drop(i + 1)) yield pti.distanceTo(ptj)
    distances.min
  }


  def main(args: Array[String]): Unit = {
    val allPoints = generatePoints(3000)

    val before = System.currentTimeMillis()
    val distanceBruteForce = closestDistanceBrute(allPoints)
    val after = System.currentTimeMillis()

    val before1 = System.currentTimeMillis()
    val distance = closestDistance(allPoints.sortBy(_.x))
    val after1 = System.currentTimeMillis()

    println(s"Closest Point using Brute Force is $distanceBruteForce, found in ${after - before} ms")
    println(s"Closest Point using Divide & Conquer is $distance, found in ${after1 - before1} ms")

  }
}

