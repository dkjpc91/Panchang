package com.mithilakshar.mithilapanchang.Models

data class SavedData(
    val ringtone: String,
    val time: String,
    val key: String // This key can be used to identify and delete the saved entry from SharedPreferences
)