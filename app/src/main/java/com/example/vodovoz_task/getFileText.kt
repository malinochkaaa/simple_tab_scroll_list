package com.example.vodovoz_task

import android.content.ContentResolver
import android.net.Uri
import java.net.URL
import java.util.*

fun getFileText(contentResolver: ContentResolver): String {
    val url = URL("http://szorinvodovoz.tw1.ru/newmobile/glavnaya/super_top.php?action=topglav")
    return url.openStream().bufferedReader().use { it.readText() }
}