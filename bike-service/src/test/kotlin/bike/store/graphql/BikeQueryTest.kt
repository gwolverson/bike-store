package bike.store.graphql

import bike.store.domain.Bike
import bike.store.domain.Review
import bike.store.domain.User
import bike.store.model.Category
import bike.store.repository.BikeRepository
import bike.store.repository.ReviewRepository
import bike.store.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotlintest.Spec
import io.kotlintest.extensions.TopLevelTest
import io.kotlintest.specs.BehaviorSpec
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest.GET
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.MediaType
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import javax.inject.Inject

@MicronautTest
class BikeQueryTest(
        @Client("/") private val client: RxHttpClient,
        @Inject private val bikeRepository: BikeRepository,
        @Inject private val reviewRepository: ReviewRepository,
        @Inject private val userRepository: UserRepository
) : BehaviorSpec({

    given("the bike-store graphql endpoint is up") {
        `when`("the service is called with a bikes query containing all fields") {

            val uri = "/graphql?query={bikes{ id make model category description }}"
                    .replace(" ", "%20")
                    .replace("{", "%7B")
                    .replace("}", "%7D")

            val request = GET<Any>(uri).accept(MediaType.APPLICATION_JSON_TYPE)

            val result = client.toBlocking().exchange(request, Argument.of(String::class.java))

            then("all the bikes should be return with all fields") {
                val expectedJson = """
                    {
                        "data": {
                            "bikes": [
                                {
                                    "id": 1,
                                    "make": "Orbea",
                                    "model": "Terra H30-D 1x",
                                    "category": "GRAVEL",
                                    "description": "Great all round gravel bike with 1x setup!"
                                },
                                {
                                    "id": 2,
                                    "make": "Ribble",
                                    "model": "Endurance SL",
                                    "category": "ROAD",
                                    "description": "Fast, full carbon road bike!"
                                },
                                {
                                    "id": 3,
                                    "make": "Pinarello",
                                    "model": "Dogma",
                                    "category": "ROAD",
                                    "description": "Top of the range, full carbon, high spec road bike!"
                                },
                                {
                                    "id": 4,
                                    "make": "Giant",
                                    "model": "Anthem X",
                                    "category": "MTB",
                                    "description": "Full suspension, lightweight mountain bike"
                                }
                            ]
                        }
                    }
                """.trimIndent()

                JSONAssert.assertEquals(expectedJson, result.body(), JSONCompareMode.LENIENT)
            }
        }

        `when`("the service is called with a bikes query containing ids only") {
            val uri = "/graphql?query={bikes{ id }}"
                    .replace(" ", "%20")
                    .replace("{", "%7B")
                    .replace("}", "%7D")

            var request = GET<Any>(uri).accept(MediaType.APPLICATION_JSON_TYPE)

            val result = client.toBlocking().exchange(request, Argument.of(String::class.java))

            then("all the bikes should be return with ids only") {
                val expectedJson = """
                    {
                        "data": {
                            "bikes": [
                                {
                                    "id": 1
                                },
                                {
                                    "id": 2
                                },
                                {
                                    "id": 3
                                },
                                {
                                    "id": 4
                                }
                            ]
                        }
                    }
                """.trimIndent()

                JSONAssert.assertEquals(expectedJson, result.body(), JSONCompareMode.LENIENT)
            }
        }

        `when`("the service is called with a bikeByCategory query") {
            val uri = "/graphql?query={bikesByCategory(category: ROAD){ id make model category description }}"
                    .replace(" ", "%20")
                    .replace("{", "%7B")
                    .replace("}", "%7D")

            val request = GET<Any>(uri).accept(MediaType.APPLICATION_JSON_TYPE)

            val result = client.toBlocking().exchange(request, Argument.of(String::class.java))

            then("all the bikes matching the category should be returned with fields") {
                val expectedJson = """
                    {
                        "data": {
                            "bikesByCategory": [
                                {
                                    "id": 2,
                                    "make": "Ribble",
                                    "model": "Endurance SL",
                                    "category": "ROAD",
                                    "description": "Fast, full carbon road bike!"
                                },
                                {
                                    "id": 3,
                                    "make": "Pinarello",
                                    "model": "Dogma",
                                    "category": "ROAD",
                                    "description": "Top of the range, full carbon, high spec road bike!"
                                }
                            ]
                        }
                    }
                """.trimIndent()

                JSONAssert.assertEquals(expectedJson, result.body(), JSONCompareMode.LENIENT)
            }
        }

        `when`("the service is called with a bikeById query") {
            val uri = "/graphql?query={bikeById(id: 2){ id make model category description reviews { id user { id firstName lastName email } rating text}}}"
                    .replace(" ", "%20")
                    .replace("{", "%7B")
                    .replace("}", "%7D")

            println()

            val request = GET<Any>(uri).accept(MediaType.APPLICATION_JSON_TYPE)

            val result = client.toBlocking().exchange(request, Argument.of(String::class.java))

            then("the bike matching the id should be returned with fields") {
                val expectedJson = """
                    {
                      "data": {
                        "bikeById": {
                          "id": 2,
                          "make": "Ribble",
                          "model": "Endurance SL",
                          "category": "ROAD",
                          "description": "Fast, full carbon road bike!",
                          "reviews": [
                            {
                              "id": 4,
                              "user": {
                                "id": 2,
                                "firstName": "Dave",
                                "lastName": "Cloggs",
                                "email": "dave.cloggs@mail.com"
                              },
                              "rating": 4,
                              "text": "Brilliant road bike, great for longer rides!"
                            }
                          ]
                        }
                      }
                    }
                """.trimIndent()

                JSONAssert.assertEquals(expectedJson, result.body(), JSONCompareMode.LENIENT)
            }
        }

        `when`("the service is called with a createBike mutation query") {

            val body = """
                mutation {
                    createBike(make: "Kinesis", model: "G2", category: GRAVEL, description: "British made all round gravel bike") {
                        id
                    }
                }
            """.trimIndent()

            val mapper = ObjectMapper()
            val wrapper = mapper.createObjectNode()
                    .put("query", body)
            val jsonBody = mapper.writeValueAsString(wrapper)

            val request = POST("/graphql", jsonBody)

            val result = client.toBlocking().exchange(request, Argument.of(String::class.java))

            then("the create bike is returned with specified fields") {
                val expectedJson = """
                    {
                        "data": {
                            "createBike": {
                                id: 5
                            }
                        }
                    }
                """.trimIndent()

                JSONAssert.assertEquals(expectedJson, result.body(), JSONCompareMode.LENIENT)
            }
        }

        `when`("the service is called with a deleteBikeById mutation query for existing bike") {

            val body = """
                mutation {
                    deleteBikeById(id: 4)
                }
            """.trimIndent()

            val mapper = ObjectMapper()
            val wrapper = mapper.createObjectNode()
                    .put("query", body)
            val jsonBody = mapper.writeValueAsString(wrapper)

            val request = POST("/graphql", jsonBody)

            val result = client.toBlocking().exchange(request, Argument.of(String::class.java))

            then("the bike with matching id is deleted") {
                val expectedJson = """
                    {
                        "data": {
                            "deleteBikeById": true
                          }
                    }
                """.trimIndent()

                JSONAssert.assertEquals(expectedJson, result.body(), JSONCompareMode.LENIENT)
            }
        }

        `when`("the service is called with a deleteBikeById mutation query for non existing bike") {

            val body = """
                mutation {
                    deleteBikeById(id: 9)
                }
            """.trimIndent()

            val mapper = ObjectMapper()
            val wrapper = mapper.createObjectNode()
                    .put("query", body)
            val jsonBody = mapper.writeValueAsString(wrapper)

            val request = POST("/graphql", jsonBody)

            val result = client.toBlocking().exchange(request, Argument.of(String::class.java))

            then("no bike is deleted") {
                val expectedJson = """
                    {
                        "data": {
                            "deleteBikeById": false
                          }
                    }
                """.trimIndent()

                JSONAssert.assertEquals(expectedJson, result.body(), JSONCompareMode.LENIENT)
            }
        }

        `when`("the service is called with a createBikeReview mutation query") {

            val body = """
                mutation {
                    createBikeReview(id: 1, review: { rating: 5 text: "Fantastic machine!" userId: 111111 }){ id }
                }
            """.trimIndent()

            val mapper = ObjectMapper()
            val wrapper = mapper.createObjectNode()
                    .put("query", body)
            val jsonBody = mapper.writeValueAsString(wrapper)

            val request = POST("/graphql", jsonBody)

            val result = client.toBlocking().exchange(request, Argument.of(String::class.java))

            then("the review is added to the bike with matching id") {
                val expectedJson = """
                    {
                        "data": {
                            "createBikeReview": {
                                "id": 5
                            }
                          }
                    }
                """.trimIndent()

                JSONAssert.assertEquals(expectedJson, result.body(), JSONCompareMode.LENIENT)
            }
        }

        `when`("the service is called with a createUser mutation query") {

            val body = """
                mutation {
                    createUser(user: { firstName: "John" lastName: "Smith" email: "john.smith@mail.com"}){ id }
                }
            """.trimIndent()

            val mapper = ObjectMapper()
            val wrapper = mapper.createObjectNode()
                    .put("query", body)
            val jsonBody = mapper.writeValueAsString(wrapper)

            val request = POST("/graphql", jsonBody)

            val result = client.toBlocking().exchange(request, Argument.of(String::class.java))

            then("the new user is added") {
                val expectedJson = """
                    {
                        "data": {
                            "createUser": {
                                "id": 3
                            }
                          }
                    }
                """.trimIndent()

                JSONAssert.assertEquals(expectedJson, result.body(), JSONCompareMode.LENIENT)
            }
        }
    }
}) {
    private var users: MutableList<User> = listOf(
            User(firstName = "Joe", lastName = "Bloggs", email = "joe.bloggs@mail.com"),
            User(firstName = "Dave", lastName = "Cloggs", email = "dave.cloggs@mail.com")
    ).toMutableList()

    private var bikes: MutableList<Bike> = listOf(
            Bike(make = "Orbea", model = "Terra H30-D 1x", category = Category.GRAVEL, description = "Great all round gravel bike with 1x setup!"),
            Bike(make = "Ribble", model = "Endurance SL", category = Category.ROAD, description = "Fast, full carbon road bike!"),
            Bike(make = "Pinarello", model = "Dogma", category = Category.ROAD, description = "Top of the range, full carbon, high spec road bike!"),
            Bike(make = "Giant", model = "Anthem X", category = Category.MTB, description = "Full suspension, lightweight mountain bike")
    ).toMutableList()

    private var reviews: MutableList<Review> = listOf(
            Review(user = users[0], bike = bikes[2], rating = 4, text = "Really good bike, quick on the flats and a great climber!"),
            Review(user = users[1], bike = bikes[0], rating = 5, text = "Awesome gravel bike, great on and off the road!"),
            Review(user = users[0], bike = bikes[3], rating = 3, text = "Nice full suspension bike, bit of a slog up climbs though!"),
            Review(user = users[1], bike = bikes[1], rating = 4, text = "Brilliant road bike, great for longer rides!")
    ).toMutableList()

    override fun beforeSpecClass(spec: Spec, tests: List<TopLevelTest>) {
        super.beforeSpecClass(spec, tests)
        users.forEach { userRepository.save(it) }
        bikes.forEach { bikeRepository.save(it) }
        reviews.forEach { reviewRepository.save(it) }
    }
}