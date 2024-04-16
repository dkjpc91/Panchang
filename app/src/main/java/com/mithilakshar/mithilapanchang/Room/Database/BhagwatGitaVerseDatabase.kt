package com.mithilakshar.mithilapanchang.Room.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mithilakshar.mithilapanchang.Room.Dao.BhagwatGitaVerseDao
import com.mithilakshar.mithilapanchang.Room.Entity.BhagwatGitaVerse
import com.mithilakshar.mithilapanchang.Room.Entity.Converter
import com.mithilakshar.mithilapanchang.Room.Entity.Converters


@Database(entities =[BhagwatGitaVerse::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class,Converters::class)
abstract class BhagwatGitaVerseDatabase : RoomDatabase() {

    abstract fun verseDao():BhagwatGitaVerseDao

    companion object {

        private var INSTANCE: BhagwatGitaVerseDatabase? = null

        fun getdbcopy(context: Context): BhagwatGitaVerseDatabase {

            synchronized(this) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BhagwatGitaVerseDatabase::class.java,
                        "BhagwatGitaVerse"
                    ).build()
                }
            }


            return INSTANCE!!

        }


    }

}

