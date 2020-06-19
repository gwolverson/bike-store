package bike.store.config

import bike.store.datafetchers.*
import graphql.GraphQL
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Singleton

@Factory
class GraphQLFactory(
        private val bikesDataFetcher: BikesDataFetcher,
        private val bikesByCategoryDataFetcher: BikesByCategoryDataFetcher,
        private val bikeByIdDataFetcher: BikeByIdDataFetcher,
        private val createBikeDataFetcher: CreateBikeDataFetcher,
        private val createBikeReviewDataFetcher: CreateBikeReviewDataFetcher,
        private val createUserDataFetcher: CreateUserDataFetcher,
        private val deleteBikeDataFetcher: DeleteBikeDataFetcher
) {

    @Bean
    @Singleton
    fun graphQL(resourceResolver: ResourceResolver): GraphQL {
        val schemaParser = SchemaParser()
        val schemaGenerator = SchemaGenerator()

        val typeRegistry = TypeDefinitionRegistry()
        typeRegistry.merge(schemaParser.parse(BufferedReader(InputStreamReader(
                resourceResolver.getResourceAsStream("classpath:schema.graphqls").get()))))

        val graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, createRuntimeWiring())

        return GraphQL.newGraphQL(graphQLSchema).build()
    }

    private fun createRuntimeWiring(): RuntimeWiring {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query") { typeWiring ->
                    typeWiring
                            .dataFetcher("bikes", bikesDataFetcher)
                            .dataFetcher("bikesByCategory", bikesByCategoryDataFetcher)
                            .dataFetcher("bikeById", bikeByIdDataFetcher)
                }
                .type("Mutation") { typeWiring ->
                    typeWiring
                            .dataFetcher("createBike", createBikeDataFetcher)
                            .dataFetcher("createBikeReview", createBikeReviewDataFetcher)
                            .dataFetcher("createUser", createUserDataFetcher)
                            .dataFetcher("deleteBikeById", deleteBikeDataFetcher)
                }
                .build()
    }
}
