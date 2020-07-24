package order.service.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "address")
@Table(name = "address")
data class Address(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,

        @NotNull
        @Column(name = "addressLine1", nullable = false)
        val addressLine1: String = "",

        @NotNull
        @Column(name = "addressLine2", nullable = false)
        val addressLine2: String = "",

        @NotNull
        @Column(name = "townCity", nullable = false)
        val townCity: String = "",

        @NotNull
        @Column(name = "postcode", nullable = false)
        val postcode: String = "",

        @OneToOne
        val customer: Customer? = null
) {
    override fun toString(): String {
        return """
                address {
                  id: $id
                  addressLine1: $addressLine1
                  addressLine2: $addressLine2
                  townCity: $townCity
                  postcode: $postcode
                }
        """.trimIndent()
    }
}