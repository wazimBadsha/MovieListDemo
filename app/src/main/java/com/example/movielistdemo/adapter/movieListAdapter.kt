package com.example.movielistdemo.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.movielistdemo.R
import com.example.movielistdemo.Utils.springyRecyclerView.SpringyAdapterAnimationType
import com.example.movielistdemo.Utils.springyRecyclerView.SpringyAdapterAnimator
import com.example.movielistdemo.models.Search
import kotlinx.android.synthetic.main.row_movie_list_item.view.*
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import android.app.Activity
import android.content.Intent
import androidx.core.util.Pair
import com.example.movielistdemo.activities.MovieDetailsActivity


class movieListAdapter(
    var context: Context,
    var subActivityData: List<Search?>?,
    val rvMovielist: RecyclerView
) : RecyclerView.Adapter<movieListAdapter.SubActivityViewHolder>() {

    private val set = ConstraintSet()
    private val requestOptions = RequestOptions().placeholder(R.drawable.placeholder)
    private var mAnimator: SpringyAdapterAnimator? = null

    init {
        mAnimator = SpringyAdapterAnimator(rvMovielist)
        mAnimator!!.setSpringAnimationType(SpringyAdapterAnimationType.SLIDE_FROM_BOTTOM)
        mAnimator!!.addConfig(85, 15)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubActivityViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_movie_list_item, parent, false)
        mAnimator!!.onSpringItemCreate(view)
        return SubActivityViewHolder(view!!)

    }

    override fun onBindViewHolder(holder: SubActivityViewHolder, position: Int) {
        holder.bindData(subActivityData?.get(position), context)

    }

    override fun getItemCount(): Int {
        return subActivityData!!.size
    }

    inner class SubActivityViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bindData(item: Search?, context: Context) {
            itemView.run {
                Glide.with(context)
                    .setDefaultRequestOptions(requestOptions)
                    .asBitmap()
                    .load(item?.Poster)
                    .into(object : SimpleTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            with(set) {
                                @SuppressLint("DefaultLocale")
                                val posterRatio =
                                    String.format("%d:%d", resource.width, resource.height)
                                clone(parentContsraint)
                                setDimensionRatio(imgSource.id, posterRatio)
                                applyTo(parentContsraint)

                            }
                            imgSource.setImageBitmap(resource)
                        }

                    })
                desc.text = item?.Title
                type.text = item?.Type
                root.setOnClickListener {
                    val imgAnim = Pair.create<View?, String?>(imgSource, "imageView")
//                    val textAnim = Pair.create<View?, String?>(desc, "textview")
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        context as Activity,
                        imgAnim
                    )
                    val intent = Intent(context, MovieDetailsActivity::class.java)
                    intent.putExtra("data", item)

                    ActivityCompat.startActivity(context, intent, options.toBundle())

                }

            }


        }


    }


}
