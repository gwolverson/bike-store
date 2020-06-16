package bike.store.domain

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "user")
@Table(name = "user")
data class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,

        @NotNull
        @Column(name = "firstName", nullable = false)
        var firstName: String = "",

        @NotNull
        @Column(name = "lastName", nullable = false)
        var lastName: String = "",

        @NotNull
        @Column(name = "email", nullable = false)
        var email: String = ""
) {
    @OneToMany(
            mappedBy = "user",
            cascade = [CascadeType.ALL],
            orphanRemoval = true
    )
    var reviews: MutableList<Review> = mutableListOf()

    override fun toString(): String {
        return """
            user {
                id: $id
                firstName: $firstName
                lastName: $lastName
                email: $email
            }
        """.trimIndent()
    }
}