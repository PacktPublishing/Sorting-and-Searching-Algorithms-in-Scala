val str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
val pattern = "tempor incididunt"

str.charAt(2)
str(2)
str.foreach(c => print(s"$c "))
str.map(c => if (c == 's') 'S' else c)
str.count(_ == 's')

def substringSearch(text: String, pattern: String): Int = {
  var index = -1
  for (i <- 0 to text.length - pattern.length if index == -1) {
    var j = 0
    while (j < pattern.length && text(i + j) == pattern(j))
      j += 1
    if (j == pattern.length) index = i
  }
  index
}

substringSearch(str, pattern)
str.indexOf(pattern)
