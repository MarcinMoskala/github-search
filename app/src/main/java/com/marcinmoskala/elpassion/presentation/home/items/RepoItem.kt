package com.marcinmoskala.elpassion.presentation.home.items

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.marcinmoskala.elpassion.R
import com.marcinmoskala.elpassion.model.Repo
import com.marcinmoskala.elpassion.picasso

class RepoItem(val repo: Repo) : RecyclerViewItem {
    override val layoutId: Int = R.layout.row_repo_item
    override val id = repo.id

    override fun bindToViewHolder(viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as ViewHolder).apply {
            val context = id.context
            name.text = repo.full_name
            id.text = context.getString(R.string.id_prefix, repo.id)
            context.picasso.load(repo.owner.avatar_url).into(photo)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView by bindView(R.id.name)
        val id: TextView by bindView(R.id.id)
        val photo: ImageView by bindView(R.id.photo)
    }
}