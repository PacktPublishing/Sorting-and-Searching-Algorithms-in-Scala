package com.packt.demo.section4.binarytree

import scala.collection.immutable.Queue

object Colour extends Enumeration {
  val Red, Black = Value
}

import Colour._

case class RedBlackNode[K, V](key: K, value: V,
                              colour: Colour.Value = Red,
                              left: Option[RedBlackNode[K, V]] = None,
                              right: Option[RedBlackNode[K, V]] = None) {
  def isLeftChild(parent: RedBlackNode[K, V]): Boolean = parent.left.exists(_.key == key)

  def isRightChild(parent: RedBlackNode[K, V]): Boolean = parent.right.exists(_.key == key)

  def redChild():Option[RedBlackNode[K, V]] = left.filter(_.colour == Red).orElse(right.filter(_.colour == Red))

}


class RedBlackTree[K, V](root: RedBlackNode[K, V], ord: Ordering[K]) extends BinarySearchTree[K, V] {

  private def rightRotate(parent: RedBlackNode[K, V]): RedBlackNode[K, V] = {
    val pivot = parent.left.get
    val newRightChild = parent.copy(left = pivot.right)
    pivot.copy(right = Some(newRightChild))
  }

  private def leftRotate(parent: RedBlackNode[K, V]): RedBlackNode[K, V] = {
    val pivot = parent.right.get
    val newLeftChild = parent.copy(right = pivot.left)
    pivot.copy(left = Some(newLeftChild))
  }

  override def search(key: K): Option[V] = search(root, key)

  private def search(node: RedBlackNode[K, V], key: K): Option[V] = key match {
    case node.key => Some(node.value)
    case k if ord.lt(k, node.key) => node.left.flatMap(n => search(n, k))
    case k => node.right.flatMap(n => search(n, k))
  }

  override def insert(key: K, value: V): RedBlackTree[K, V] = {
    new RedBlackTree(insert(root, key, value).copy(colour = Black), ord)
  }

  private def insert(node: RedBlackNode[K, V], key: K, v: V): RedBlackNode[K, V] = key match {
    case node.key => node.copy(value = v)
    case k if ord.lt(k, node.key) =>
      val newLeft = node.left.map(n => insert(n, k, v))
        .orElse(Some(RedBlackNode(key = key, value = v)))
      val grandP = node.copy(left = newLeft)
      redBlackFix(grandP, newLeft.get, grandP.right).getOrElse(grandP)
    case k =>
      val newRight = node.right.map(n => insert(n, k, v))
        .orElse(Some(RedBlackNode(key = key, value = v)))
      val grandP = node.copy(right = newRight)
      redBlackFix(grandP, newRight.get, grandP.left).getOrElse(grandP)
  }

  private def redBlackFix(grandP:RedBlackNode[K,V], parent:RedBlackNode[K,V],
                          uncle:Option[RedBlackNode[K,V]]):Option[RedBlackNode[K,V]] = {
    val redChildOpt = if (parent.colour == Red) parent.redChild() else None
    redChildOpt.map(child => uncle.map(_.colour).getOrElse(Black) match {
      case Red if parent.isLeftChild(grandP) =>
        val newP = parent.copy(colour = Black)
        val newU = uncle.map(_.copy(colour = Black))
        grandP.copy(colour = Red, left = Some(newP), right = newU)
      case Black if parent.isLeftChild(grandP) =>
        val rotP = if (child.isRightChild(parent)) leftRotate(parent) else parent
        val newP = rotP.copy(colour = Black)
        val newG = grandP.copy(colour = Red, left = Some(newP))
        rightRotate(newG)

      case Red if parent.isRightChild(grandP) =>
        val newP = parent.copy(colour = Black)
        val newU = uncle.map(_.copy(colour = Black))
        grandP.copy(colour = Red, right = Some(newP), left = newU)
      case Black if parent.isRightChild(grandP) =>
        val rotP = if (child.isLeftChild(parent)) rightRotate(parent) else parent
        val newP = rotP.copy(colour = Black)
        val newG = grandP.copy(colour = Red, right = Some(newP))
        leftRotate(newG)
    })
  }

  def foreachBFS(f: (K, V) => Unit): Unit = {
    val sq = Stream.iterate(Queue(root)) { q =>
      val (node, tail) = q.dequeue
      tail ++ List(node.left, node.right).flatten
    }
    sq.takeWhile(q => q.nonEmpty).foreach(q => f(q.head.key, q.head.value))
  }
}

object RedBlackTree {
  def apply[K, V](key: K, value: V)(implicit ord: Ordering[K]) =
    new RedBlackTree[K, V](RedBlackNode(key = key, value = value, colour = Black), ord)
}

object App2 {
  def main(args: Array[String]): Unit = {
    val x = RedBlackTree(1, "A")
      .insert(2, "B")
      .insert(3, "C")
      .insert(4, "D")
      .insert(5, "E")
      .insert(6, "F")
      .insert(7, "G")
      .insert(8, "H")
      .insert(9, "I")
      .insert(10, "J")

    x.foreachBFS((k, v) => println(s"$k -> $v"))
  }
}
