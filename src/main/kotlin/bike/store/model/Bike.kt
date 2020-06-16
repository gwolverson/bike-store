package bike.store.model

data class Bike(
        val id: Int?,
        val make: String?,
        val model: String?,
        val category: Category?,
        val description: String?,
        var reviews: MutableList<Review>?
)