package com.carlosjr.eventsapp.helper.extensions

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun Activity.openActivity(activity: Any) = startActivity(Intent(this, activity::class.java))

fun Activity.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Activity.hideKeyboard(view: View) {
    val inputManager = this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
}


