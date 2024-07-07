package com.mithilakshar.mithilapanchang.Utility


import android.app.Application
import com.mithilakshar.mithilapanchang.Repository.RingtoneRepository
import com.mithilakshar.mithilapanchang.Room.RingtoneDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication: Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { RingtoneDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { RingtoneRepository(database.ringtonedao()) }
}