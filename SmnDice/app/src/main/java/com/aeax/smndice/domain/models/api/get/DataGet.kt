package com.aeax.smndice.domain.models.api.get

import com.aeax.smndice.domain.models.api.common.Attributes

data class DataGet(
    val attributes: Attributes,
    val id: Int
)