package com.home.taipeiwaitingbusdemo

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.home.taipeiwaitingbusdemo.databinding.ActivityMainBinding
import com.home.taipeiwaitingbusdemo.fragments.MainHomePageFragment
import com.home.taipeiwaitingbusdemo.fragments.MainTaiwanGoodTravelFragment
import com.home.taipeiwaitingbusdemo.utility.toast
import com.home.taipeiwaitingbusdemo.component.LoadingDialog

class MainActivity : AppCompatActivity() {

    companion object {
        const val MAIN_HOME_PAGE_FRAGMENT: String = "MainHomePageFragment"
        const val MAIN_TAIWAN_GOOD_TRAVEL_FRAGMENT: String = "MainTaiwanGoodTravelFragment"
    }

    private var binding: ActivityMainBinding? = null // 關於類型後面加一個問號, 表示該變量是Nullable, 不加表示該變量不可為null
    private var mainHomePageFragment: MainHomePageFragment? = null
    private var mainTaiwanGoodTravelFragment: MainTaiwanGoodTravelFragment? = null
    var currentShowFragment: String? = null // 提供判斷目前activity是顯示哪一個fragment
    private var firstCloseAppCurrentTimeMillis: Long = 0 // 第一次點擊返回鍵, 關閉app的時間
    private val closeAppTimeInterval: Long = 2000 // 在2秒內再次點擊返回鍵, 則關閉app
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        showFragment(MAIN_HOME_PAGE_FRAGMENT) // 預設是載入首頁
    }

    /**
     * 彈出讀取中的訊息框
     */
    fun createLoadingDialog() {
        binding!!.containerFrameLayout.post {
            loadingDialog = LoadingDialog(this, R.style.LoadingDialog, getScreenWidth())
            val view = View.inflate(this, R.layout.dialog_loading, null)
            loadingDialog.setView(view)
            loadingDialog.setCanceledOnTouchOutside(false) // 點擊周圍留白處不消失
            loadingDialog.setCancelable(false) // 點擊返回鍵不消失
            loadingDialog.show()
        }
    }

    /**
     * 關閉讀取中的訊息框
     */
    fun dismissLoadingDialog() {
        val delayMillis = 200
        binding!!.containerFrameLayout.postDelayed({
            if (loadingDialog.isShowing) {
                loadingDialog.dismiss()
            }
        }, delayMillis.toLong())
    }

    /**
     * 取得螢幕的寬度
     */
    private fun getScreenWidth(): Int {
        return binding!!.containerFrameLayout.width
    }

    /**
     * 顯示Fragment
     * 多個Fragment切換時, 避免重複創建Fragment
     */
    fun showFragment(fragmentName: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction() // 開啟事務
        hideFragment(fragmentTransaction) // 先隱藏所有的Fragment
        when (fragmentName) {
            MAIN_HOME_PAGE_FRAGMENT -> // 首頁
                if (mainHomePageFragment == null) { // 如果Fragment為空, 就新建一個實例
                    mainHomePageFragment = MainHomePageFragment.newInstance(this)
                    fragmentTransaction.add(R.id.containerFrameLayout, mainHomePageFragment!!) // 執行事務, 添加Fragment
                } else { // 如果不為空, 就將它從棧中顯示出來
                    fragmentTransaction.show(mainHomePageFragment!!)
                }
            MAIN_TAIWAN_GOOD_TRAVEL_FRAGMENT -> // 台灣好行
                if (mainTaiwanGoodTravelFragment == null) { // 如果Fragment為空, 就新建一個實例
                    mainTaiwanGoodTravelFragment = MainTaiwanGoodTravelFragment.newInstance(this)
                    fragmentTransaction.add(
                        R.id.containerFrameLayout,
                        mainTaiwanGoodTravelFragment!!
                    ) // 執行事務, 添加Fragment
                } else { // 如果不為空, 就將它從棧中顯示出來
                    fragmentTransaction.show(mainTaiwanGoodTravelFragment!!)
                }
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    /**
     * 隱藏Fragment
     */
    private fun hideFragment(fragmentTransaction: FragmentTransaction) {
        if (mainHomePageFragment != null) { // 如果不為空, 就先隱藏起來
            fragmentTransaction.hide(mainHomePageFragment!!)
        }
        if (mainTaiwanGoodTravelFragment != null) { // 如果不為空, 就先隱藏起來
            fragmentTransaction.hide(mainTaiwanGoodTravelFragment!!)
        }
    }

    /**
     * 根據currentFragment判斷目前顯示的是哪一個Fragment, 並執行對應的行為
     */
    override fun onBackPressed() {
        when (currentShowFragment) {
            MAIN_HOME_PAGE_FRAGMENT -> { // 2秒內點擊2次返回鍵, 才會退出app
                if (firstCloseAppCurrentTimeMillis + closeAppTimeInterval > System.currentTimeMillis()) {
                    finish()
                } else {
                    toast(R.string.activity_main_again_click)
                }
                firstCloseAppCurrentTimeMillis = System.currentTimeMillis()
            }
            MAIN_TAIWAN_GOOD_TRAVEL_FRAGMENT -> showFragment(MAIN_HOME_PAGE_FRAGMENT)
        }
    }
}
