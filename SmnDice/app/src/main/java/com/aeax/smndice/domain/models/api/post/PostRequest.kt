package com.aeax.smndice.domain.models.api.post

import com.squareup.moshi.Json

data class PostRequest (
    @Json(name = "data")
    val dataPost: DataPost
)