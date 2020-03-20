package com.fastnews.data.model

data class PostDataChild(
    val children: List<PostChildren>,
    val after: String?,
    val before: String?
)