package com.luc.powermock

import android.util.Log

object CollaboratorWithStaticMethods {

    @JvmStatic
    fun firstMethod(name: String): String? {
        return "Hello $name !"
    }

    @JvmStatic
    fun secondMethod(): String? {
        return "Hello no one!"
    }

    fun thirdMethod() {
        var test = "sds"
        var test2 = "dsdsds"
        Log.d("tag", "hello my home")
    }
}