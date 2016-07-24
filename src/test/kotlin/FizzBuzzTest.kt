import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FizzBuzzTest {
    val toC: (λ) -> Char = { "0123456789BFiuz"[toI ͺ it] }
    val toS: (λ) -> String = { toA(it).map { toC ͺ it }.joinToString("") }
    val ToDigit = Z(λ { f -> λ { n -> Push(If(Leq(n)(Pred(ten)))(Empty)(λ { x -> f(Div(n)(ten))(x) }))((Mod(n))(ten)) } })

    val b = ten
    val f = Succ(b)
    val i = Succ(f)
    val u = Succ(i)

    val zed = Succ(u)
    val fizz = Cons(Cons(Cons(Cons(Empty)(zed))(zed))(i))(f)

    val buzz = Cons(Cons(Cons(Cons(Empty)(zed))(zed))(u))(b)

    val fizzbuzz = Cons(Cons(Cons(Cons(buzz)(zed))(zed))(i))(f)

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
}
