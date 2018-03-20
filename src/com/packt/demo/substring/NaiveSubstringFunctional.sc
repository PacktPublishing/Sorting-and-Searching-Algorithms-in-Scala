val myList = List(2, 2, 3, 5, 6, 7)

val optNumber = myList.find(_ == 5)

val n = optNumber.getOrElse(-1)

myList.indices.find(i => myList(i) == 5).getOrElse(-1)

val text = "Sally sells seashells on the seashore"
val pattern = "seashore"

text.indices.find(i => text(i) == 'z').getOrElse(-1)

def naiveSubstring(text: String, pattern: String): Int = {
  text.indices.find { i =>
    i + pattern.length <= text.length &&
      pattern.indices.forall(j => text(j + i) == pattern(j))
  }.getOrElse(-1)
}

naiveSubstring(text, pattern)
text.indexOf(pattern)

