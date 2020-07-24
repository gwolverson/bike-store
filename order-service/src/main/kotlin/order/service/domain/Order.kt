package order.service.domain

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "product_order")
@Table(name = "product_order")
data class Order(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,

        @NotNull
        @Column(name = "bikeId", nullable = false)
        val bikeId: Int = 0,

        @NotNull
        @Column(name = "amount", nullable = false)
        val amount: Double = 0.00,

        @ManyToOne(
                optional = false
        )
        @JoinColumn(
                name = "customer_id",
                referencedColumnName = "id"
        )
        var customer: Customer? = null
) {
    override fun toString(): String {
        return """
                order {
                  id: $id
                  bikeId: $bikeId
                  amount: $amount 
                  customer: {
                    $customer
                  }
                }
        """.trimIndent()
    }
}