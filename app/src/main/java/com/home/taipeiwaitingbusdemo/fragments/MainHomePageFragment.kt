package com.home.taipeiwaitingbusdemo.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.home.taipeiwaitingbusdemo.MainActivity
import com.home.taipeiwaitingbusdemo.R
import com.home.taipeiwaitingbusdemo.databinding.FragmentMainHomePageBinding
import com.home.taipeiwaitingbusdemo.utility.ClickEventUtility
import com.home.taipeiwaitingbusdemo.utility.toast

/**
 * 首頁
 */
class MainHomePageFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(activity: MainActivity): MainHomePageFragment {
            val fragment = MainHomePageFragment()
            fragment.activity = activity
            return fragment
        }
    }

    lateinit var activity: MainActivity
    lateinit var binding: FragmentMainHomePageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_home_page, container, false)
        setActivityCurrentShowFragmentLabel()
        initializeClickListener()
        return binding.root
    }

    /** 告訴Activity, 目前顯示的Fragment是哪一個 */
    private fun setActivityCurrentShowFragmentLabel() {
        activity.currentShowFragment = MainActivity.MAIN_HOME_PAGE_FRAGMENT
    }

    private fun initializeClickListener() {
        binding.nineButtonBarInclude.publicBicyclesLinearLayout.setOnClickListener(this)
        binding.nineButtonBarInclude.mrtRoadMapLinearLayout.setOnClickListener(this)
        binding.nineButtonBarInclude.taiwanGoodTravelLinearLayout.setOnClickListener(this)
        binding.nineButtonBarInclude.trainTimetableLinearLayout.setOnClickListener(this)
        binding.nineButtonBarInclude.busTrackingLinearLayout.setOnClickListener(this)
        binding.nineButtonBarInclude.dataBackupLinearLayout.setOnClickListener(this)
        binding.nineButtonBarInclude.airportPassengerTransportLinearLayout.setOnClickListener(this)
        binding.nineButtonBarInclude.highSpeedRailTimetableLinearLayout.setOnClickListener(this)
        binding.nineButtonBarInclude.settingLinearLayout.setOnClickListener(this)
        binding.fourButtonBarInclude.routeSearchLinearLayout.setOnClickListener(this)
        binding.fourButtonBarInclude.nearbySignLinearLayout.setOnClickListener(this)
        binding.fourButtonBarInclude.routePlanningLinearLayout.setOnClickListener(this)
        binding.fourButtonBarInclude.commonSignLinearLayout.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.publicBicyclesLinearLayout -> toast(R.string.functional_development)
            R.id.mrtRoadMapLinearLayout -> toast(R.string.functional_development)
            R.id.taiwanGoodTravelLinearLayout -> {
                if (ClickEventUtility.avoidFastMultipleClicks()) {
                    activity.createLoadingDialog()
                    activity.showFragment(MainActivity.MAIN_TAIWAN_GOOD_TRAVEL_FRAGMENT)
                }
            }
            R.id.trainTimetableLinearLayout -> toast(R.string.functional_development)
            R.id.busTrackingLinearLayout -> toast(R.string.functional_development)
            R.id.dataBackupLinearLayout -> toast(R.string.functional_development)
            R.id.airportPassengerTransportLinearLayout -> toast(R.string.functional_development)
            R.id.highSpeedRailTimetableLinearLayout -> toast(R.string.functional_development)
            R.id.settingLinearLayout -> toast(R.string.functional_development)
            R.id.routeSearchLinearLayout -> toast(R.string.functional_development)
            R.id.nearbySignLinearLayout -> toast(R.string.functional_development)
            R.id.routePlanningLinearLayout -> toast(R.string.functional_development)
            R.id.commonSignLinearLayout -> toast(R.string.functional_development)
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
        }
    }
}