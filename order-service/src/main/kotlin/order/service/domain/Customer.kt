package order.service.domain

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "customer")
@Table(name = "customer")
data class Customer(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,

        @NotNull
        @Column(name = "name", nullable = false)
        val name: String = ""
) {
    @OneToMany(
            mappedBy = "customer",
            targetEntity = Order::class,
            fetch = FetchType.EAGER
    )
    val order: MutableList<Order>? = mutableListOf()
    
    @OneToOne(
            mappedBy = "customer",
            cascade = [CascadeType.ALL],
            orphanRemoval = true,
            optional = false
    )
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "id"
    )
    var address: Address? = null

    override fun toString(): String {
        return """
                customer {
                  id: $id
                  name: $name 
                  address: {
                    $address
                  }
                }
        """.trimIndent()
    }
}