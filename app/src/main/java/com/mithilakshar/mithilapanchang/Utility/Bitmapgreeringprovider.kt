package com.mithilakshar.mithilapanchang.Utility

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Bitmapgreeringprovider : ContentProvider() {

    companion object {
        private const val TAG = "Bitmapgreeringprovider"

        private var bitmapUri: Uri? = null
        private var bitmap: Bitmap? = null

        fun fetchBitmapFromUrl(url: String, callback: (Bitmap?) -> Unit) {
            Thread {
                var bmp: Bitmap? = null
                try {
                    val connection = URL(url).openConnection() as HttpURLConnection
                    connection.inputStream.use { inputStream ->
                        bmp = BitmapFactory.decodeStream(inputStream)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error fetching bitmap from URL", e)
                }
                callback(bmp)
            }.start()
        }

        fun setBitmap(uri: Uri, bitmap: Bitmap) {
            bitmapUri = uri
            this.bitmap = bitmap
        }

        fun getBitmap(uri: Uri): Bitmap? {
            return if (uri == bitmapUri) bitmap else null
        }
    }

    override fun onCreate(): Boolean = true

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? = null

    override fun getType(uri: Uri): String = "image/png"

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun openFile(uri: Uri, mode: String): ParcelFileDescriptor? {
        return try {
            val file = File(context?.cacheDir, "shared_image.png")
            FileOutputStream(file).use { outputStream ->
                bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }
            ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        } catch (e: Exception) {
            Log.e(TAG, "Error opening file", e)
            null
        }
    }
}
