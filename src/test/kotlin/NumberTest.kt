
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NumberTest {
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
    fun divDivides() {
        assertThat(toI ͺ (Div(ten))(five)).isEqualTo(2)
    }

    @Test
    fun powExponentiates() {
        val two = Succ(Succ(Zero))
        val three = Succ(Succ(Succ(Zero)))
        assertThat(toI ͺ Pow(two)(three)).isEqualTo(8)
    }

    // Boolean

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


    @Test
    fun modIsModulo() {
        assertThat(Mod ͺ five ͺ three ͺ toI).isEqualTo(5 % 3)
        assertThat(Mod ͺ five ͺ two ͺ toI).isEqualTo(5 % 2)
        assertThat(Mod ͺ three ͺ two ͺ toI).isEqualTo(3 % 2)
        assertThat(Mod ͺ three ͺ five ͺ toI).isEqualTo(3 % 5)
    }

}