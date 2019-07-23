package com.axion.news.network.responses.author

import com.axion.news.database.table.Author
import com.axion.news.network.responses.Meta

data class AuthorResponse(
    val authors: List<Author>,
    val meta: Meta
)