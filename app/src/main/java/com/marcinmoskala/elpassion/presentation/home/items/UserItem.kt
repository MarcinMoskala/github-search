package com.marcinmoskala.elpassion.presentation.home.items

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.bindView
import com.marcinmoskala.elpassion.R
import com.marcinmoskala.elpassion.gson
import com.marcinmoskala.elpassion.model.User
import com.marcinmoskala.elpassion.presentation.home.UserActivity
import com.marcinmoskala.elpassion.presentation.home.UserActivity.Const.USER_JSON_ARG
import org.jetbrains.anko.onClick

class UserItem(val user: User) : RecyclerViewItem {
    override val layoutId: Int = R.layout.row_user_item
    override val id = user.id

    override fun bindToViewHolder(viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as UserItem.ViewHolder).apply {
            val context = id.context
            name.text = user.login
            id.text = context.getString(R.string.id_prefix, user.id)
            view.onClick {
                UserActivity.start(context, user)
            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val view: View by bindView(R.id.view)
        val name: TextView by bindView(R.id.name)
        val id: TextView by bindView(R.id.id)
    }
}