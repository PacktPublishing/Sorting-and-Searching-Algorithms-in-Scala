package com.packt.demo.section4.binarytree

import scala.collection.immutable.Queue

case class BinaryNode[K, V](key: K, value: V, left: Option[BinaryNode[K, V]] = None, right: Option[BinaryNode[K, V]] = None)

class UnBalancedBinaryTree[K, V](root: BinaryNode[K, V], ord: Ordering[K]) extends BinarySearchTree[K, V] {
  override def search(key: K): Option[V] = search(key, root)

  private def search(key: K, node: BinaryNode[K, V]): Option[V] = key match {
    case node.key => Some(node.value)
    case k if ord.lt(k, node.key) => node.left.flatMap(n => search(k, n))
    case k => node.right.flatMap(n => search(k, n))
  }

  private def insert(key: K, v: V, node: BinaryNode[K, V]): BinaryNode[K, V] = key match {
    case node.key => node.copy(value = v)
    case k if ord.lt(k, node.key) =>
      val newLeft = node.left.map(n => insert(k, v, n)).orElse(Some(BinaryNode(k, v)))
      node.copy(left = newLeft)
    case k =>
      val newRight = node.right.map(n => insert(k, v, n)).orElse(Some(BinaryNode(k, v)))
      node.copy(right = newRight)
  }

  override def insert(key: K, value: V): UnBalancedBinaryTree[K, V] = new UnBalancedBinaryTree(insert(key, value, root), ord)

  def foreachDFS(f:(K,V) => Unit): Unit = foreachDFS(f, root)

  private def foreachDFS(f:(K,V) => Unit, node: BinaryNode[K, V]): Unit = {
    node.left.foreach(n => foreachDFS(f, n))
    f(node.key, node.value)
    node.right.foreach(n => foreachDFS(f, n))
  }

  def foreachBFS(f:(K,V) => Unit): Unit = {
    val sq = Stream.iterate(Queue(root)) {q =>
      val (node, tail) = q.dequeue
      tail ++ node.left ++ node.right
    }
    sq.takeWhile(q => q.nonEmpty).foreach(q => f(q.head.key, q.head.value))
  }
}

object UnBalancedBinaryTree {
  def apply[K, V](key: K, value: V)(implicit ord: Ordering[K]) = new UnBalancedBinaryTree(BinaryNode(key, value), ord)
}

object App {
  def main(args: Array[String]): Unit = {
    val tree = UnBalancedBinaryTree(10, "James")
      .insert(5, "Isabel")
      .insert(15, "Ruth")
      .insert(25, "Judith")

    println(tree.search(10))
    println(tree.search(5))
    println(tree.search(15))
    println(tree.search(25))
    println(tree.search(100))

    tree.foreachDFS((k, v) => println(s"$k $v"))
    println()
    tree.foreachBFS((k, v) => println(s"$k $v"))
  }
}