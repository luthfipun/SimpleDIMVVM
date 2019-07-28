package studio.elevenbox.simple_di_mvvm.ui.main

import androidx.lifecycle.ViewModel
import studio.elevenbox.simple_di_mvvm.data.repository.UserRepository
import studio.elevenbox.simple_di_mvvm.utilities.lazyDeferred

class MainViewModel(private val userRepository: UserRepository): ViewModel() {
    val getUserItem by lazyDeferred {
        userRepository.gettingUsers()
    }
}