package bike.store.input

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class ReviewInput @JsonCreator constructor(
        @JsonProperty("rating") val rating: Int?,
        @JsonProperty("text") val text: String?,
        @JsonProperty("userId") val userId: Int?
)