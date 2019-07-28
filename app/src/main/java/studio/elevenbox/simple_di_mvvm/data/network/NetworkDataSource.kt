package studio.elevenbox.simple_di_mvvm.data.network

import androidx.lifecycle.LiveData
import studio.elevenbox.simple_di_mvvm.data.model.UserItem

interface NetworkDataSource {
    val fetchUsers: LiveData<List<UserItem>>
    suspend fun getUsers()
}