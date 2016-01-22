package com.marcinmoskala.elpassion.presentation.home

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.widget.EditText
import com.jakewharton.rxbinding.widget.RxTextView
import com.marcinmoskala.elpassion.R
import com.marcinmoskala.elpassion.showError
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class HomeActivity : Activity(), AnkoComponent<HomeActivity> {
    private val presenter = HomeController()
    private val adapter = HomeListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this)
    }

    override fun createView(ui: AnkoContext<HomeActivity>) = with(ui) {
        verticalLayout {
            padding = dip(12)

            val search = editText {
                hintResource = R.string.search_hint
            }

            val recyclerView = recyclerView().lparams {
                margin = dip(16)
                gravity = Gravity.CENTER
            }

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter

            bindSearchToUpdateAdapter(search)
        }
    }

    private fun bindSearchToUpdateAdapter(search: EditText) {
        presenter.onSearchTextChanged(editTextToObservable(search), adapter, {showError(it)})
    }

    fun editTextToObservable(editText: EditText) =
        RxTextView.textChanges(editText).map {it.toString()}
}
