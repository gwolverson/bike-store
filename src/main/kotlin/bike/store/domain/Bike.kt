package bike.store.domain

import bike.store.model.Category
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "bike")
@Table(name = "bike")
data class Bike(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,

        @NotNull
        @Column(name = "make", nullable = false)
        val make: String = "",

        @NotNull
        @Column(name = "model", nullable = false)
        val model: String = "",

        @NotNull
        @Enumerated
        @Column(name = "category", nullable = false)
        val category: Category? = null,

        @NotNull
        @Column(name = "description", nullable = false)
        val description: String = ""
) {
    @OneToMany(
            mappedBy = "bike", 
            cascade = [CascadeType.ALL], 
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    var reviews: MutableList<Review> = mutableListOf()

    override fun toString(): String {
        return """
                bike {
                  id: $id
                  make: $make
                  model: $model
                  category: $category
                  description: $description
                  reviews: [
                    $reviews
                  ]
                }
        """.trimIndent()
    }
}