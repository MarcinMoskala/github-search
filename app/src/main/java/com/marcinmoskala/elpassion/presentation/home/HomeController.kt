package com.marcinmoskala.elpassion.presentation.home

import com.marcinmoskala.elpassion.Rest
import com.marcinmoskala.elpassion.presentation.home.items.RecyclerViewItem
import com.marcinmoskala.elpassion.presentation.home.items.RepoItem
import com.marcinmoskala.elpassion.presentation.home.items.UserItem
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io

class HomeController() {
    val rest: Rest = Rest()
    var subscriber: Subscription? = null
    val margeNSortListFunction: (List<RepoItem>, List<UserItem>) -> List<RecyclerViewItem> = { l1, l2 -> l1.plus(l2).sortedBy { it.layoutId } }

    fun onSearchTextChanged(searchTextObservable: Observable<String>, adapter: HomeListAdapter, showErrorFunction: (String) -> Unit) {
        searchTextObservable
                .repeat()
                .filter { it != "" }
                .subscribe ({
                    getReposBySearchFunction(it, adapter, showErrorFunction)
                },{
                    showErrorFunction(it.message?:"Nieznany b³¹d")
                })
    }

    fun getReposBySearchFunction(text: String, adapter: HomeListAdapter, showErrorFunction: (String) -> Unit) {
        subscriber?.unsubscribe()
        val repoObs = rest.api.repositories(text)
                .subscribeOn(io())
                .map { it.items.map { RepoItem(it) } }
        val userObs = rest.api.users(text)
                .subscribeOn(io())
                .map { it.items.map { UserItem(it) } }
        subscriber = Observable
                .zip(repoObs, userObs, margeNSortListFunction)
                .retry()
                .observeOn(mainThread())
                .subscribe({
                    adapter.changeItems(it)
                }, {
                    showErrorFunction(it.message?:"Nieznany b³¹d")
                })
    }

    fun onFinish(){
        subscriber?.unsubscribe()
    }
}
