import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UtilTest {
    @Test
    fun fromInt() {
        assertThat(toI ͺ fromInt(0)).isEqualTo(0)
        assertThat(toI ͺ fromInt(1)).isEqualTo(1)
        assertThat(toI ͺ fromInt(2)).isEqualTo(2)
        assertThat(toI ͺ fromInt(99)).isEqualTo(99)
    }

    @Test
    fun fromBoolean() {
        val booleanFalse = fromBoolean(false)
        val booleanTrue = fromBoolean(true)

        println(toB ͺ fromBoolean(setOf<Any?>().isEmpty()))

        assertThat(toB ͺ booleanFalse).isEqualTo(false)
        assertThat(toB ͺ booleanTrue).isEqualTo(true)
    }

    @Test
    fun toArray() {
        assertThat(toA ͺ Cons(Cons(Empty)("getTwo".λ))("getOne".λ)).isEqualTo(listOf("getOne".λ, "getTwo".λ))
    }
}