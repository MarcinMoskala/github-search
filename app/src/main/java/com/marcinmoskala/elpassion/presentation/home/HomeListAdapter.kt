package com.marcinmoskala.elpassion.presentation.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.marcinmoskala.elpassion.R
import com.marcinmoskala.elpassion.presentation.home.items.RecyclerViewItem
import com.marcinmoskala.elpassion.presentation.home.items.RepoItem
import com.marcinmoskala.elpassion.presentation.home.items.UserItem
import com.marcinmoskala.elpassion.theSameClass
import java.util.*

class HomeListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<RecyclerViewItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        when (viewType) {
            R.layout.row_repo_item -> return RepoItem.ViewHolder(itemView)
            R.layout.row_user_item -> return UserItem.ViewHolder(itemView)
            else -> throw IllegalArgumentException("Cannot find view holder for home view")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items.elementAt(position).layoutId
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items.elementAt(position).bindToViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    operator fun get(position: Int): RecyclerViewItem {
        return items.elementAt(position)
    }

    fun changeItems(newItems: List<RecyclerViewItem>) {
        items =  newItems
        notifyDataSetChanged()
    }
}