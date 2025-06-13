package ru.glindaquint.everwell.utils

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

@SuppressLint("ServiceCast")
fun copyToClipboard(
    context: Context,
    text: String,
) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText("invite_link", text))
    Toast.makeText(context, "Copied! $text", Toast.LENGTH_SHORT).show()
}
