package com.packt.demo.section3.hashtable

object MutableMainApp {

  def main(args: Array[String]): Unit = {
    val table: mutable.HashTable[Int, String] = new mutable.HashTableImpl[Int,String](13)

    table.insert(23423633, "Sam")
    table.insert(45871412, "Joe")
    table.insert(56784564, "Ruth")
    table.insert(98837144, "Luis")

    println(s"This should be Sam", table.search(23423633))
    println(s"This should be Joe", table.search(45871412))
    println(s"This should be Ruth", table.search(56784564))
    println(s"This should be Luis", table.search(98837144))
    println(s"This should be None", table.search(11111111))

    println(s"This should be Joe", table.delete(45871412))
    println(s"This should be None", table.search(45871412))

  }

}
