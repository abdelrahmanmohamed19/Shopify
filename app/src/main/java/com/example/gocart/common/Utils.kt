package com.example.gocart.common

import android.content.Context
import android.view.View
import com.example.gocart.R
import com.google.android.material.snackbar.Snackbar

class Utils {

    companion object {

        fun showSnackbar(view: View, text: String, action: () -> Unit, context: Context) {
            Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") { action.invoke() }
                .setBackgroundTint(context.getColor(R.color.white))
                .setTextColor(context.getColor(R.color.main))
                .setActionTextColor(context.getColor(R.color.main))
                .show()
        }

    }
}