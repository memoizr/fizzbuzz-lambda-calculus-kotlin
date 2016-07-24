val True = λ { x -> λ { y -> x } }
val False = λ { x -> λ { y -> y } }
val If = λ { p -> p }
val And = λ { x -> λ { y -> x(y)(x) } }
val IsZero = λ { n -> n(λ { x -> False })(True) }
val Leq = λ { m -> λ { n -> IsZero(Sub ͺ m ͺ n) } }