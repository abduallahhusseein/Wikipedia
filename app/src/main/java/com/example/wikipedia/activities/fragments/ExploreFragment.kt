package com.example.wikipedia.activities.fragments


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.wikipedia.R
import com.example.wikipedia.activities.SearchActivity
import com.example.wikipedia.activities.adapters.ArticleCardRecyclerAdapter
import com.example.wikipedia.activities.models.WikiResult
import com.example.wikipedia.activities.providers.ArticleDataProvider

/**
 * A simple [Fragment] subclass.
 */
class ExploreFragment : Fragment() {
    private var articleProvider: ArticleDataProvider = ArticleDataProvider()
    var searchCardView : CardView? = null
    var exploreRecycler : RecyclerView? = null
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()
    var refresher: SwipeRefreshLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater!!.inflate(R.layout.fragment_explore, container, false)
        searchCardView = view.findViewById<CardView>(R.id.search_card_view)
        exploreRecycler = view.findViewById<RecyclerView>(R.id.explore_article_recycler)
        refresher = view.findViewById<SwipeRefreshLayout>(R.id.refresher)

        searchCardView!!.setOnClickListener{
            val searchIntent =Intent(context,SearchActivity::class.java)
            context?.startActivity(searchIntent)
        }
        exploreRecycler!!.layoutManager = LinearLayoutManager(context)//what you want to use vertical or horizontal
        exploreRecycler!!.adapter = adapter
        refresher?.setOnRefreshListener {
            getRandomArticles()
        }
        getRandomArticles()
        return view
    }
 private fun getRandomArticles(){
     refresher?.isRefreshing = true
try {
    articleProvider.getRandom(15,{WikiResult ->
        //do something when we get the articles
        adapter.currentResults.clear()
        adapter.currentResults.addAll(WikiResult.query!!.pages)
        activity?.runOnUiThread{
            adapter.notifyDataSetChanged()
            refresher?.isRefreshing = false
        }
    })
}
     catch (ex: Exception){
         //show alert
         val builder = AlertDialog.Builder(activity)
         builder.setMessage(ex.message).setTitle("opps!")
         val dialog = builder.create()
         dialog.show()
     }
 }

}
