package com.home.taipeiwaitingbusdemo.fragments

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.home.taipeiwaitingbusdemo.R
import com.home.taipeiwaitingbusdemo.data.MainTaiwanGoodTravelData
import com.home.taipeiwaitingbusdemo.databinding.FragmentMainTaiwanGoodTravelTabLayoutItemBinding
import com.home.taipeiwaitingbusdemo.utility.toast
import com.home.taipeiwaitingbusdemo.adapters.MainTaiwanGoodTravelRecyclerViewAdapter

/**
 * 台灣好行的TabLayoutItem
 */
@SuppressLint("ValidFragment")
class MainTaiwanGoodTravelTabLayoutItemFragment
@SuppressLint("ValidFragment") constructor(
    private val dataList: List<MainTaiwanGoodTravelData.NorthData>
) : Fragment() {

    lateinit var binding: FragmentMainTaiwanGoodTravelTabLayoutItemBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            com.home.taipeiwaitingbusdemo.R.layout.fragment_main_taiwan_good_travel_tab_layout_item,
            container,
            false
        )
        initializeRecyclerView()
        return binding.root
    }

    private fun initializeRecyclerView() {
        val adapter = MainTaiwanGoodTravelRecyclerViewAdapter(this.context!!, dataList)
        adapter.setOnItemClickListener(object :
            MainTaiwanGoodTravelRecyclerViewAdapter.OnItemClickListener {
            override fun onDynamicClick() = toast(R.string.functional_development)
            override fun onTicketClick() = toast(R.string.functional_development)
            override fun onTimetableClick() = toast(R.string.functional_development)
            override fun onOfficialWebsiteClick() = toast(R.string.functional_development)
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        val cacheSize = -1
        binding.recyclerView.setItemViewCacheSize(cacheSize) // 解決複用錯亂的問題
    }
}