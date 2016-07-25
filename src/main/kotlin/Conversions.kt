val toI: (λ) -> Int get() = { f ->
    f(
            λ { x ->
                (x.v<Int>().inc()).λ
            }
    )(
            0.λ
    ).v()
}

val toB: (λ) -> Boolean = { λ ->
    λ(true.λ)(false.λ).v()
}

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
    }
    )(it)(emptyList<λ>().λ).v()
}

val fromInt: (Int) -> λ = { n ->
    Pred ͺ (0..n).fold(Zero) { a, b -> Succ(a) }
}

val fromBoolean: (Boolean) -> λ = { bool ->
    IsZero(fromInt((!bool).compareTo(false)))
}