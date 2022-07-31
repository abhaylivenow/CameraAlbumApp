package com.example.cameraactivity.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cameraactivity.model.PhotoModel

@Database(
    entities = [PhotoModel::class],
    version = 1,
    exportSchema = false
)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun getPhotoDao() : PhotoDao

    companion object {
        private const val DB_NAME = "photo_database.db"
        @Volatile private var instance: PhotoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PhotoDatabase::class.java,
            DB_NAME
        ).build()
    }
}