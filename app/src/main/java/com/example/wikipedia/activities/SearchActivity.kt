package com.example.wikipedia.activities

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wikipedia.R
import com.example.wikipedia.activities.adapters.ArticleListItemRecyclerAdapter
import com.example.wikipedia.activities.models.WikiResult
import com.example.wikipedia.activities.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private val articleProvider: ArticleDataProvider = ArticleDataProvider()
private var adapter: ArticleListItemRecyclerAdapter = ArticleListItemRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        search_results_recycler.layoutManager = LinearLayoutManager(this)
        search_results_recycler.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search_menu,menu)

        val searchItem = menu!!.findItem(R.id.action_search)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView = searchItem!!.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isIconifiedByDefault()
        searchView.requestFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                //do the search and update the element
                    articleProvider.search(query,020,{ WikiResult ->
                        adapter.currentResults.clear()
                        adapter.currentResults.addAll(WikiResult.query!!.pages)
                        runOnUiThread { adapter.notifyDataSetChanged() }
                    })
                println("updated search")

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}

