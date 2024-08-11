package com.xp.fxckvip

import android.widget.Toast
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers


class DragonRead {
    fun hook(classLoader: ClassLoader) {
        hookCreate(classLoader)
        hookAd(classLoader)
        hookVip(classLoader)
        hookUpdate(classLoader)

    }

    private fun hookCreate(classLoader: ClassLoader) {
        XposedHelpers.findAndHookMethod(
            "com.dragon.read.pages.main.MainFragmentActivity",
            classLoader,
            "onCreate",
            android.os.Bundle::class.java,
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam) {
                    //调用原方法
                    super.afterHookedMethod(param)
                    //获取当前的Activity
                    val activity = param.thisObject as android.app.Activity
                    //调用showToast方法
                    Toast.makeText(activity, "番茄FxckVip模块加载成功！", Toast.LENGTH_SHORT).show()
                }
            }
        )
//        XposedHelpers.findAndHookMethod(
//            "com.dragon.read.reader.ui.ReaderActivity",
//            classLoader,
//            "onCreate",
//            android.os.Bundle::class.java,
//            object : XC_MethodHook() {
//                override fun afterHookedMethod(param: MethodHookParam) {
//                    //调用原方法
//                    super.afterHookedMethod(param)
//                    //获取当前的Activity
//                    val activity = param.thisObject as android.app.Activity
//                    //调用showToast方法
//                    Toast.makeText(activity, "FxckVip去广告已启用", Toast.LENGTH_SHORT).show()
//                }
//            }
//        )
    }


    private fun hookAd(classLoader: ClassLoader) {
        try {
            classLoader.loadClass("com.dragon.read.component.biz.impl.g.e")
            XposedHelpers.findAndHookMethod("com.dragon.read.component.biz.impl.g.e",
                classLoader,
                "isNoAd",
                String::class.java,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        super.afterHookedMethod(param)
                        param.result = true
                    }
                })
            classLoader.loadClass("com.dragon.read.component.biz.impl.h.e")
            XposedHelpers.findAndHookMethod("com.dragon.read.component.biz.impl.h.e",
                classLoader,
                "isNoAd",
                String::class.java,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        super.afterHookedMethod(param)
                        param.result = true
                    }
                })
            classLoader.loadClass("com.dragon.read.component.biz.impl.i.e")
            XposedHelpers.findAndHookMethod("com.dragon.read.component.biz.impl.i.e",
                classLoader,
                "isNoAd",
                String::class.java,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        super.afterHookedMethod(param)
                    }
                })
            XposedHelpers.findAndHookMethod(
                "com.dragon.read.component.biz.impl.i.e",
                classLoader,
                "hasNoAdFollAllScene",
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        super.afterHookedMethod(param)
                        param.result = true
                    }
                })
        } catch (e: Exception) {
            XposedBridge.log("com.dragon.read.component.biz.impl.g.e:${e.message}")
        }

    }

    private fun hookVip(classLoader: ClassLoader) {
        val vipSubTypeClass = Class.forName("com.dragon.read.rpc.model.VipSubType", false, classLoader)
        XposedHelpers.findAndHookConstructor("com.dragon.read.user.model.VipInfoModel",
            classLoader,
            String::class.java,
            String::class.java,
            String::class.java,
            Boolean::class.java,
            Boolean::class.java,
            Int::class.java,
            Boolean::class.java,
            vipSubTypeClass,
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun beforeHookedMethod(param: MethodHookParam) {
                    val args = param.args
//                    this.expireTime = str;
//                    this.isVip = str2;
//                    this.leftTime = str3;
//                    this.isAutoCharge = z;
//                    this.isUnionVip = z2;
//                    this.unionSource = i2;
//                    this.isAdVip = z3;
//                    this.subType = vipSubType;
                    args[0] = "3743662698"
                    args[1] = "1"
                    args[2] = "999999"
                    args[3] = false
                    args[4] = true
                    args[5] = 1
                    args[6] = true
                }

                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    val args = param.args
                    val expireTime = args[0] as String
                    val isVip = args[1] as String
                    val leftTime = args[2] as String
                    val isAutoCharge = args[3] as Boolean
                }
            })
    }

    private fun hookUpdate(classLoader: ClassLoader) {
        try {
            XposedHelpers.findAndHookMethod(
                "com.dragon.read.update.b",
                classLoader,
                "b",
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        param.result = false
                    }
                })
        } catch (e: Throwable) {
            XposedBridge.log("Error occurred calling hooker on $e")
        }
    }
}