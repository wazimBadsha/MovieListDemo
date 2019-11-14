package com.example.movielistdemo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movielistdemo.Repo.HTTPClient
import com.example.movielistdemo.models.Search
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var mMovieList: MutableLiveData<List<Search?>>? = null
    var mMovieListData = mutableListOf<Search>()

    fun getProProfile(): LiveData<List<Search?>> {
        if (mMovieList == null) {
            mMovieList = MutableLiveData()
            GlobalScope.launch {
                try {
                    val result = HTTPClient.coroutineRestfulAPI.getMovieList(page = "1").await()
                    if(result.Search!!.isNotEmpty()){
                        mMovieList!!.postValue(result.Search)
                    }else{
                        mMovieList!!.postValue(null)
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                    mMovieList!!.postValue(null)
                }
            }
        }
        return mMovieList as MutableLiveData<List<Search?>>
    }

}
