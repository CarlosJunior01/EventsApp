package com.carlosjr.eventsapp.helper.extensions

import android.app.Activity
import android.content.Intent
import android.widget.Toast

fun Activity.openActivity(activity: Any) = startActivity(Intent(this, activity::class.java))

fun Activity.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()



