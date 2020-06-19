package bike.store.domain

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "review")
@Table(name = "review")
data class Review(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,

        @NotNull
        @Column(name = "rating", nullable = false)
        val rating: Int = 0,

        @NotNull
        @Column(name = "text", nullable = false)
        val text: String = "",

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        val user: User? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "bike_id", referencedColumnName = "id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        val bike: Bike? = null
) {
    override fun toString(): String {
            return """
                    review {
                        id: $id
                        rating: $rating
                        text: $text
                        user: ${user.toString()}
                    }
            """.trimIndent()
    }
}