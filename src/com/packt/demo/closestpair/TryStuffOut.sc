val list = List('A', 'B', 'C', 'D')

val listWtIndex = list.zipWithIndex

listWtIndex.foreach{ case (c, i) =>
    println(s"$c, $i")
}

list.drop(1)

list.dropRight(2)

val list2 = List('a', 'B', 'c', 'D')

val (lower, upper) = list2.partition(_.isLower)

Stream.iterate(list)(l => l.tail).takeWhile(_.nonEmpty).toList
