package studio.elevenbox.simple_di_mvvm.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item.view.*
import studio.elevenbox.simple_di_mvvm.R
import studio.elevenbox.simple_di_mvvm.data.model.UserItem

class MainAdapter(private val listener: Listener) : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    private var listsData = mutableListOf<UserItem>()

    interface Listener {
        fun onItemClick(userItem: UserItem)
    }

    fun addData(item: List<UserItem>){
        listsData.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount() = listsData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listsData[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(userItem: UserItem, listener: Listener) {
            Glide.with(itemView.context)
                .load(userItem.avatar)
                .into(itemView.userPhoto)

            itemView.userName.text = "${userItem.firstName} ${userItem.lastName}"
            itemView.userEmail.text = userItem.email

            itemView.setOnClickListener { listener.onItemClick(userItem) }
        }
    }

}