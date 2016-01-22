package com.marcinmoskala.elpassion.presentation.home.items

import android.support.v7.widget.RecyclerView

interface RecyclerViewItem {
    val layoutId: Int
    val id: Int
    fun bindToViewHolder(viewHolder: RecyclerView.ViewHolder)
}