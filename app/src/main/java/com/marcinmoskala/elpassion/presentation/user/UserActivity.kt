package com.marcinmoskala.elpassion.presentation.home

import android.app.Activity
import android.os.Bundle
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.Gravity.CENTER_VERTICAL
import com.marcinmoskala.elpassion.gson
import com.marcinmoskala.elpassion.model.User
import com.marcinmoskala.elpassion.picasso
import com.marcinmoskala.elpassion.presentation.home.UserActivity.Const.USER_JSON_ARG
import org.jetbrains.anko.*

class UserActivity : Activity(), AnkoComponent<HomeActivity> {
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent?.extras
        user = gson.fromJson(extras?.getString(USER_JSON_ARG), User::class.java)

        setContentView(this)
    }

    override fun createView(ui: AnkoContext<HomeActivity>) = with(ui) {
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
                text = if(user != null) user?.id.toString() else "Unknown id"
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
    }

    object Const {
        val USER_JSON_ARG = "User"
    }
}
