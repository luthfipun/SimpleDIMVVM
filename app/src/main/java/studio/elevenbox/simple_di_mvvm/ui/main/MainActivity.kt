package studio.elevenbox.simple_di_mvvm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import studio.elevenbox.simple_di_mvvm.R
import studio.elevenbox.simple_di_mvvm.data.model.UserItem
import studio.elevenbox.simple_di_mvvm.ui.adapter.MainAdapter

class MainActivity : AppCompatActivity(), KodeinAware, MainAdapter.Listener {

    override val kodein by closestKodein()
    private val viewModelFactory: MainViewModelFactory by instance()

    private lateinit var viewModel: MainViewModel
    private var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
        bindUI()
    }

    private fun initRecyclerView() {
        mainAdapter = MainAdapter(this)
        recyclerView.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            adapter = mainAdapter
        }
    }

    override fun onItemClick(userItem: UserItem) {
        Toast.makeText(this, userItem.id.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun bindUI() {
        GlobalScope.launch(Dispatchers.Main){
            val getUserItem = viewModel.getUserItem.await()
            getUserItem.observe(this@MainActivity, Observer { listUserItem ->
                if (listUserItem == null) return@Observer
                progress.visibility = View.GONE
                mainAdapter?.addData(listUserItem)
            })
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }
}
