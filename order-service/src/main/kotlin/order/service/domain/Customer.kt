package order.service.model

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
        val name: String = "",

        @OneToOne
        val order: Order? = null
) {
    @OneToOne(
            mappedBy = "customer",
            cascade = [CascadeType.ALL],
            orphanRemoval = true
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