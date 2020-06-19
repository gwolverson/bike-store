package bike.store.datafetchers

import bike.store.domain.Bike
import bike.store.repository.BikeRepository
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import mu.KotlinLogging
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BikeByIdDataFetcher(
        @Inject val bikeRepository: BikeRepository
) : DataFetcher<Bike> {

    private val logger = KotlinLogging.logger { }
    
    override fun get(environment: DataFetchingEnvironment?): Bike? {
        val id = environment!!.getArgument<Int>("id")

        logger.info { "Searching on category: $id" }

        return bikeRepository.findById(id.toLong())
    }
}
