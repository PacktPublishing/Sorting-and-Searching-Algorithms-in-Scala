package com.packt.demo.section3.hashtable.immutable

protected class HashTableImpl[K,V](hashVector: Vector[List[(K,V)]]) extends HashTable[K,V] {

  private val initialSize = hashVector.size

  def hash[K](key: K) = {
    val h = key.## % initialSize
    if (h < 0) h + initialSize else h
  }

  override def insert(key: K, value: V): HashTable[K, V] = {
    val i = hash(key)
    val list = hashVector(i)
    val newList = (key, value) +: list.filter(_._1 != key)
    new HashTableImpl[K, V](hashVector.updated(i, newList))
  }

  override def search(key: K): Option[V] = {
    val list = hashVector(hash(key))
    list.find(t => t._1 == key).map(t => t._2)
  }

  override def delete(key: K): HashTable[K, V] = {
    val i = hash(key)
    val list = hashVector(i)
    val newList = list.filter(_._1 != key)
    new HashTableImpl[K, V](hashVector.updated(i, newList))
  }
}

object HashTableImpl {
  def apply[K,V](initialSize:Int) = {
    val hashVector = Vector.fill(initialSize)(List())
    new HashTableImpl[K,V](hashVector)
  }
}
