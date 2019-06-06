package com.home.taipeiwaitingbusdemo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.home.taipeiwaitingbusdemo.data.MainTaiwanGoodTravelData
import com.home.taipeiwaitingbusdemo.databinding.FragmentMainTaiwanGoodTravelRecyclerViewItemBinding

class MainTaiwanGoodTravelRecyclerViewAdapter(
    private val context: Context,
    private val dataList: List<MainTaiwanGoodTravelData.NorthData>
) : RecyclerView.Adapter<MainTaiwanGoodTravelRecyclerViewAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null
    private var lastPosition: Int = dataList.size - 1

    interface OnItemClickListener {
        fun onDynamicClick() // 動態
        fun onTicketClick() // 套票
        fun onTimetableClick() // 時刻表
        fun onOfficialWebsiteClick() // 官網
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val itemBinding: com.home.taipeiwaitingbusdemo.databinding.FragmentMainTaiwanGoodTravelRecyclerViewItemBinding =
            FragmentMainTaiwanGoodTravelRecyclerViewItemBinding.inflate(
                LayoutInflater.from(context),
                viewGroup,
                false
            )
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        initializeHolderItemBinding(holder, position)
    }

    /** 初始化Item的相關 */
    private fun initializeHolderItemBinding(holder: ViewHolder, position: Int) {
        holder.itemBinding!!.eventNameTextView.text = dataList[position].eventName // 設置活動名稱
        holder.itemBinding!!.countyNameTextView.text = dataList[position].countyName // 設置縣市名稱
        holder.itemBinding!!.introductionTextView.text = dataList[position].introduction // 設置活動簡介
        if (position == lastPosition) {
            holder.itemBinding!!.view.visibility = View.GONE
        } else {
            holder.itemBinding!!.view.visibility = View.VISIBLE
        }
        holder.itemBinding!!.dynamicLinearLayout.setOnClickListener {
            onItemClickListener!!.onDynamicClick()
        }
        holder.itemBinding!!.ticketLinearLayout.setOnClickListener {
            onItemClickListener!!.onTicketClick()
        }
        holder.itemBinding!!.timetableLinearLayout.setOnClickListener {
            onItemClickListener!!.onTimetableClick()
        }
        holder.itemBinding!!.officialWebsiteLinearLayout.setOnClickListener {
            onItemClickListener!!.onOfficialWebsiteClick()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemBinding: FragmentMainTaiwanGoodTravelRecyclerViewItemBinding? = null

        constructor(itemBinding: FragmentMainTaiwanGoodTravelRecyclerViewItemBinding) : this(
            itemBinding.root
        ) {
            this.itemBinding = itemBinding
        }
    }
}