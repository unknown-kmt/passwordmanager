package com.kmt.passwordmanager.passwordsScreen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kmt.passwordmanager.R
import com.kmt.passwordmanager.database.model.AuthInfoRecord


class AuthInfoAdapter : RecyclerView.Adapter<AuthInfoViewHolder>() {

    var data = listOf<AuthInfoRecord>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthInfoViewHolder {
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_auth_info, parent, false) as LinearLayout
        return AuthInfoViewHolder(card)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: AuthInfoViewHolder, position: Int) {
        val item = data[position]

        // name bind
        holder.nameField.text = item.name

        // Login bind
        holder.loginField.setOnClickListener(View.OnClickListener { view ->
            onLoginInfoClickHandler(view, holder.loginField, item)
        })

        // Password bind
        holder.passwordField.setOnClickListener(View.OnClickListener { view ->
            onPasswordInfoClickHandler(view, holder.passwordField, item)
        })

        captionUpdating(holder, item)
        urlButtonUpdate(holder, item)

        holder.editButton.setOnClickListener(View.OnClickListener { view ->
            view.findNavController().navigate(
                PasswordsFragmentDirections.actionPasswordsFragmentToEditRecordFragment(item.id)
            )
        })
    }

    /**
     * URL button binding
     */
    private fun urlButtonUpdate(holder: AuthInfoViewHolder, item: AuthInfoRecord) {
        if (item.url.isEmpty()) {
            holder.urlButton.isVisible = false
            return
        }

        holder.urlButton.setOnClickListener(View.OnClickListener { view ->
            val address: Uri = Uri.parse(item.url)
            val intent = Intent(Intent.ACTION_VIEW, address)

            if (intent.resolveActivity(view.context.packageManager) == null) {
                Toast.makeText(view.context, R.string.cant_open_link, Toast.LENGTH_LONG).show()
            } else {
                startActivity(view.context, intent, null)
            }
        })
    }

    private fun captionUpdating(holder: AuthInfoViewHolder, item: AuthInfoRecord) {
        if (item.caption.isEmpty()) {
            holder.captionText.isVisible = false
            holder.captionButton.isVisible = false
            return
        }

        holder.captionButton.setOnClickListener(View.OnClickListener {
            if (holder.captionText.text.isEmpty()) {
                holder.captionText.text = item.caption
            } else {
                holder.captionText.text = ""
            }
        })
    }

    private fun onLoginInfoClickHandler(
        view: View,
        field: EditText,
        authInfoRecord: AuthInfoRecord
    ) {
        field.setText(authInfoRecord.login, TextView.BufferType.NORMAL)
        copyToClipboard(view, authInfoRecord.login)
        Toast.makeText(view.context, R.string.login_copied, Toast.LENGTH_SHORT).show()
    }

    private fun onPasswordInfoClickHandler(
        view: View,
        field: EditText,
        authInfoRecord: AuthInfoRecord
    ) {
        field.setText(authInfoRecord.password, TextView.BufferType.NORMAL)
        copyToClipboard(view, authInfoRecord.password)
        Toast.makeText(view.context, R.string.password_copied, Toast.LENGTH_SHORT).show()
    }

    private fun copyToClipboard(view: View, text: String) {
        val clipboard =
            getSystemService(view.context, ClipboardManager::class.java) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.setPrimaryClip(clip)
    }
}