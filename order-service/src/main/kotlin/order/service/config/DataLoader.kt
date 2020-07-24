package bike.store.config

import bike.store.domain.Bike
import bike.store.domain.Review
import bike.store.domain.Specification
import bike.store.domain.User
import bike.store.model.Category
import bike.store.repository.BikeRepository
import bike.store.repository.ReviewRepository
import bike.store.repository.SpecificationRepository
import bike.store.repository.UserRepository
import io.micronaut.context.annotation.Requires
import io.micronaut.context.env.Environment
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceReadyEvent
import javax.inject.Singleton

@Singleton
@Requires(env = [Environment.DEVELOPMENT])
class DataLoader(
        private val bikeRepository: BikeRepository,
        private val userRepository: UserRepository,
        private val reviewRepository: ReviewRepository,
        private val specificationRepository: SpecificationRepository
) : ApplicationEventListener<ServiceReadyEvent> {

    override fun onApplicationEvent(event: ServiceReadyEvent?) {
        val users: MutableList<User> = listOf(
                User(firstName = "Joe", lastName = "Bloggs", email = "joe.bloggs@mail.com"),
                User(firstName = "Dave", lastName = "Cloggs", email = "dave.cloggs@mail.com")
        ).toMutableList()

        val bikes: MutableList<Bike> = listOf(
                Bike(make = "Orbea", model = "Terra H30-D 1x", category = Category.GRAVEL, description = "Great all round gravel bike with 1x setup!", price = 1599.00),
                Bike(make = "Ribble", model = "Endurance SL", category = Category.ROAD, description = "Fast, full carbon road bike!", price = 1500.00),
                Bike(make = "Pinarello", model = "Dogma", category = Category.ROAD, description = "Top of the range, full carbon, high spec road bike!", price = 4000.00),
                Bike(make = "Giant", model = "Anthem X", category = Category.MTB, description = "Full suspension, lightweight mountain bike", price = 2500.00),
                Bike(make = "Ribble", model = "GCR", category = Category.GRAVEL, description = "A versatile, british made gravel bike. Great all rounder!", price = 1299.00),
                Bike(make = "Kinesis", model = "G2", category = Category.GRAVEL, description = "A great gravel bike, both on road and on the trails!", price = 1399.00)
        ).toMutableList()

        val specifications: MutableList<Specification> = listOf(
                Specification(groupset = "Shimano Ultegra", wheelset = "Mavic Aksium", tyres = "Continental Gatorskins", frameset = "Carbon", brakes = "Rim", bike = bikes[1]),
                Specification(groupset = "Shimano Dura-ace", wheelset = "Mavic Ksyrium Elite Disc", tyres = "Mavic Yksyon UST", frameset = "Carbon", brakes = "Disk", bike = bikes[2]),
                Specification(groupset = "SRAM Rival 1", wheelset = "Mavic Aksium Disc", tyres = "Schwalbe All-road", frameset = "Alloy", brakes = "Disk", bike = bikes[4]),
                Specification(groupset = "SRAM Apex 1", wheelset = "Mavic Aksium Disc", tyres = "Schwalbe All-road", frameset = "Alloy", brakes = "Disk", bike = bikes[5]),
                Specification(groupset = "SRAM Rival 1", wheelset = "Orbea", tyres = "Kenda Alluvium", frameset = "Alloy", brakes = "Disk", bike = bikes[0]),
                Specification(groupset = "Shimano SLX", wheelset = "Giant XC2", tyres = "Schwalbe Racing Ralph", frameset = "Aluminium", brakes = "Disk", bike = bikes[3])
        ).toMutableList()

        val reviews: MutableList<Review> = listOf(
                Review(user = users[0], bike = bikes[2], rating = 4, text = "Really good bike, quick on the flats and a great climber!"),
                Review(user = users[1], bike = bikes[0], rating = 5, text = "Awesome gravel bike, great on and off the road!"),
                Review(user = users[0], bike = bikes[3], rating = 3, text = "Nice full suspension bike, bit of a slog up climbs though!"),
                Review(user = users[1], bike = bikes[1], rating = 4, text = "Brilliant road bike, great for longer rides!"),
                Review(user = users[1], bike = bikes[4], rating = 4, text = "Brilliant gravel bike, great for trails and dodgy roads!"),
                Review(user = users[0], bike = bikes[5], rating = 4, text = "Brilliant gravel bike, great for trails and dodgy roads!")
        ).toMutableList()

        users.forEach { userRepository.save(it) }
        bikes.forEach { bikeRepository.save(it) }
        specifications.forEach { specificationRepository.save(it) }
        reviews.forEach { reviewRepository.save(it) }
    }
}