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
    var items: SortedSet<RecyclerViewItem> = sortedSetOf(comparator { r1, r2 -> compareValues(r1.id, r2.id) })

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

    fun addItem(newElem: RecyclerViewItem, position: Int = items.indexOf(newElem)) {
        items.add(newElem)
        notifyItemInserted(position)
    }

    fun removeItem(remElem: RecyclerViewItem, position: Int = items.indexOf(remElem)) {
        items.remove(remElem)
        notifyItemRemoved(position)
    }

    fun changeItemsByType(newList: List<RecyclerViewItem>) {
        removeElementsOneByOne(newList)
        addElementsOneByOne(newList)
    }

    private fun addElementsOneByOne(newList: List<RecyclerViewItem>) {
        for(newElem in newList) {
            if (newElem !in items) {
                addItem(newElem)
            }
        }
    }

    private fun removeElementsOneByOne(newList: List<RecyclerViewItem>) {
        var index: Int = 0
        var iter: MutableIterator<RecyclerViewItem> = items.iterator()
        while (iter.hasNext()) {
            val item: RecyclerViewItem = iter.next()
            if (newList.isEmpty() || ((newList.first() theSameClass item) && item !in newList)) {
                iter.remove()
                notifyItemRemoved(index)
            } else {
                index++
            }
        }
    }
}