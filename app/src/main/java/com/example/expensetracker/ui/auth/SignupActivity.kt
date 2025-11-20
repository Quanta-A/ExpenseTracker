package com.example.expensetracker.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker.databinding.ActivitySignupBinding
import com.example.expensetracker.viewmodel.AuthViewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var vm: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = AuthViewModel(this)

        binding.btnSignup.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val pass = binding.inputPassword.text.toString()

            vm.signup(email, pass)
            Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
