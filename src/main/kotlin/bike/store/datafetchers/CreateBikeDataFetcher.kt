package bike.store.datafetchers

import bike.store.domain.Bike
import bike.store.model.Category
import bike.store.repository.BikeRepository
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateBikeDataFetcher(
        @Inject private val bikeRepository: BikeRepository
) : DataFetcher<Bike> {

    override fun get(environment: DataFetchingEnvironment?): Bike {
        val make = environment!!.getArgument<String>("make")
        val model = environment.getArgument<String>("model")
        val category = Category.valueOf(environment.getArgument("category"))
        val description = environment.getArgument<String>("description")

        val bike = Bike(make = make, model = model, category = category, description = description)

        bikeRepository.save(bike)

        return bike
    }
}