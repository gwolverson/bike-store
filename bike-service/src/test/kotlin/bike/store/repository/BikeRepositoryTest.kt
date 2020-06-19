package bike.store.repository

import bike.store.domain.Bike
import bike.store.model.Category
import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.numerics.shouldBeGreaterThan
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.micronaut.test.annotation.MicronautTest
import javax.inject.Inject

@MicronautTest
class BikeRepositoryTest(
        @Inject private var bikeRepository: BikeRepository
) : BehaviorSpec({

    given("no bikes exist in the db") {
        `when`("save is called") {
            val bike = Bike(make = "Ribble", model = "R872", category = Category.ROAD, description = "Fast, racey road bike")

            bikeRepository.save(bike)

            println("Bikes on db: ${bikeRepository.findAll()}")

            then("the bike is saved and exists on the database") {
                val result = bikeRepository.findAll()

                result.size shouldBeGreaterThan 0
                result[0].make shouldBe "Ribble"
                result[0].model shouldBe "R872"
                result[0].category shouldBe Category.ROAD
                result[0].description shouldBe "Fast, racey road bike"
                result[0].reviews shouldHaveSize 0
            }
        }
    }
})
