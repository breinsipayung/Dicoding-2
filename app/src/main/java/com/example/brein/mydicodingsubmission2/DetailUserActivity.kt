package com.example.brein.mydicodingsubmission2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.brein.mydicodingsubmission2.adapter.ViewPagerAdapter
import com.example.brein.mydicodingsubmission2.databinding.ActivityDetailUserBinding
import com.example.brein.mydicodingsubmission2.model.ItemUser
import com.example.brein.mydicodingsubmission2.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private var detailbinding: ActivityDetailUserBinding? = null
    private val binding get() = detailbinding!!
    private lateinit var detailViewModel: DetailViewModel

    companion object{
        const val DETAIL_USER = "detail_user"
        const val FOL_FRAGMENT = "following_follower_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailbinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailViewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory(application)).get(
            DetailViewModel::class.java
        )
        setDetailData()
        viewData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setDetailData(){
        val title = resources.getStringArray(R.array.tab_title)
        val detailUser = intent.getParcelableExtra<ItemUser>(DETAIL_USER)

        detailUser?.username?.let {
            val login = Bundle()
            login.putString(FOL_FRAGMENT , detailUser.username)
            detailViewModel.setUserDetail(it)
//            loading(false)
            binding.viewPager.adapter = ViewPagerAdapter(
                this, title , login
            )
        }
        TabLayoutMediator(binding.tabsFollowerFollowing , binding.viewPager){
            tabs , position -> tabs.text = title[position]
        }.attach()
    }

    private fun viewData(){
        binding.apply {
            detailViewModel.getDetailUser().observe(this@DetailUserActivity){
                it?.let {
                    Glide.with(this@DetailUserActivity).load(it.avatar).into(detailUserAva)
                    detailUserUsername.text = it.username
                    detailUserName.text = it.name
                    detailFollowing.text = if(it.following == "null") getString(R.string.notfound) else it.following
                    detailFollower.text = if(it.follower == "null") getString(R.string.notfound) else it.follower
                    detailUserLocation.text = if(it.location == "null") getString(R.string.notfound) else it.location
                    detailRepos.text = if(it.repo == "null") getString(R.string.notfound) else it.repo
                    detailUserCompany.text = if(it.company == "null") getString(R.string.notfound) else it.company
                    loading(false)
                }
            }
        }
    }

    private fun loading(progressState: Boolean) {
        if (progressState) {
            binding.progress.visibility = View.VISIBLE
            binding.detailUserAva.visibility = View.GONE
        } else {
            binding.progress.visibility = View.GONE
            binding.detailUserAva.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        detailbinding = null
    }
}