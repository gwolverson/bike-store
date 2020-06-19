package bike.store.datafetchers

import bike.store.repository.BikeRepository
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import mu.KotlinLogging
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteBikeDataFetcher(
        @Inject private val bikeRepository: BikeRepository
) : DataFetcher<Boolean> {

    private val logger = KotlinLogging.logger { }

    override fun get(environment: DataFetchingEnvironment?): Boolean {
        val id = environment!!.getArgument<Int>("id")

        logger.info { "Deleting bike with id: $id" }

        try {
            bikeRepository.deleteById(id.toLong())
        } catch (e: Exception) {
            println(e)
            logger.error { "Cannot delete entity with id: $id due to exception: $e" }
            return false
        }
        
        return true
    }
}