package com.packt.demo.section3.hashtable.mutable

class HashTableImpl[K, V](initialSize: Int) extends HashTable[K, V] {

  private val hashArray = Array.fill(initialSize)(List[(K,V)]())

  def hash[K](key: K) = {
    val h = key.## % initialSize
    if (h < 0) h + initialSize else h
  }

  override def insert(key: K, value: V): Unit = {
    val list = hashArray(hash(key))
    hashArray(hash(key)) = (key, value) +: list.filter(t => t._1 != key)
  }

  override def search(key: K): Option[V] = {
    val list = hashArray(hash(key))
    list.find(t => t._1 == key).map(t => t._2)
  }

  override def delete(key: K): Option[V] = {
    val list = hashArray(hash(key))
    hashArray(hash(key)) = list.filter(t => t._1 != key)
    list.find(t => t._1 == key).map(t => t._2)
  }
}
