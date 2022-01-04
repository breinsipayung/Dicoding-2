package com.example.brein.mydicodingsubmission2.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brein.mydicodingsubmission2.DetailUserActivity
import com.example.brein.mydicodingsubmission2.adapter.FollowingAdapter
import com.example.brein.mydicodingsubmission2.viewmodel.FollowingViewModel
import com.example.brein.mydicodingsubmission2.databinding.FragmentFollowingBinding


class FollowingFragment : Fragment() {

    private var followingBinding : FragmentFollowingBinding? = null
    private val binding get() = followingBinding!!
    private lateinit var  followingViewModel : FollowingViewModel

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        followingViewModel = ViewModelProvider(this,ViewModelProvider.
        AndroidViewModelFactory(activity!!.application)).
        get(FollowingViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         followingBinding = FragmentFollowingBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            loadingBar(true)
            followingViewModel.setDataFollowing(arguments?.getString(DetailUserActivity.FOL_FRAGMENT).toString())
        }
        recyclerData()
    }
    
    private fun recyclerData(){
        binding.apply {
            userFollowing.layoutManager = LinearLayoutManager(context)
            val followingAdapter = FollowingAdapter()
            userFollowing.adapter = followingAdapter

            followingViewModel.getDataFollowing().observe(viewLifecycleOwner){
                it?.let {
                    followingAdapter.setFollowing(it)
                    loadingBar(false)
                }
            }

        }
    }

    private fun loadingBar(progessState : Boolean){
        if ( progessState){
            binding.followingProgress.visibility = View.VISIBLE
        }else{
            binding.followingProgress.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        followingBinding = null
    }

}