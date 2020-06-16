package bike.store.input

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class UserInput @JsonCreator constructor(
        @JsonProperty("firstName") val firstName: String?,
        @JsonProperty("lastName") val lastName: String?,
        @JsonProperty("email") val email: String?
)