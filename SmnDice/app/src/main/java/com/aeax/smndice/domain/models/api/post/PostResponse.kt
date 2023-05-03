package com.aeax.smndice.domain.models.api.post

import com.aeax.smndice.domain.models.api.get.DataGet
import com.aeax.smndice.domain.models.api.common.Meta
import com.squareup.moshi.Json

data class PostResponse(
    @Json(name = "data")
    val dataNative: DataGet,
    val meta: Meta
)