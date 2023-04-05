package com.kmt.passwordmanager.passwordsScreen

import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kmt.passwordmanager.R

class AuthInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameField: TextView = itemView.findViewById(R.id.name)
    val urlButton: ImageButton = itemView.findViewById(R.id.goToWebsite)
    val editButton: ImageButton = itemView.findViewById(R.id.editRecord)
    val loginField: EditText = itemView.findViewById(R.id.login)
    val passwordField: EditText = itemView.findViewById(R.id.password)
    val captionField: EditText = itemView.findViewById(R.id.caption)
}