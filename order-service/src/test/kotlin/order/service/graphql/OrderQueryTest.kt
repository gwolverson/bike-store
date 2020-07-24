package order.service.graphql

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotlintest.Spec
import io.kotlintest.extensions.TopLevelTest
import io.kotlintest.specs.BehaviorSpec
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import order.service.domain.Address
import order.service.domain.Customer
import order.service.repository.AddressRepository
import order.service.repository.CustomerRepository
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import javax.inject.Inject

@MicronautTest
class OrderQueryTest(
        @Client("/") private val client: RxHttpClient,
        @Inject private val customerRepository: CustomerRepository,
        @Inject private val addressRepository: AddressRepository
) : BehaviorSpec({

    given("the order-service graphql endpoint is up") {
        `when`("the service is called with a create order query an order is created") {
            val body = """
                mutation {
                    createOrder(bikeId: 1, amount: 1500.00, customerId: 1) {
                        id
                    }
                }
            """.trimIndent()

            val mapper = ObjectMapper()
            val wrapper = mapper.createObjectNode()
                    .put("query", body)
            val jsonBody = mapper.writeValueAsString(wrapper)

            val request = HttpRequest.POST("/graphql", jsonBody)

            val result = client.toBlocking().exchange(request, Argument.of(String::class.java))

            then("the created order is returned with specified fields") {
                val expectedJson = """
                    {
                        "data": {
                            "createOrder": {
                                id: 1
                            }
                        }
                    }
                """.trimIndent()

                JSONAssert.assertEquals(expectedJson, result.body(), JSONCompareMode.LENIENT)
            }
        }
    }
}) {
    private val customer = Customer(name = "Joe Bloggs")

    private val address = Address(addressLine1 = "1 Temp Street", townCity = "Temp Ville", postcode = "TP1 M11", customer = customer)

    override fun beforeSpecClass(spec: Spec, tests: List<TopLevelTest>) {
        super.beforeSpecClass(spec, tests)

        customerRepository.save(customer)
        addressRepository.save(address)
    }
}