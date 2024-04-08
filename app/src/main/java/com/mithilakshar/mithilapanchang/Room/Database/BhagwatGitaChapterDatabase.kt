package com.mithilakshar.mithilapanchang.Room.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mithilakshar.mithilapanchang.Room.Dao.BhagwatGitaChapterDao
import com.mithilakshar.mithilapanchang.Room.Entity.BhagwatGitaChapter


@Database(entities =[BhagwatGitaChapter::class], version = 1, exportSchema = false)
abstract class BhagwatGitaChapterDatabase :RoomDatabase() {

    abstract fun chapterdao(): BhagwatGitaChapterDao
    companion object{

        private var INSTANCE: BhagwatGitaChapterDatabase?=null

        fun getdbcopy(context: Context):BhagwatGitaChapterDatabase{

            synchronized(this){

                if(INSTANCE==null){

                    INSTANCE = Room.databaseBuilder(context.applicationContext,BhagwatGitaChapterDatabase::class.java,"BhagwatGitaChapter").build()
                }
            }


            return INSTANCE!!

        }





    }

}