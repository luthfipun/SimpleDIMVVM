package studio.elevenbox.simple_di_mvvm.data.repository

import androidx.lifecycle.LiveData
import studio.elevenbox.simple_di_mvvm.data.model.UserItem

interface UserRepository {
    suspend fun gettingUsers(): LiveData<out List<UserItem>>
}