package com.fastnews.data.model

data class CommentData(
    val id: String,
    val author: String,
    val body: String,
    val name: String,
    val downs: Int,
    val ups: Int,
    val created_utc: Long
)