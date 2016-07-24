
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class λtest {

    //    val If = λ { p -> p }
    //    val One = λ { f -> λ { x -> f(x) } }
    //    val Two = λ { f -> λ { x -> f(f(x)) } }
    //    val Plus = λ { m -> λ { n -> λ { f -> λ { x -> m(f(n(f)(x))) } } } }
    //                val Mod = λ { m -> λ { n -> If(Leq(n)(m))(λ { x -> recur(Sub(m)(n))(n)(x) })(m) } }
    // val Mod = Z(λ { recur -> λ { m -> λ { n -> If(Leq(n)(m))(λ { x -> recur(Sub(m)(n))(n)(x) })(m) } } })
    //    val recur: λ = Mod

    val True = λ { x -> λ { y -> x } }
    val False = λ { x -> λ { y -> y } }
    //    val If = λ { p -> λ { t -> λ { f -> p(t)(f) } } }
    val If = λ { p -> λ { t -> λ { f -> p ͺ t ͺ f } } }
    val And = λ { x -> λ { y -> x(y)(x) } }
    val IsZero = λ { n -> n(λ { x -> False })(True) }
    //    val Leq = λ { m -> λ { n -> IsZero(Sub(m)(n)) } }
    val Leq = λ { m -> λ { n -> IsZero(Sub ͺ m ͺ n) } }


    val Succ = λ { n -> λ { f -> λ { x -> f(n ͺ f ͺ x) } } }
    //    val Succ = λ { n -> λ { f -> λ { x -> f(n(f)(x)) } } }
    val Pred = λ { n -> λ { f -> λ { x -> n(λ { g -> λ { h -> h(g(f)) } })(λ { u -> x })(λ { u -> u }) } } }

    val Zero = λ { f -> λ { x -> x } }

    val Plus = λ { m -> λ { n -> m(Succ)(n) } }
    val Mult = λ { m -> λ { n -> λ { f -> m(n(f)) } } }
    val Pow = λ { b -> λ { e -> e(b) } }
    val Sub = λ { m -> λ { n -> n(Pred)(m) } }


    val Z = λ { f -> λ { x -> f(λ { y -> x(x)(y) }) }(λ { x -> f(λ { y -> x(x)(y) }) }) }
    val Mod = Z(λ { f -> λ { m -> λ { n -> If(Leq(n)(m))(λ { x -> f(Sub(m)(n))(n)(x) })(m) } } })

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

    //    val Fold = Z(λ { f -> λ { l -> λ { x -> λ { g -> If(IsEmpty(l))(x)(λ { y -> g(f(Tail(l))(x)(g))(Head(l))(y) }) } } } })
    val Fold = Z(λ { f -> λ { l -> λ { x -> λ { g -> If(IsEmpty ͺ l) ͺ x ͺ λ { y -> g(f(Tail ͺ l) ͺ x ͺ g)(Head ͺ l) ͺ y } } } } })
    val Map = λ { k -> λ { f -> Fold(k)(Empty)(λ { l -> λ { x -> Cons(l)(f(x)) } }) } }

    val Div = Z(λ { f -> λ { m -> λ { n -> If(Leq(n)(m))(λ { x -> Succ(f(Sub(m)(n))(n))(x) })(Zero) } } })
    val Push = λ { l -> λ { x -> Fold(l)(Cons(Empty)(x))(Cons) } }
    val ToDigit = Z(λ { f -> λ { n -> Push(If(Leq(n)(Pred(ten)))(Empty)(λ { x -> f(Div(n)(ten))(x) }))((Mod(n))(ten)) } })

    val one = Succ(Zero)
    val two = Succ(one)
    val three = Succ(two)
    val five = (Plus(two))(three)

    val ten = Mult(two)(five)
    val fifteen = (Mult(five))(three)
    val hundred = Mult(ten)(ten)
    val b = ten
    val f = Succ(b)
    val i = Succ(f)
    val u = Succ(i)
    val zed = Succ(u)

    val fizz = Cons(Cons(Cons(Cons(Empty)(zed))(zed))(i))(f)
    val buzz = Cons(Cons(Cons(Cons(Empty)(zed))(zed))(u))(b)
    val fizzbuzz = Cons(Cons(Cons(Cons(buzz)(zed))(zed))(i))(f)

    val toC: (λ) -> Char = { "0123456789BFiuz"[toI ͺ it] }
    val toS: (λ) -> String = { toA(it).map { toC ͺ it }.joinToString("") }

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

    fun defer(func: (λ?) -> λ) = object : Valuable<Any?> {
        override val value: Any? get() = func(λ).v
        override fun invoke(λ: λ?): λ = func(λ)
    }

    //    val toA: (λ) -> List<λ> = {
//        fun rec(proc: λ, acc: List<λ>): List<λ> = if (toB ͺ IsEmpty(proc)) acc else rec(Tail(proc), acc + Head(proc))
//        rec(it, emptyList<λ>())
//    }
    val toI: (λ) -> Int get() = { it(λ { x -> (1 + x.v<Int>()).λ })(0.λ).v() }
    val toB: (λ) -> Boolean = { λ -> λ(true.λ)(false.λ).v() }

    val <T : Any> T.λ: Valuable<T> get() = Wrapper(this)
    @Suppress("UNCHECKED_CAST") fun <T : Any> λ.v(): T = (this as Valuable<T>).value

    val λ.v: Any? get() = (this as Valuable<*>).value

    infix fun <T> ((λ) -> T).ͺ(other: λ): T = invoke(other)

    data class Wrapper<T : Any>(override val value: T) : Valuable<T> {
        override fun invoke(λ: λ?): λ = λ!!
    }

    interface Valuable<T> : λ {
        val value: T
        override fun invoke(λ: λ?): λ = λ!!
    }

    @Test
    fun FizzBuzz() {
        val result = Map(Range(one)(hundred))(λ { n ->
            If(IsZero((Mod(n))(fifteen)))(fizzbuzz)(If(IsZero(Mod(n)(three)))(fizz)(If(IsZero((Mod(n))(five)))(buzz)(ToDigit(n))))
        })
        toA(result).forEach { println(toS ͺ it) }
    }

    @Test
    fun toDigitDigitizes() {
        assertThat(toS ͺ ToDigit(fifteen)).isEqualTo("15")
    }

    @Test
    fun pushConcatenates() {
        assertThat(toA ͺ Push(Cons(Empty)(1.λ))(2.λ)).isEqualTo(listOf(1.λ, 2.λ))
    }

    @Test
    fun divDivides() {
        assertThat(toI ͺ (Div(ten))(five)).isEqualTo(2)
    }

    @Test
    fun mapMaps() {
        val three = Succ(Succ(Succ(Zero)))

        assertThat((toA ͺ Map(Range(Zero)(three))(Succ)).map(toI)).isEqualTo(listOf(1, 2, 3, 4))
    }

    @Test
    fun foldFolds() {
        val three = Succ(Succ(Succ(Zero)))
        assertThat(toI ͺ Fold(Range(Zero)(three))(Zero)(Plus)).isEqualTo(6)
    }

    @Test
    fun rangeCreatesRange() {
        val three = Succ(Succ(Succ(Zero)))
        assertThat((toA ͺ Range(Zero)(three)).map(toI)).isEqualTo(listOf(0, 1, 2, 3))
    }

    @Test
    fun toArray() {
        assertThat(toA ͺ Cons(Cons(Empty)("two".λ))("one".λ)).isEqualTo(listOf("one".λ, "two".λ))
    }

    @Test
    fun emptyIsEmpty() {
        assertThat(toB ͺ IsEmpty(Empty)).isEqualTo(true)
    }

    @Test
    fun consAddsToList() {
        assertThat(toB ͺ IsEmpty(Cons(Empty)("one".λ))).isEqualTo(false)
        assertThat(toB ͺ IsEmpty(Cons(Cons(Empty)("two".λ))("one".λ))).isEqualTo(false)
    }

    @Test
    fun headGetsFirstElement() {
        assertThat(Head(Cons(Cons(Empty)("two".λ))("one".λ)).v).isEqualTo("one")
    }

    @Test
    fun tailReturnsRest() {
        assertThat(Head(Tail(Cons(Cons(Empty)("two".λ))("one".λ))).v).isEqualTo("two")
    }

    @Test
    fun pairHasLeftAndRight() {
        val one = Succ(Zero)
        val two = Succ(Succ(Zero))
        val pair = Pair("left".λ)("right".λ)
        val pair2 = Pair(one)(two)

        assertThat(toI ͺ Right(pair2)).isEqualTo(2)
        assertThat(toI ͺ Left(pair2)).isEqualTo(1)

        assertThat(Right(pair).v).isEqualTo("right")
        assertThat(Left(pair).v).isEqualTo("left")
    }

    @Test
    fun zeroIsZero() {
        assertThat(toI ͺ Zero).isEqualTo(0)
    }

    @Test
    fun succIncrements() {
        assertThat(toI ͺ Succ(Zero)).isEqualTo(1)
    }

    @Test
    fun predDecrements() {
        val one = Succ(Zero)
        val two = Succ(Succ(Zero))
        val three = Succ(Succ(Succ(Zero)))

        assertThat(toI ͺ Pred(three)).isEqualTo(2)
        assertThat(toI ͺ Pred(Pred(three))).isEqualTo(1)
        assertThat(toI ͺ Pred(two)).isEqualTo(1)
        assertThat(toI ͺ Pred(one)).isEqualTo(0)
    }

    @Test
    fun plusAdds() {
        val one = Succ(Zero)
        val two = Succ(Succ(Zero))

        assertThat(toI ͺ (Plus(one))(two)).isEqualTo(3)
        assertThat(toI ͺ (Plus(two))(two)).isEqualTo(4)
    }

    @Test
    fun subSubtracts() {
        val one = Succ(Zero)
        val three = Succ(Succ(Succ(Zero)))

        assertThat(toI ͺ Sub(three)(one)).isEqualTo(2)
    }

    @Test
    fun multMultiplies() {
        val two = Succ(Succ(Zero))
        val three = Succ(Succ(Succ(Zero)))
        assertThat(toI ͺ (Mult(two))(three)).isEqualTo(6)
    }

    @Test
    fun powExponentiates() {
        val two = Succ(Succ(Zero))
        val three = Succ(Succ(Succ(Zero)))
        assertThat(toI ͺ Pow(two)(three)).isEqualTo(8)
    }

    @Test
    fun ifSwitches() {
        assertThat(toB ͺ If(True)).isEqualTo(true)
        assertThat(toB ͺ If(False)).isEqualTo(false)
    }

    @Test
    fun andIsBoth() {
        assertThat(toB ͺ If(And(True)(False))).isEqualTo(false)
        assertThat(toB ͺ If(And(False)(False))).isEqualTo(false)
        assertThat(toB ͺ If(And(True)(True))).isEqualTo(true)
    }

    @Test
    fun isZero() {
        val zero = Zero
        val two = Succ(Succ(Zero))
        val three = Succ(Succ(Succ(Zero)))

        assertThat(toB ͺ If(IsZero)(zero)).isEqualTo(true)
        assertThat(toB ͺ If(IsZero)(two)).isEqualTo(false)
        assertThat(toB ͺ If(IsZero)(three)).isEqualTo(false)
    }

    @Test
    fun leqIsLessThanEqual() {
        val two = Succ(Succ(Zero))
        val three = Succ(Succ(Succ(Zero)))
        val five = Succ(Succ(Succ(Succ(Succ(Zero)))))

        assertThat(toB ͺ Leq(three)(two)).isEqualTo(false)
        assertThat(toB ͺ Leq(two)(three)).isEqualTo(true)
        assertThat(toB ͺ Leq(two)(two)).isEqualTo(true)
        assertThat(toB ͺ Leq(five)(three)).isEqualTo(false)
        assertThat(toB ͺ Leq(three)(five)).isEqualTo(true)
    }

    infix fun λ.ͺ(other: λ): λ = invoke(other)
    infix fun <T> λ.ͺ(other: (λ) -> T): T = other(this)

    @Test
    fun modIsModulo() {
        assertThat(Mod ͺ five ͺ three ͺ toI).isEqualTo(5 % 3)
        assertThat(Mod ͺ five ͺ two ͺ toI).isEqualTo(5 % 2)
        assertThat(Mod ͺ three ͺ two ͺ toI).isEqualTo(3 % 2)
        assertThat(Mod ͺ three ͺ five ͺ toI).isEqualTo(3 % 5)
    }

}
