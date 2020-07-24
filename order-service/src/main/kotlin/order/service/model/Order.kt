package order.service.model

data class Order (
        val id: Int?,
        val bikeId: Int?,
        val amount: Double?,
        val customer: Customer?
)