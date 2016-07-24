val <T : Any> T.λ: Valuable<T> get() = Wrapper(this)
@Suppress("UNCHECKED_CAST") fun <T : Any> λ.v(): T = (this as Valuable<T>).value
data class Wrapper<T : Any>(override val value: T) : Valuable<T> {
    override fun invoke(λ: λ?): λ = λ!!
}

val λ.v: Any? get() = (this as Valuable<*>).value

interface Valuable<T> : λ {
    val value: T
    override fun invoke(λ: λ?): λ = λ!!

}

fun defer(func: (λ?) -> λ) = object : Valuable<Any?> {
    override val value: Any? get() = func(λ).v
    override fun invoke(λ: λ?): λ = func(λ)
}