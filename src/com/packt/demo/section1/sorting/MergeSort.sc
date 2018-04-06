def merge(left:List[Int], right:List[Int]): List[Int] = {
  val output = (0 until left.length + right.length).foldLeft(List[Int](), left, right) { (triple, _) =>
    val (merged, leftRemaining, rightRemaining) = triple
    (leftRemaining, rightRemaining) match {
      case (Nil, r :: rTail) => (r :: merged, Nil, rTail)
      case (l :: lTail, Nil) => (l :: merged, lTail, Nil)
      case (l :: lTail, r :: rTail) if l < r => (l :: merged, lTail, rightRemaining)
      case (l :: lTail, r :: rTail) => (r :: merged, leftRemaining, rTail)
    }
  }
  output._1.reverse
}

def mergeSort(input: IndexedSeq[Int]): List[Int] = {
  if(input.length == 1) List(input.head)
  else {
    val (left, right) = input.splitAt(input.length / 2)
    val sortedLeft = mergeSort(left)
    val sortedRight = mergeSort(right)
    merge(sortedLeft, sortedRight)
  }
}

val vector1 = Vector(3, 6, 1, 5, 2, 0, 8)
mergeSort(vector1)

val vector2 = Vector(4,6,22,56,11,55,223,1,7,33,9,10,67,88,2,5,6,9,213,6,3)
mergeSort(vector2)
