package bike.store.datafetchers

import bike.store.domain.Review
import bike.store.input.ReviewInput
import bike.store.repository.BikeRepository
import bike.store.repository.ReviewRepository
import bike.store.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateBikeReviewDataFetcher(
        @Inject private val reviewRepository: ReviewRepository,
        @Inject private val userRepository: UserRepository,
        @Inject private val bikeRepository: BikeRepository
) : DataFetcher<Review> {

    override fun get(environment: DataFetchingEnvironment?): Review {
        val bikeId = environment!!.getArgument<Int>("id")

        val mapper = ObjectMapper()
        val reviewInput = mapper.convertValue(
                environment.getArgument<ReviewInput>("review"),
                ReviewInput::class.java
        )

        val bike = bikeRepository.findById(bikeId.toLong())
        val user = userRepository.findById(reviewInput.userId!!.toLong())

        val review = Review(rating = reviewInput.rating!!, text = reviewInput.text!!, user = user, bike = bike)

        reviewRepository.save(review)

        return review
    }
}