
implicit val hoursInADayOnEarth:Int = 24

def hoursInDay(days:Int)(implicit hrsInADay:Int) = days * hrsInADay

hoursInDay(7)

val list = List(1, 2, 3)

list ++ List(4) ++ List()

list ++ Some(4) ++ None



