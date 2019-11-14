package com.example.movielistdemo.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.movielistdemo.R
import com.example.movielistdemo.adapter.movieListAdapter
import com.example.movielistdemo.viewModels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeUpdatedFragment : Fragment() {

    private lateinit var mAdapter: movieListAdapter
    private lateinit var mLayoutManager: StaggeredGridLayoutManager
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mAdapter = movieListAdapter(context!!, viewModel.mMovieListData, rv_movieList)
        view?.run {
            rv_movieList.apply {
                adapter = mAdapter
                layoutManager = mLayoutManager
            }
        }

        viewModel.getProProfile().observe(this, Observer {
            if(it != null){
                it.forEach {
                    viewModel.mMovieListData.add(it!!)
                }// or use addAll inline fun
                mAdapter.notifyDataSetChanged()
            }
        })
    }


}
