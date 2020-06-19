package bike.store.repository

import bike.store.domain.User
import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.micronaut.test.annotation.MicronautTest
import javax.inject.Inject

@MicronautTest
class UserRepositoryTest(
        @Inject private val userRepository: UserRepository
) : BehaviorSpec({

    given("no user exists on the db") {
        `when`("save is called") {

            val user = User(firstName = "Joe", lastName = "Bloggs", email = "joe.bloggs@mail.com")

            userRepository.save(user)

            then("the user is saved and exists on the database") {
                val result = userRepository.findById(1)
                result!!.id shouldBe 1
                result.firstName shouldBe "Joe"
                result.lastName shouldBe "Bloggs"
                result.email shouldBe "joe.bloggs@mail.com"
                result.reviews shouldHaveSize 0
            }
        }
    }
})