package com.axion.news.viewmodel.author

import com.axion.news.repository.UserRepository
import com.axion.news.viewmodel.factory.ObservableViewModel
import javax.inject.Inject

class AuthorViewModel @Inject constructor(
    private val userRepository: UserRepository
): ObservableViewModel() {

    fun getAllAuthors() = userRepository.getAuthors()
}