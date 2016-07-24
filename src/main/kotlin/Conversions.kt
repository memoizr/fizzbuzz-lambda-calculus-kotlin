val toI: (λ) -> Int get() = { it(λ { x -> (1 + x.v<Int>()).λ })(0.λ).v() }
val toB: (λ) -> Boolean = { λ -> λ(true.λ)(false.λ).v() }
val toA: (λ) -> List<λ> = {
    Z(λ { recur ->
        λ { λλ ->
            λ { acc ->
                If(IsEmpty(λλ))(
                        defer { acc }
                )(
                        defer { recur(Tail(λλ))((acc.v<List<λ>>() + Head(λλ)).λ) }
                )
            }
        }
    })(it)(emptyList<λ>().λ).v()
}
val fromInt = { n: Int -> Pred ͺ (0..n).fold(Zero) { a, b -> Succ(a) } }
val fromBoolean = { bool: Boolean -> If(IsZero(fromInt((!bool).compareTo(false)))) }