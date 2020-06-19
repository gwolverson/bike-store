package bike.store.datafetchers

import bike.store.domain.Bike
import bike.store.repository.BikeRepository
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BikesDataFetcher(
        @Inject private val bikeRepository: BikeRepository
) : DataFetcher<List<Bike>> {

    override fun get(environment: DataFetchingEnvironment?): List<Bike> = bikeRepository.findAll()
}
