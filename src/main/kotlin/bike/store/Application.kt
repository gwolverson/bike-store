package bike.store

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("bike.store")
                .mainClass(Application.javaClass)
                .start()
    }
}