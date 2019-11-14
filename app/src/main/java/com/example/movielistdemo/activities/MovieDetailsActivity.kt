package com.example.movielistdemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movielistdemo.R
import com.example.movielistdemo.models.Search
import com.nowfal.kdroidext.Utils.setImageUrl
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setSupportActionBar(toolbar)
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val data = intent.getSerializableExtra("data") as Search
        setImageUrl(data.Poster, imageView)
        setImageUrl(data.Poster, headerImage)
        textView.text = data.Title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}

