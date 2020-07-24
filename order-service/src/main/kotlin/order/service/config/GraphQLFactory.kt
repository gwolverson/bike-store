package order.service.config

import graphql.GraphQL
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import order.service.datafetchers.CreateOrderDataFetcher
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Singleton

@Factory
class GraphQLFactory(
        private val createOrderDataFetcher: CreateOrderDataFetcher
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
                .type("Mutation") { typeWiring ->
                    typeWiring
                            .dataFetcher("createOrder", createOrderDataFetcher)
                }
                .build()
    }
}
