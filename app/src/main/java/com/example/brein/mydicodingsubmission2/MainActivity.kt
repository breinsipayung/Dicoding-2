package com.example.brein.mydicodingsubmission2

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brein.mydicodingsubmission2.adapter.UserAdapter
import com.example.brein.mydicodingsubmission2.databinding.ActivityMainBinding
import com.example.brein.mydicodingsubmission2.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private var mainBinding: ActivityMainBinding? = null
    private val binding get() = mainBinding!!
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        mainViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory(application)
        ).get(MainViewModel::class.java)

        getData()

    }

    private fun getData(){
         binding.apply {
            userMain.layoutManager = LinearLayoutManager(this@MainActivity)
             val adapter = UserAdapter()
            userMain.adapter = adapter

            mainViewModel.getUsers().observe(this@MainActivity){
                it?.let {
                    adapter.listUser(it)
                    loading(false)
                }
            }
        }
    }

    private fun loading(progressState: Boolean) {
        if (progressState) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu) : Boolean{
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search_bar).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean{
                query.let {
                    loading(true)
                    mainViewModel.setSearch(it!!)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.change_setting -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }
}


