package com.mithilakshar.mithilapanchang.Utility


import android.app.Application
import android.content.ContentValues
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.mithilakshar.mithilapanchang.Repository.RingtoneRepository
import com.mithilakshar.mithilapanchang.Room.RingtoneDatabase
import com.mithilakshar.mithilapanchang.Room.Updates
import com.mithilakshar.mithilapanchang.Room.UpdatesDao
import com.mithilakshar.mithilapanchang.UI.Fragments.mayfragment
import com.mithilakshar.mithilapanchang.ViewModel.BhagwatGitaViewModel
import com.mithilakshar.mithilapanchang.databinding.ActivityCalendarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.File

class MyApplication: Application() {






    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { RingtoneDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { RingtoneRepository(database.ringtonedao()) }






}










