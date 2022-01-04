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
import com.example.brein.mydicodingsubmission2.adapter.FollowerAdapter
import com.example.brein.mydicodingsubmission2.viewmodel.FollowerViewModel
import com.example.brein.mydicodingsubmission2.databinding.FragmentFollowerBinding


class FollowerFragment : Fragment() {
    private var followerBinding : FragmentFollowerBinding?= null
    private val binding get() = followerBinding!!
    private lateinit var followerViewModel: FollowerViewModel

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        followerViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory(activity!!.application)
        ).get(FollowerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        followerBinding = FragmentFollowerBinding.inflate(inflater , container , false)
        return binding.root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            loadingBar(true)
            followerViewModel.setDataFollower(arguments!!.getString(DetailUserActivity.FOL_FRAGMENT).toString())
        }
        recyclerData()
    }

   private fun recyclerData(){
       binding.apply {
           userFollower.layoutManager = LinearLayoutManager(context)
           val adapter = FollowerAdapter()
           userFollower.adapter = adapter

           followerViewModel.getDataFollower().observe(viewLifecycleOwner){
               it?.let {
                   adapter.setFollower(it)
                   loadingBar(false)
               }
           }

       }
   }
    private fun loadingBar(progessState : Boolean){
        if ( progessState){
            binding.followerProgress.visibility = View.VISIBLE
        }else{
            binding.followerProgress.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        followerBinding = null
    }

}