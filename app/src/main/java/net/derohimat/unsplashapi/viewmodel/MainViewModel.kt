package net.derohimat.unsplashapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import net.derohimat.unsplashapi.di.DaggerApiComponent
import net.derohimat.unsplashapi.model.ApiResponse
import net.derohimat.unsplashapi.model.ImageResponse
import net.derohimat.unsplashapi.network.UnsplashService
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var unsplashService: UnsplashService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val dispossable = CompositeDisposable()

    val images = MutableLiveData<List<ImageResponse>>()
    val imageLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val loadMore = MutableLiveData<Boolean>()
    val lastPage = MutableLiveData<Boolean>()
    val query = MutableLiveData<String>()

    fun refresh() {
        lastPage.value = false
        getImages()
    }

    fun getImages(page: Int = 0, isLoadMore: Boolean = false) {
        loading.value = true
        loadMore.value = isLoadMore
        dispossable.add(
            unsplashService.getImages(query.value.toString(), page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ApiResponse>() {
                    override fun onSuccess(response: ApiResponse) {
                        if (response.results.isNullOrEmpty()) {
                            if (isLoadMore) {
                                lastPage.value = true
                            }
                        } else {
                            images.value = response.results
                        }
                        imageLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        imageLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        dispossable.clear()
    }

}