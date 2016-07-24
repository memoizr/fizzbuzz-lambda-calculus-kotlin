
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListTest {

    @Test
    fun emptyIsEmpty() {
        assertThat(toB ͺ IsEmpty(Empty)).isEqualTo(true)
    }

    @Test
    fun rangeCreatesRange() {
        val three = Succ(Succ(Succ(Zero)))
        assertThat((toA ͺ Range(Zero)(three)).map(toI)).isEqualTo(listOf(0, 1, 2, 3))
    }

    @Test
    fun pushConcatenates() {
        assertThat(toA ͺ Push(Cons(Empty)(1.λ))(2.λ)).isEqualTo(listOf(1.λ, 2.λ))
    }

    @Test
    fun consAddsToList() {
        assertThat(toB ͺ IsEmpty(Cons(Empty)("getOne".λ))).isEqualTo(false)
        assertThat(toB ͺ IsEmpty(Cons(Cons(Empty)("getTwo".λ))("getOne".λ))).isEqualTo(false)
    }

    @Test
    fun headGetsFirstElement() {
        assertThat(Head(Cons(Cons(Empty)("getTwo".λ))("getOne".λ)).v).isEqualTo("getOne")
    }

    @Test
    fun tailReturnsRest() {
        assertThat(Head(Tail(Cons(Cons(Empty)("getTwo".λ))("getOne".λ))).v).isEqualTo("getTwo")
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
    fun foldFolds() {
        val three = Succ(Succ(Succ(Zero)))
        assertThat(toI ͺ Fold(Range(Zero)(three))(Zero)(Plus)).isEqualTo(6)
    }

    @Test
    fun mapMaps() {
        val three = Succ(Succ(Succ(Zero)))

        assertThat((toA ͺ Map(Range(Zero)(three))(Succ)).map(toI)).isEqualTo(listOf(1, 2, 3, 4))
    }

}