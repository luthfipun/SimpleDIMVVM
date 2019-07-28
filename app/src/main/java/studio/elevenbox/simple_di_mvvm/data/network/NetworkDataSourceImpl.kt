package studio.elevenbox.simple_di_mvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import studio.elevenbox.simple_di_mvvm.data.model.UserItem
import java.io.IOException

class NetworkDataSourceImpl(private val apiService: ApiService) : NetworkDataSource {

    private val _fetchedUsers = MutableLiveData<List<UserItem>>()
    override val fetchUsers: LiveData<List<UserItem>>
        get() = _fetchedUsers

    @Suppress("unchecked_cast")
    override suspend fun getUsers() {
        try {
            val doGetUsersItem = apiService
                .getUsers()
                .await()
            _fetchedUsers.postValue(doGetUsersItem.data as List<UserItem>)
        }
        catch (e: IOException){
            Log.d("Network", "No Internet", e)
        }
    }
}