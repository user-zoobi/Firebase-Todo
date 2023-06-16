package com.informatica.chatapplication.ktx

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun <T : AppCompatActivity> AppCompatActivity.gotoActivity(targetActivityClass: Class<T>) {
    val intent = Intent(this, targetActivityClass)
    startActivity(intent)
}

fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}