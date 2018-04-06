def prefixTable(pattern: String): Vector[Int] = {
  pattern.drop(1).foldLeft(0, Vector(0)) {
    case ((f, prefixT), c) =>
      val lowerF = Stream.iterate(f)(f => prefixT(f - 1))
        .find(f => f == 0 || pattern(f) == c).get
      val newF = if (pattern(lowerF) == c) lowerF + 1 else lowerF
      (newF, prefixT :+ newF)
  }._2
}

prefixTable("odoodledo")
val answer01 = List(0, 0, 1, 1, 2, 0, 0, 0, 1)

prefixTable("acacabacacabacacac")
val answer02 = List(0, 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 4)
