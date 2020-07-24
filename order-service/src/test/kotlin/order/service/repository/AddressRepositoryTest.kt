package order.service.repository

import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.BehaviorSpec
import io.micronaut.test.annotation.MicronautTest
import order.service.domain.Address
import javax.inject.Inject

@MicronautTest
class AddressRepositoryTest(
        @Inject private val addressRepository: AddressRepository
) : BehaviorSpec({

    given("no address exists on the db") {
        `when`("save is called") {
            val address = Address(addressLine1 = "1 Temp Road", townCity = "Temp Ville", postcode = "TE1 1MP")
            addressRepository.save(address)

            then("the order is saved and exists on the database") {
                val result = addressRepository.findById(1)
                result shouldNotBe null
                result!!.id shouldBe 1
                result.addressLine1 shouldBe "1 Temp Road"
                result.townCity shouldBe "Temp Ville"
                result.postcode shouldBe "TE1 1MP"
            }
        }
    }
})