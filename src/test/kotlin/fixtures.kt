val one = Succ(Zero)
val two = Succ(one)
val three = Succ(two)
val five = (Plus(two))(three)
val ten = Mult(two)(five)
val fifteen = (Mult(five))(three)
val hundred = Mult(ten)(ten)