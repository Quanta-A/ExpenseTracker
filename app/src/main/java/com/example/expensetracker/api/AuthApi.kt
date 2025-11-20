package com.example.expensetracker.api

import android.content.Context
import android.content.SharedPreferences

class AuthApi(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    fun signup(email: String, password: String): Boolean {
        prefs.edit().putString("email", email).putString("password", password).apply()
        return true
    }

    fun login(email: String, password: String): Boolean {
        return prefs.getString("email", "") == email &&
                prefs.getString("password", "") == password
    }

    fun logout() {
        prefs.edit().clear().apply()
    }
}
