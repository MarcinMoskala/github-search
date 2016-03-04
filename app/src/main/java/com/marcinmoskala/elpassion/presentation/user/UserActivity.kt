package com.marcinmoskala.elpassion.presentation.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.Gravity.CENTER_VERTICAL
import com.marcinmoskala.elpassion.gson
import com.marcinmoskala.elpassion.model.User
import com.marcinmoskala.elpassion.picasso
import com.marcinmoskala.elpassion.presentation.home.UserActivity.Const.USER_JSON_ARG
import org.jetbrains.anko.*

class UserActivity : Activity() {
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent?.extras
        user = gson.fromJson(extras?.getString(USER_JSON_ARG), User::class.java)

        setContentView(view)
    }

    val view = UI {
        verticalLayout {
            padding = dip(12)
            gravity = CENTER_VERTICAL or CENTER_HORIZONTAL

            imageView {}.lparams {
                width = dip(200)
                height = dip(200)
            }.apply {
                picasso.load(user?.avatar_url).into(this)
            }

            textView() {
                text = user?.id.toString() ?: "Unknown id"
                gravity = CENTER_VERTICAL or CENTER_HORIZONTAL
            }

            textView() {
                text = user?.login ?: "Unknown login"
                gravity = CENTER_VERTICAL or CENTER_HORIZONTAL
            }

            textView() {
                text = user?.url ?: "Unknown url"
                gravity = CENTER_VERTICAL or CENTER_HORIZONTAL
            }
        }
    }.view

    companion object{
        fun start(context: Context,  user: User) {
            context.startActivity(Intent(context, UserActivity::class.java).putExtra(USER_JSON_ARG, gson.toJson(user)))
        }
    }

    object Const {
        val USER_JSON_ARG = "User"
    }
}
