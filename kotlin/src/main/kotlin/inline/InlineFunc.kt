package inline

import kotlin.math.log10

class InlineFunc {

    inline fun testInline(a: () -> Int) {
        a.invoke()
    }

    fun testNoInline(a: () -> Int) {
        a.invoke()
    }
}
