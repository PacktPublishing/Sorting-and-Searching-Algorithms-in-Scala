package com.packt.demo.section3.hashtable

object ImmutableMainApp {
  def main(args: Array[String]): Unit = {
    val table: immutable.HashTable[Int, String] = immutable.HashTableImpl(10)

    val finalTable = table.insert(23423633, "Sam")
      .insert(45871412, "Joe")
      .insert(56784564, "Ruth")
      .insert(98837144, "Luis")

    println(s"This should be Sam", finalTable.search(23423633))
    println(s"This should be Joe", finalTable.search(45871412))
    println(s"This should be Ruth", finalTable.search(56784564))
    println(s"This should be Luis", finalTable.search(98837144))
    println(s"This should be None", finalTable.search(11111111))

    val removedJoe = finalTable.delete(45871412)

    println(s"This should be None", removedJoe.search(45871412))
    println(s"This should be Joe", finalTable.search(45871412))

  }


}
