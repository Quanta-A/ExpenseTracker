package com.example.expensetracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.expensetracker.api.AuthApi

class AuthViewModel(context: Context) : ViewModel() {

    private val api = AuthApi(context)

    fun login(email: String, password: String): Boolean =
        api.login(email, password)

    fun signup(email: String, password: String): Boolean =
        api.signup(email, password)

    fun logout() = api.logout()
}
