package com.aeax.smndice.domain.models.api.get

import com.aeax.smndice.domain.models.api.common.Meta
import com.squareup.moshi.Json

data class GetResponse(
    @Json(name = "data")
    val dataNative: List<DataGet>,
    val meta: Meta
)