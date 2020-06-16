package bike.store.datafetchers

import bike.store.domain.Bike
import bike.store.model.Category
import bike.store.repository.BikeRepository
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import mu.KotlinLogging
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BikesByCategoryDataFetcher(
        @Inject val bikeRepository: BikeRepository
) : DataFetcher<List<Bike>> {

    private val logger = KotlinLogging.logger {  }
    
    override fun get(environment: DataFetchingEnvironment?): List<Bike> {
        val category = Category.valueOf(environment!!.getArgument("category"))
        
        logger.info { "Searching on category: $category" }
        
        return bikeRepository.findAllByCategory(category)
    }
}
