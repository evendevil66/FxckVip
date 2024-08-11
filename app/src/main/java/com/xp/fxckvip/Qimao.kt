package com.xp.fxckvip

import android.content.Context
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

class Qimao {
    fun hook(classLoader: ClassLoader) {
        hookVip(classLoader)
    }

    private fun hookVip(classLoader: ClassLoader) {
        try {
            XposedHelpers.findAndHookMethod("rh", classLoader, "isVipUser",
                Context::class.java, object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        super.afterHookedMethod(param)
                        param.result = true
                    }
                })
            XposedHelpers.findAndHookMethod("fx3", classLoader, "Y",
                Context::class.java, object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        super.afterHookedMethod(param)
                        param.result = "1"
                    }
                })
            XposedHelpers.findAndHookMethod("fx3", classLoader, "A0",
                Context::class.java, object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        super.afterHookedMethod(param)
                        param.result = true
                    }
                })
            XposedHelpers.findAndHookMethod(
                "com.qimao.qmservice.reader.entity.CommonBook",
                classLoader,
                "isBookVip",
                object : XC_MethodHook() {

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        super.afterHookedMethod(param)
                        param.result = true
                    }
                })
        } catch (e: Throwable) {
            XposedBridge.log("Error occurred calling hooker on $e")
        }
    }
}