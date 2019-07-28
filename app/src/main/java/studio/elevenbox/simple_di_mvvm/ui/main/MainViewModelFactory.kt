package studio.elevenbox.simple_di_mvvm.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import studio.elevenbox.simple_di_mvvm.data.repository.UserRepository

class MainViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(userRepository) as T
    }
}