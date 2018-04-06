package com.packt.demo.section3.hashtable.mutable

trait HashTable[K, V] {

  def insert(key: K, value: V)

  def search(key: K): Option[V]

  def delete(key: K): Option[V]

}
