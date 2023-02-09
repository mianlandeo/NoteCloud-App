package com.example.notecloud.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.example.notecloud.R
import com.google.android.material.snackbar.Snackbar

fun Context.priorityView(priority: Int): Int{
    return when(priority){
        1 -> ContextCompat.getColor(this, R.color.color_green)
        2 -> ContextCompat.getColor(this, R.color.color_orange)
        else -> ContextCompat.getColor(this, R.color.color_red)
    }
}

fun View.snackBar(msg : String){
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).also { snackbar ->
        snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.white))
        snackbar.setTextColor(Color.BLACK)
        snackbar.dismiss()
    }.show()
}