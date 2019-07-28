package studio.elevenbox.simple_di_mvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import studio.elevenbox.simple_di_mvvm.data.model.UserItem
import studio.elevenbox.simple_di_mvvm.data.network.NetworkDataSource

class UserRepositoryImpl(private val networkDataSource: NetworkDataSource) : UserRepository {

    private val liveUserItem = MutableLiveData<List<UserItem>>()

    init {
        networkDataSource.apply {
            fetchUsers.observeForever { userItems ->
                liveUserItem.postValue(userItems)
            }
        }
    }

    override suspend fun gettingUsers(): LiveData<out List<UserItem>> {
        return withContext(Dispatchers.IO){
            initData()
            return@withContext liveUserItem
        }
    }

    private suspend fun initData() {
        //get from api service
        networkDataSource.getUsers()
        //this also can use for get from databases
    }
}