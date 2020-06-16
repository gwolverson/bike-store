package bike.store.datafetchers

import bike.store.domain.User
import bike.store.input.UserInput
import bike.store.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateUserDataFetcher(
        @Inject private val userRepository: UserRepository
) : DataFetcher<User> {

    override fun get(environment: DataFetchingEnvironment?): User {
        val mapper = ObjectMapper()
        val user = mapper.convertValue(
                environment!!.getArgument<UserInput>("user"),
                UserInput::class.java
        )

        val newUser = User(firstName = user.firstName!!, lastName = user.lastName!!, email = user.email!!)

        userRepository.save(newUser)

        return newUser
    }
}