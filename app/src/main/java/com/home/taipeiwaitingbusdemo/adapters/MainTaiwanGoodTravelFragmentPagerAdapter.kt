package com.home.taipeiwaitingbusdemo.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.home.taipeiwaitingbusdemo.fragments.MainTaiwanGoodTravelTabLayoutItemFragment
import com.home.taipeiwaitingbusdemo.data.MainTaiwanGoodTravelData

class MainTaiwanGoodTravelFragmentPagerAdapter(
    private val northDataList: List<MainTaiwanGoodTravelData.NorthData>,
    private val centralDataList: List<MainTaiwanGoodTravelData.NorthData>,
    private val southDataList: List<MainTaiwanGoodTravelData.NorthData>,
    private val eastDataList: List<MainTaiwanGoodTravelData.NorthData>,
    private val outlyingIslandDataList: List<MainTaiwanGoodTravelData.NorthData>,
    fragmentManager: FragmentManager?
) : FragmentStatePagerAdapter(fragmentManager) {

    private val north = "北部"
    private val central = "中部"
    private val south = "南部"
    private val east = "東部"
    private val outlyingIsland = "離島"
    private var tabTitles = arrayOf(north, central, south, east, outlyingIsland)

    override fun getCount(): Int {
        return tabTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    override fun getItem(position: Int): Fragment? {
        val north = 0
        val central = 1
        val south = 2
        val east = 3
        val outlyingIsland = 4
        return when (position) {
            north -> MainTaiwanGoodTravelTabLayoutItemFragment(northDataList)
            central -> MainTaiwanGoodTravelTabLayoutItemFragment(centralDataList)
            south -> MainTaiwanGoodTravelTabLayoutItemFragment(southDataList)
            east -> MainTaiwanGoodTravelTabLayoutItemFragment(eastDataList)
            outlyingIsland -> MainTaiwanGoodTravelTabLayoutItemFragment(outlyingIslandDataList)
            else -> null
        }
    }
}