package com.informatica.chatapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.informatica.chatapplication.databinding.ActivitySignupBinding
import com.informatica.chatapplication.ktx.toast
import com.informatica.chatapplication.model.UserModel

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignupBinding
    private lateinit var dbRef: DatabaseReference
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        binding.btSignup.setOnClickListener {
            registerUser()
            saveUserInfo()
        }
    }

    private fun registerUser() {

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                    if (it.isSuccessful){
                        toast("User signup successfully")
                        finish()
                    }
                    else{
                        toast("Something went wrong")
                        println("${Exception().message}")
                    }
            }
        }

    }

    private fun saveUserInfo(){

        with(binding)
        {
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()
            var fullName = etFullName.text.toString()
            var phoneNo = etPhoneNo.text.toString()

            if (
                email.isNullOrEmpty() or password.isNullOrEmpty() or
                fullName.isNullOrEmpty() or phoneNo.isNullOrEmpty()
                )
            {
                toast("Something went wrong")
            }
            else
            {
                var userId = dbRef.push().key!!
                var user = UserModel(userId,email,password,fullName,phoneNo)

                dbRef.child(userId).setValue(user)
                    .addOnCompleteListener {
                        toast("Data inserted successfully")
                    }.addOnFailureListener { err->
                        toast("Error is ${Exception().message}")
                    }

            }
        }

    }
}