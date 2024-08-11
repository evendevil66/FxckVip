package com.xp.fxckvip

import android.widget.Toast
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
class MainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        //创建包名列表
        val packageList = arrayOf(
            "com.dragon.read",
            "com.kmxs.reader"
        )
        //匹配包名 不存在则return
        if (!packageList.contains(lpparam.packageName)) return
        // 执行Hook
        XposedBridge.log("FxckVip模块加载成功！")
        hook(lpparam)

    }

    private fun hook(lpparam: XC_LoadPackage.LoadPackageParam) {
        //遍历lpparam的packageName分配给对应的处理类
        when (lpparam.packageName) {
            "com.dragon.read" -> {
                val dragonRead = DragonRead()
                dragonRead.hook(lpparam.classLoader)
            }
            "com.kmxs.reader" -> {
                val qimao = Qimao()
                qimao.hook(lpparam.classLoader)
            }
        }
    }
}