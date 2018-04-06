val str = "Hello"

str.hashCode

val str1 = "Hello this is a long string"

str1.hashCode

str.##

3.0.hashCode()

3.0.##

val m = 13

def hash[K](key: K) = {
  val h = key.## % m
  if (h < 0) h + m else h
}

hash("Hello")

hash(1000)

