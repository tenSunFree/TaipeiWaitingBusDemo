package com.home.taipeiwaitingbusdemo.utility

object ClickEventUtility {

    private const val minClickDelayTime = 1000 // 兩次點擊按鈕之間的點擊間隔不能少於1000毫秒
    private var lastClickTime: Long = 0

    /** 避免使用者連續快速地多次點擊, 關鍵的才使用, 比如說伺服器請求..等耗時的 */
    fun avoidFastMultipleClicks(): Boolean {
        var flag = false
        val curClickTime = System.currentTimeMillis()
        if (curClickTime - lastClickTime >= minClickDelayTime) {
            flag = true
        }
        lastClickTime = curClickTime
        return flag
    }
}