package com.packt.demo.section3.hashtable.immutable

trait HashTable[K, V] {

  def insert(key: K, value: V): HashTable[K, V]

  def search(key: K): Option[V]

  def delete(key: K): HashTable[K, V]

}
