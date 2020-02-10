package com.example.wikipedia.activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wikipedia.R
import com.example.wikipedia.activities.holders.CardHolder
import com.example.wikipedia.activities.models.WikiPage

class ArticleCardRecyclerAdapter() : RecyclerView.Adapter<CardHolder>() {
    val currentResults: ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun getItemCount(): Int {
        return currentResults.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        var page = currentResults[position]
        //update view within holder
        holder?.updateWikiPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        var cardItem =LayoutInflater.from(parent?.context).inflate(R.layout.article_card_item,parent,false)
        return CardHolder(cardItem)
    }
}