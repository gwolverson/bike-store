package bike.store.model

data class Review(
        val id: Int?,
        val user: User?,
        val rating: Int?,
        val text: String?
) {
}