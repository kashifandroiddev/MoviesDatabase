package com.common.network

import android.content.Context
import android.os.Build
import android.provider.Settings
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONException
import org.json.JSONObject
import java.util.Random

object NetworkUtils {
    fun getErrorResponse(response: Response): Response {
        val message = "{\"message\": \"Something went wrong.\nError Code: entx30013\"}"
        val emptyBody = message.toResponseBody("text/plain".toMediaType())
        return response.newBuilder()
            .code(response.code)
            .body(emptyBody).build()
    }

    fun isJSONValid(jsonObject: String?): Boolean {
        if (jsonObject == null) return false
        try {
            try {
                JSONObject(jsonObject)
            } catch (e: JSONException) {
                //NetworkSDK.networkEventListener?.onLogException(e)
                return false
            }
        } catch (e: Exception) {
            //NetworkSDK.networkEventListener?.onLogException(e)
            e.printStackTrace()
            return false
        }
        return true
    }

    private val ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm"
    fun getDeviceUDID(context: Context): String {
//        if(NetworkSDK.getNetworkConfig().isDeviceResetEnabled() && !NetworkSDK.getNetworkConfig().getCustomDeviceId().isNullOrEmpty())
//            return NetworkSDK.getNetworkConfig().getCustomDeviceId()?:""
        try {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                Settings.Secure.getString(context.contentResolver,
                    Settings.Secure.ANDROID_ID)
            } else {
                return ""
            }
        } catch (ex: Exception) {
            return ""
        }

    }

    fun replaceApiChar(s: String?): String? {
        return s?.replace("+", "-")?.replace("/", "_")?.replace("=", ",")
    }

    fun undoReplaceApiChar(s: String?): String? {
        return s?.replace("-", "+")?.replace("_", "/")?.replace(",", "=")
    }


    fun getDeviceModel(): String {
        try {
            val deviceName = Build.MODEL
            val deviceMan = Build.MANUFACTURER
            return "$deviceMan $deviceName".replace(" ", "%20")
        } catch (ignore: Exception) {
        }

        return ""
    }



    fun getRandomNetworkId(): String {
        val random = Random()
        val sb = StringBuilder(25)
        for (i in 0 until 25)
            sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        return "NID_"+sb.toString()
    }
    
}