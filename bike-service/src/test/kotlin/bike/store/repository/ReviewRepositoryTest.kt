package bike.store.repository

import bike.store.domain.Bike
import bike.store.domain.Review
import bike.store.domain.User
import bike.store.model.Category
import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.numerics.shouldBeGreaterThan
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.micronaut.test.annotation.MicronautTest
import javax.inject.Inject

@MicronautTest
class ReviewRepositoryTest(
        @Inject val reviewRepository: ReviewRepository,
        @Inject val bikeRepository: BikeRepository,
        @Inject val userRepository: UserRepository
) : BehaviorSpec({

    given("no reviews exist on the db") {
        `when`("save is called with a review linked to a bike and user") {
            val user = User(firstName = "Joe", lastName = "Bloggs", email = "joe.bloggs@mail.com")
            userRepository.save(user)

            val bike = Bike(make = "Orbea", model = "Terra H30-D 1x", category = Category.GRAVEL, description = "Great all round gravel bike with 1x setup!")
            bikeRepository.save(bike)

            val review = Review(rating = 3, text = "Good bike", user = user, bike = bike)
            reviewRepository.save(review)
            
            val result = reviewRepository.findAll()

            then("findAll should return the saved review") {
                result.size shouldBeGreaterThan 0
                result[0].id shouldBe 1
                result[0].rating shouldBe 3
                result[0].text shouldBe "Good bike"
                result[0].bike?.make shouldBe "Orbea"
                result[0].bike?.model shouldBe "Terra H30-D 1x"
                result[0].bike?.category shouldBe Category.GRAVEL
                result[0].bike?.description shouldBe "Great all round gravel bike with 1x setup!"
                result[0].user?.firstName shouldBe "Joe"
                result[0].user?.lastName shouldBe "Bloggs"
                result[0].user?.email shouldBe "joe.bloggs@mail.com"
            }
        }
    }
})