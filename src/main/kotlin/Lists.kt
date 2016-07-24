val Pair = λ { x -> λ { y -> λ { f -> f(x)(y) } } }
val Left = λ { p -> p(λ { x -> λ { y -> x } }) }
val Right = λ { p -> p(λ { x -> λ { y -> y } }) }
val Empty = Pair(True)(False)
val IsEmpty = Left
val Cons = λ { l -> λ { x -> Pair(False)(Pair(x)(l)) } }
val Head = λ { l -> Left(Right(l)) }
val Tail = λ { l -> Right(Right(l)) }
val Range =
        Z(λ { recur ->
            λ { from ->
                λ { to ->
                    If(Leq(from)(to))(
                            λ { x -> Cons(recur(Succ(from))(to))(from)(x) }
                    )(
                            Empty
                    )
                }
            }
        })
val Fold = Z(λ { f -> λ { l -> λ { x -> λ { g -> If(IsEmpty ͺ l) ͺ x ͺ λ { y -> g(f(Tail ͺ l) ͺ x ͺ g)(Head ͺ l) ͺ y } } } } })
val Map = λ { k -> λ { f -> Fold(k)(Empty)(λ { l -> λ { x -> Cons(l)(f(x)) } }) } }
val Div = Z(λ { f -> λ { m -> λ { n -> If(Leq(n)(m))(λ { x -> Succ(f(Sub(m)(n))(n))(x) })(Zero) } } })
val Push = λ { l -> λ { x -> Fold(l)(Cons(Empty)(x))(Cons) } }