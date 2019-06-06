package com.home.taipeiwaitingbusdemo.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.home.taipeiwaitingbusdemo.MainActivity
import com.home.taipeiwaitingbusdemo.R
import com.home.taipeiwaitingbusdemo.adapters.MainTaiwanGoodTravelFragmentPagerAdapter
import com.home.taipeiwaitingbusdemo.data.CateringTourismInformationDatabaseData
import com.home.taipeiwaitingbusdemo.data.MainTaiwanGoodTravelData
import com.home.taipeiwaitingbusdemo.databinding.FragmentMainTaiwanGoodTravelBinding
import com.home.taipeiwaitingbusdemo.utility.toast
import com.howshea.home.repository.MainService
import com.home.taipeiwaitingbusdemo.data.extentions.dispatchDefault
import io.reactivex.rxkotlin.subscribeBy

/**
 * 台灣好行
 */
class MainTaiwanGoodTravelFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(activity: MainActivity): MainTaiwanGoodTravelFragment {
            val fragment = MainTaiwanGoodTravelFragment()
            fragment.activity = activity
            return fragment
        }
    }

    lateinit var activity: MainActivity
    lateinit var binding: FragmentMainTaiwanGoodTravelBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_taiwan_good_travel,
            container,
            false
        )
        setActivityCurrentShowFragmentLabel()
        initializeClickListener()
        requestGetCateringTourismInformationDatabaseData()
        return binding.root
    }

    /** 請求取得餐飲觀光資訊資料庫的資料 */
    private fun requestGetCateringTourismInformationDatabaseData() {
        binding.listLinearLayout.visibility = View.INVISIBLE
        MainService.getCateringTourismInformationDatabaseData()
            .dispatchDefault()
            .subscribeBy(
                onNext = {
                    val info = it.body()!!.xMLHead.infos.info
                    val northDataList = mutableListOf<MainTaiwanGoodTravelData.NorthData>()
                    val centralDataList = mutableListOf<MainTaiwanGoodTravelData.NorthData>()
                    val southDataList = mutableListOf<MainTaiwanGoodTravelData.NorthData>()
                    val eastDataList = mutableListOf<MainTaiwanGoodTravelData.NorthData>()
                    val outlyingIslandDataList = mutableListOf<MainTaiwanGoodTravelData.NorthData>()
                    val north = "北部"
                    val central = "中部"
                    val south = "南部"
                    val east = "東部"
                    val outlyingIsland = "離島"
                    val min = 0
                    val max = 100
                    val subSequenceMax = 2
                    for (position in min until max) {
                        val countyName = info[position].location.subSequence(min, subSequenceMax).toString()
                        val countyGroupName = countyGrouping(countyName)
                        when (countyGroupName) {
                            north -> northDataList.add(newNorthData(info, position, countyName))
                            central -> centralDataList.add(newNorthData(info, position, countyName))
                            south -> southDataList.add(newNorthData(info, position, countyName))
                            east -> eastDataList.add(newNorthData(info, position, countyName))
                            outlyingIsland -> outlyingIslandDataList.add(newNorthData(info, position, countyName))
                        }
                    }
                    initializeViewPager(
                        northDataList, centralDataList, southDataList,
                        eastDataList, outlyingIslandDataList
                    )
                    binding.listLinearLayout.visibility = View.VISIBLE
                    activity.dismissLoadingDialog()
                },
                onError = {
                    toast(R.string.network_exception)
                    activity.dismissLoadingDialog()
                }
            )
    }

    private fun initializeViewPager(
        northDataList: MutableList<MainTaiwanGoodTravelData.NorthData>,
        centralDataList: MutableList<MainTaiwanGoodTravelData.NorthData>,
        southDataList: MutableList<MainTaiwanGoodTravelData.NorthData>,
        eastDataList: MutableList<MainTaiwanGoodTravelData.NorthData>,
        outlyingIslandDataList: MutableList<MainTaiwanGoodTravelData.NorthData>
    ) {
        val pagerAdapter = MainTaiwanGoodTravelFragmentPagerAdapter(
            northDataList, centralDataList, southDataList,
            eastDataList, outlyingIslandDataList, fragmentManager
        )
        binding.viewPager.adapter = pagerAdapter
        val pageLimit = 5
        binding.viewPager.offscreenPageLimit = pageLimit
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    /**
     * 判斷該countyName 屬於哪一個部分, 比如說 北部
     */
    private fun countyGrouping(countyName: String): String {
        val taipei = "臺北"
        val newNorth = "新北"
        val keelung = "基隆"
        val yilan = "宜蘭"
        val taoyuan = "桃園"
        val hsinchu = "新竹"
        val miaoli = "苗栗"
        val taichung = "臺中"
        val changhua = "彰化"
        val yunlin = "雲林"
        val chiayi = "嘉義"
        val tainan = "台南"
        val kaohsiung = "高雄"
        val pingtung = "屏東"
        val hualien = "花蓮"
        val taitung = "臺東"
        val wuhu = "澎湖"
        val goldenGate = "金門"
        val lianjiang = "連江"
        val north = "北部"
        val central = "中部"
        val south = "南部"
        val east = "東部"
        val outlyingIsland = "離島"
        val unknown = "未知"
        return when (countyName) {
            taipei -> north
            newNorth -> north
            keelung -> north
            yilan -> north
            taoyuan -> north
            hsinchu -> north
            miaoli -> central
            taichung -> central
            changhua -> central
            yunlin -> central
            chiayi -> south
            tainan -> south
            kaohsiung -> south
            pingtung -> south
            hualien -> east
            taitung -> east
            wuhu -> outlyingIsland
            goldenGate -> outlyingIsland
            lianjiang -> outlyingIsland
            else -> unknown
        }
    }

    private fun newNorthData(
        info: List<CateringTourismInformationDatabaseData.XMLHead.Infos.Info>,
        position: Int,
        countyName: String
    ): MainTaiwanGoodTravelData.NorthData {
        return MainTaiwanGoodTravelData.NorthData(
            info[position].name,
            info[position].description,
            countyName
        )
    }

    /** 告訴Activity, 目前顯示的Fragment是哪一個 */
    private fun setActivityCurrentShowFragmentLabel() {
        activity.currentShowFragment = MainActivity.MAIN_TAIWAN_GOOD_TRAVEL_FRAGMENT
    }

    private fun initializeClickListener() {
        binding.previousPageFrameLayout.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.previousPageFrameLayout -> activity.showFragment(MainActivity.MAIN_HOME_PAGE_FRAGMENT)
        }
    }

    /**
     * 判斷此Fragment目前屬於顯示還是隱藏
     * 第二次顯示之後, 每次都會觸發此方法; 第一次顯示, 只會觸發onCreateView
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setActivityCurrentShowFragmentLabel()
            requestGetCateringTourismInformationDatabaseData()
        }
    }
}