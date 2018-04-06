def bubbleSort(numbers: Array[Int]) = {
  for (k <- 1 until numbers.length; j <- 0 until numbers.length - 1) {
    if (numbers(j) > numbers(j + 1)) {
      val x = numbers(j)
      numbers(j) = numbers(j + 1)
      numbers(j + 1) = x
    }
  }
}

def bubbleSort2(numbers: Array[Int]) = {
  for (k <- 1 until numbers.length; j <- 0 until numbers.length - 1 if numbers(j) > numbers(j+1)) {
    (numbers(j), numbers(j + 1)) match {
      case (x, y) =>
        numbers(j) = y
        numbers(j + 1) = x
    }
  }
}

def bubbleSort3(numbers: Array[Int]) = {
  for (k <- 1 until numbers.length; j <- 0 until numbers.length - k
       if numbers(j) > numbers(j+1)) {
    val x = numbers(j)
    numbers(j) = numbers(j + 1)
    numbers(j + 1) = x
  }
}

val array1 = Array(3, 6, 1, 5, 2, 0, 8)
bubbleSort3(array1)
array1

val array2 = Array(4, 6, 22, 56, 11, 55, 223, 1, 7, 33, 9, 10, 67, 88, 2, 5, 6, 9, 213, 6, 3)
bubbleSort3(array2)
array2
