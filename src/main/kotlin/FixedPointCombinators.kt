val Y = λ { f -> λ { x -> f(x(x)) }(λ { x -> f(x(x)) }) }

val Z = λ { f -> λ { x -> f(λ { y -> x(x)(y) }) }(λ { x -> f(λ { y -> x(x)(y) }) }) }