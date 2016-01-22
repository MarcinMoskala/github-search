package com.marcinmoskala.elpassion.presentation.home

import com.marcinmoskala.elpassion.Rest
import com.marcinmoskala.elpassion.presentation.home.items.RecyclerViewItem
import com.marcinmoskala.elpassion.presentation.home.items.RepoItem
import com.marcinmoskala.elpassion.presentation.home.items.UserItem
import rx.Observable
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io

class HomeController {
    val rest: Rest = Rest()

    fun onSearchTextChanged(searchTextObservable: Observable<String>, adapter: HomeListAdapter, showErrorFunction: (String) -> Unit) {
        searchTextObservable
                .filter { it != "" }
                .flatMap(getReposBySearchFunction)
                .observeOn(mainThread())
                .subscribe({
                    adapter.changeItemsByType(it)
                }, {
                    showErrorFunction(it.message?:"Nieznany b³¹d")
                    onSearchTextChanged(searchTextObservable, adapter, showErrorFunction)
                })
    }

    val getReposBySearchFunction: (String) -> Observable<List<RecyclerViewItem>> = { text: String ->
        Observable.merge(
                rest.api.repositories(text)
                        .subscribeOn(io())
                        .map { it.items.map { RepoItem(it) } },
                rest.api.users(text)
                        .subscribeOn(io())
                        .map { it.items.map { UserItem(it) } }
        )
    }
}
