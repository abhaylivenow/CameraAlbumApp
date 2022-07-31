package com.example.cameraactivity.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cameraactivity.model.PhotoModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class PhotoDatabaseTest : TestCase() {

    private lateinit var db: PhotoDatabase
    private lateinit var dao: PhotoDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PhotoDatabase::class.java).build()
        dao = db.getPhotoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun `add photo to database`() = runBlocking {
        val photo = PhotoModel(1, "some location" ,LocalDateTime.now().toString(),"1")
        dao.insertPhoto(photo)
        val photos = dao.getPhotoByAlbum("1")
        assertTrue(photos.contains(photo))
    }
}