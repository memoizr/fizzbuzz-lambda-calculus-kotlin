
val Zero = λ { f -> λ { x ->
    x
}}

val Identity = λ { x -> x }

val Succ = λ { n -> λ { f -> λ { x ->
    f(n ͺ f ͺ x)
}}}

val Pred = λ { number -> λ { f -> λ { x ->
    number(
            λ { g -> λ { h ->
                h(g(f))
            }}
    )(
            λ { u ->
                x
            }
    )(
            Identity
    )
}}}

val Plus = λ { m -> λ { n ->
    m(Succ)(n)
}}

val Sub = λ { m -> λ { n ->
    n(Pred)(m)
}}

val Mult = λ { m -> λ { n -> λ { f ->
    m(n(f))
}}}

val Pow = λ { base -> λ { exponent ->
    exponent(base)
}}

val Mod = Z(λ { recur -> λ { m -> λ { n ->
    If(Leq(n)(m))(
            λ { defer -> recur(Sub(m)(n))(n) (defer) }
    )(
            m
    )
}}})
