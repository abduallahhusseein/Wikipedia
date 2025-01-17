package com.example.wikipedia.activities.holders

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wikipedia.R
import com.example.wikipedia.activities.ArticleDetailActivity
import com.example.wikipedia.activities.models.WikiPage
import com.github.kittinunf.fuel.core.interceptors.cUrlLoggingRequestInterceptor
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_card_item.view.*

class CardHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val articleImageView : ImageView =itemView.findViewById(R.id.article_image)
    private val titleTextView :TextView =itemView.findViewById(R.id.article_title)
    private var currentPage: WikiPage? = null

    init {
        itemView.setOnClickListener{view: View? ->
            var detailPageIntent = Intent(itemView.context,ArticleDetailActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page",pageJson)
            itemView.context.startActivity(detailPageIntent)
        }
    }

    fun updateWikiPage(page: WikiPage){
        currentPage= page
        titleTextView.text = page.title

        //load image lazily with picasso
        if(page.thumbnail != null){
            Picasso.with(itemView.context).load(page.thumbnail!!.source).into(articleImageView)
        }
    }
}