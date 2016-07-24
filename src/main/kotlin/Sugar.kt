infix fun <T> ((λ) -> T).ͺ(other: λ): T = invoke(other)
infix fun λ.ͺ(other: λ): λ = invoke(other)
infix fun <T> λ.ͺ(other: (λ) -> T): T = other(this)