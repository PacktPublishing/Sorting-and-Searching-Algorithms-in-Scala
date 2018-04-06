package com.packt.demo.section5.closestpair

import scala.util.Random

object ClosestDistance2D {
  val rand = Random

  def generatePoints(i: Int): List[Point] = (0 until i)
    .map(_ => Point(rand.nextInt(Int.MaxValue), rand.nextInt(Int.MaxValue))).toSet.toList

  def closestDistance(ptsByX: List[Point], ptsByY: List[Point]): Double = {
    if (ptsByX.size <= 3) closestDistanceBrute(ptsByX)
    else {
      val (leftByX, rightByX) = ptsByX.splitAt(ptsByX.size / 2)
      val l = rightByX.head.x
      val (leftByY, rightByY) = ptsByY.partition(p => p.x < l)
      val delta = math.min(closestDistance(leftByX, leftByY), closestDistance(rightByX, rightByY))
      val ptsInBoundary = ptsByY.filter(p => p.x >= l - delta && p.x <= l + delta)
      Stream.iterate(ptsInBoundary)(_.tail).takeWhile(_.nonEmpty).foldLeft(delta){ (min, pts) =>
        val ptsInBox = pts.takeWhile( p => p.y <= pts.head.y + delta)
        val deltaInBox = if (ptsInBox.size > 1) closestDistanceBrute(ptsInBox) else Int.MaxValue
        Math.min(deltaInBox, min)
      }
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
    val distance = closestDistance(allPoints.sortBy(_.x), allPoints.sortBy(_.y))
    val after1 = System.currentTimeMillis()

    println(s"Closest Point using Brute Force is $distanceBruteForce, found in ${after - before} ms")
    println(s"Closest Point using Divide & Conquer is $distance, found in ${after1 - before1} ms")

  }
}

