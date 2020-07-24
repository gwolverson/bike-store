package bike.store.api

import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Options

@Controller("/graphql")
class Controller {
    
    @Options
    fun options(): MutableHttpResponse<Unit> {
        return HttpResponse.ok()
    }  
}