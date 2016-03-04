package com.marcinmoskala.elpassion.presentation.home

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.widget.EditText
import com.jakewharton.rxbinding.widget.RxTextView
import com.marcinmoskala.elpassion.R
import com.marcinmoskala.elpassion.model.User
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class HomeActivity : Activity() {
    private val presenter = HomeController()
    private val adapter = HomeListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view)
    }

    val view = UI {
        verticalLayout {
            padding = dip(12)

            val search = editText {
                hintResource = R.string.search_hint
                maxLines = 1
                singleLine = true
            }

            val recyclerView = recyclerView().lparams {
                margin = dip(16)
                gravity = Gravity.CENTER
            }

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter

            bindSearchToUpdateAdapter(search)
        }
    }.view

    override fun onDestroy() {
        super.onDestroy()
        presenter.onFinish()
    }

    private fun bindSearchToUpdateAdapter(search: EditText) {
        presenter.onSearchTextChanged(editTextToObservable(search), adapter, {toast(it)})
    }

    fun editTextToObservable(editText: EditText) =
        RxTextView.textChanges(editText).map {it.toString()}
}
