package com.informatica.chatapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.informatica.chatapplication.databinding.ActivityLoginBinding
import com.informatica.chatapplication.ktx.gotoActivity
import com.informatica.chatapplication.ktx.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btLogin.setOnClickListener {
            loginUser()
        }

        binding.tvSignup.setOnClickListener {
            gotoActivity(SignupActivity::class.java)
        }
    }

    private fun loginUser() {

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful){
                    toast("User signin successfully")
                    gotoActivity(MessageScreenActivity::class.java)
                }
                else{
                    toast("Something went wrong")
                    println("${Exception().message}")
                }
            }
        }

    }

}