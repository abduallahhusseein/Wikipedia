package com.example.wikipedia.activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wikipedia.R
import com.example.wikipedia.activities.holders.CardHolder
import com.example.wikipedia.activities.holders.ListItemHolder
import com.example.wikipedia.activities.models.WikiPage
import java.util.zip.Inflater

class ArticleListItemRecyclerAdapter(): RecyclerView.Adapter<ListItemHolder>() {

    val currentResults: ArrayList<WikiPage> = ArrayList<WikiPage>()
    override fun getItemCount(): Int {
        //temporary
        return currentResults.size
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        //this is where we will update our view
        var page = currentResults[position]
        holder!!.updateWithPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var cardItem =LayoutInflater.from(parent?.context).inflate(R.layout.article_list_item,parent,false)
        return ListItemHolder(cardItem)
    }
}