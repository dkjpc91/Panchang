package com.mithilakshar.mithilapanchang.Utility

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.io.File


class dbHelper(context: Context, dbName: String) {

    private val TAG = "DBHelper"
    val dbFolderPath = context.getExternalFilesDir(null)?.absolutePath + File.separator + "test"
    val dbFilePath = "$dbFolderPath/$dbName"
    private var db: SQLiteDatabase? = null

    init {
        try {
            db = SQLiteDatabase.openDatabase(dbFilePath, null, SQLiteDatabase.OPEN_READWRITE)
        } catch (e: Exception) {
            Log.e(TAG, "Error opening database", e)
        }
    }

    fun getTableNames(): List<String> {
        val tableNames = mutableListOf<String>()
        db?.let {
            val cursor = it.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name != 'android_metadata'", null)
            try {
                cursor.use { c ->
                    while (c.moveToNext()) {
                        val tableName = c.getString(0)
                        tableNames.add(tableName)
                    }
                }
            } finally {
                cursor.close()  // Close the cursor even if an exception occurs
            }
        }
        return tableNames
    }


    @SuppressLint("Range")
    fun getColumnNames(tableName: String): List<String> {
        val columnNames = mutableListOf<String>()
        try {
            db?.let { db ->
                if (!db.isOpen) {
                    // Handle case where database is not open
                    return emptyList()
                }
                val cursor = db.rawQuery("PRAGMA table_info($tableName)", null)

                cursor.use { c ->
                    while (c.moveToNext()) {
                        val columnName = c.getString(c.getColumnIndex("name"))
                        columnNames.add(columnName)
                        Log.d(TAG, "Column Name: $columnName")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching column names", e)
            // Handle exception (e.g., throw or return empty list)
        } finally {
            //db?.close()
        }
        return columnNames
    }



    fun getAllTableData(tableName: String): List<Map<String, Any?>> {
        val dataList = mutableListOf<Map<String, Any?>>()
        db?.let { database ->
            if (!database.isOpen) {
                Log.w(TAG, "Database not open for table: $tableName")
                return emptyList()
            }

            val cursor = database.rawQuery("SELECT * FROM $tableName", null)
            cursor.use { c ->
                val columnNames = getColumnNames(tableName)  // Reuse existing function for column names
                while (c.moveToNext()) {
                    val rowData = mutableMapOf<String, Any?>()
                    for (i in 0 until columnNames.size) {
                        val columnName = columnNames[i]
                        val value = c.getString(i)  // Assuming all columns are string for simplicity
                        rowData[columnName] = value
                    }
                    dataList.add(rowData)
                }
            }
        }
        return dataList
    }


    fun getRowCount(tableName: String): Int {
        var rowCount = 0
        db?.let {
            val cursor = it.rawQuery("SELECT COUNT(*) FROM $tableName", null)
            cursor.use { c ->
                if (c.moveToFirst()) {
                    rowCount = c.getInt(0)
                }
            }
        }
        return rowCount
    }

    fun getRowValues(tableName: String, primaryKeyValue: Any): List<Any>? {
        var rowValues: MutableList<Any>? = null // Use MutableList instead of List
        db?.let {
            val cursor = it.rawQuery("SELECT * FROM $tableName WHERE id = ?", arrayOf(primaryKeyValue.toString()))
            cursor.use { c ->
                if (c.moveToFirst()) {
                    rowValues = mutableListOf()
                    do {
                        for (i in 0 until c.columnCount) {
                            val value = when (c.getType(i)) {
                                Cursor.FIELD_TYPE_NULL -> null
                                Cursor.FIELD_TYPE_INTEGER -> c.getLong(i)
                                Cursor.FIELD_TYPE_FLOAT -> c.getDouble(i)
                                Cursor.FIELD_TYPE_STRING -> c.getString(i)
                                Cursor.FIELD_TYPE_BLOB -> c.getBlob(i)
                                else -> null
                            }
                            rowValues!!.add(value ?: "") // Add value to the mutable list
                        }
                    } while (c.moveToNext())
                }
            }
        }
        return rowValues
    }

    @SuppressLint("Range")
    fun getHolidaysByMonth(monthName: String): List<Map<String, String>> {
        val holidays = mutableListOf<Map<String, String>>()
        db?.let { database ->
            if (!database.isOpen) {
                Log.w(TAG, "Database not open for reading holidays for month: $monthName")
                return emptyList()
            }

            val query = "SELECT * FROM holiday WHERE month = ?"
            val selectionArgs = arrayOf(monthName)

            database.rawQuery(query, selectionArgs)?.use { cursor ->
                while (cursor.moveToNext()) {
                    val rowData = mutableMapOf<String, String>()
                    val month = cursor.getString(cursor.getColumnIndex("month"))
                    val value1 = cursor.getString(cursor.getColumnIndex("date"))
                    val value2 = cursor.getString(cursor.getColumnIndex("name"))
                    val value3 = cursor.getString(cursor.getColumnIndex("desc"))
                    rowData["month"] = month
                    rowData["date"] = value1
                    rowData["name"] = value2
                    rowData["desc"] = value3
                    holidays.add(rowData)
                }
            }
        }

        if (holidays.isEmpty()) {
            // Example: Return a default value indicating no holidays found
            val defaultHoliday = mutableMapOf<String, String>()
            defaultHoliday["month"] = monthName
            defaultHoliday["date"] = "वर्तमान मास आ अगिला मास के चुनाव करू।"
            defaultHoliday["name"] = "ई महीना के जल्द अपडेट उपलब्ध हैत। मिथिला पंचांग स जुरल रहू |"
            holidays.add(defaultHoliday)
        }

        return holidays
    }

    data class Chapter(val uid: String, val chapterName: String,val chapternumber:String, val description: String)


    @SuppressLint("Range")
    fun getChapterNames(): List<Chapter> {
        val chapters = mutableListOf<Chapter>()
        db?.let { database ->
            try {
                if (!database.isOpen) {
                    Log.w(TAG, "Database not open for reading chapter names")
                    return emptyList()
                }

                val query = "SELECT DISTINCT uid, chaptername, Chapternumber, chapterdescription FROM Gita"
                database.rawQuery(query, null)?.use { cursor ->
                    while (cursor.moveToNext()) {
                        val uidIndex = cursor.getColumnIndexOrThrow("uid")
                        val chapterNameIndex = cursor.getColumnIndexOrThrow("chaptername")
                        val chapterNumberIndex = cursor.getColumnIndexOrThrow("Chapternumber")
                        val descriptionIndex = cursor.getColumnIndexOrThrow("chapterdescription")

                        val uid = if (!cursor.isNull(uidIndex)) {
                            cursor.getString(uidIndex)
                        } else {
                            "Unknown UID"
                        }

                        val chapterName = if (!cursor.isNull(chapterNameIndex)) {
                            cursor.getString(chapterNameIndex)
                        } else {
                            "Unknown Chapter Name"
                        }

                        val chapterNumber = if (!cursor.isNull(chapterNumberIndex)) {
                            cursor.getInt(chapterNumberIndex)
                        } else {
                            0 // Default value if Chapternumber is null
                        }

                        val description = if (!cursor.isNull(descriptionIndex)) {
                            cursor.getString(descriptionIndex)
                        } else {
                            "No Description Available"
                        }

                        chapters.add(Chapter(uid, chapterName, chapterNumber.toString(), description))
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching chapter names", e)
                e.printStackTrace()
            }
        } ?: Log.e(TAG, "Database is null!")

        return chapters
    }



    @SuppressLint("Range")
    fun getRowsByChapterName(chapterName: String): List<Map<String, Any?>> {
        val rows = mutableListOf<Map<String, Any?>>()
        db?.let { database ->
            if (!database.isOpen) {
                Log.w(TAG, "Database not open for reading rows by chapter name")
                return emptyList()
            }

            val query = "SELECT * FROM Gita WHERE chaptername = ?"
            val selectionArgs = arrayOf(chapterName)

            database.rawQuery(query, selectionArgs)?.use { cursor ->
                val columnNames = getColumnNames("Gita")  // Assuming getColumnNames fetches column names dynamically

                while (cursor.moveToNext()) {
                    val rowData = mutableMapOf<String, Any?>()
                    for (columnName in columnNames) {
                        val value = cursor.getString(cursor.getColumnIndex(columnName))
                        rowData[columnName] = value
                    }
                    rows.add(rowData)
                }
            }
        }
        return rows
    }

    @SuppressLint("Range")
    fun getRowById(uid: Int): Map<String, Any?>? {
        var rowData: MutableMap<String, Any?>? = null
        db?.let { database ->
            try {
                if (!database.isOpen) {
                    Log.w(TAG, "Database not open for reading row by id")
                    return null
                }

                val query = "SELECT * FROM Gita WHERE id = ?"
                val selectionArgs = arrayOf(uid.toString())

                database.rawQuery(query, selectionArgs)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        rowData = mutableMapOf()
                        val columnNames = cursor.columnNames

                        for (columnName in columnNames) {
                            val value = when (cursor.getType(cursor.getColumnIndex(columnName))) {
                                Cursor.FIELD_TYPE_NULL -> null
                                Cursor.FIELD_TYPE_INTEGER -> cursor.getLong(cursor.getColumnIndex(columnName))
                                Cursor.FIELD_TYPE_FLOAT -> cursor.getDouble(cursor.getColumnIndex(columnName))
                                Cursor.FIELD_TYPE_STRING -> cursor.getString(cursor.getColumnIndex(columnName))
                                Cursor.FIELD_TYPE_BLOB -> cursor.getBlob(cursor.getColumnIndex(columnName))
                                else -> null
                            }
                            rowData!!.put(columnName, value)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching row by id", e)
            }
        } ?: Log.e(TAG, "Database is null!")

        return rowData
    }

    @SuppressLint("Range")
    fun getRowsByMonth(month: String): List<Map<String, String>> {
        val rows = mutableListOf<Map<String, String>>()
        db?.let { database ->
            if (!database.isOpen) {
                Log.w(TAG, "Database not open for reading rows by month: $month")
                return emptyList()
            }

            val query = "SELECT * FROM calander WHERE month = ?"
            val selectionArgs = arrayOf(month)

            database.rawQuery(query, selectionArgs)?.use { cursor ->
                val columnNames = cursor.columnNames

                while (cursor.moveToNext()) {
                    val rowData = mutableMapOf<String, String>()
                    for (columnName in columnNames) {
                        val value = cursor.getString(cursor.getColumnIndex(columnName)) ?: ""
                        rowData[columnName] = value
                    }
                    rows.add(rowData)
                }
            }
        } ?: Log.e(TAG, "Database is null!")

        return rows
    }

    @SuppressLint("Range")
    fun getRowByMonthAndDate(month: String, date: String): Map<String, String>? {
        db?.let { database ->
            if (!database.isOpen) {
                Log.w(TAG, "Database not open for reading rows by month: $month and date: $date")
                return null
            }

            val query = "SELECT * FROM calander WHERE month = ? AND date = ?"
            val selectionArgs = arrayOf(month, date)

            database.rawQuery(query, selectionArgs)?.use { cursor ->
                val columnNames = cursor.columnNames

                if (cursor.moveToFirst()) {
                    val rowData = mutableMapOf<String, String>()
                    for (columnName in columnNames) {
                        val value = cursor.getString(cursor.getColumnIndex(columnName)) ?: ""
                        rowData[columnName] = value
                    }
                    return rowData
                }
            }
        } ?: Log.e(TAG, "Database is null!")

        return null
    }


    @SuppressLint("Range")
    fun getimageByDayName(dayName: String): List<Map<String, String>> {
        val rows = mutableListOf<Map<String, String>>()
        db?.let { database ->
            if (!database.isOpen) {
                Log.w(TAG, "Database not open for reading rows by day name: $dayName")
                return emptyList()
            }

            val query = "SELECT * FROM imageauto WHERE day = ?"
            val selectionArgs = arrayOf(dayName)

            database.rawQuery(query, selectionArgs)?.use { cursor ->
                val columnNames = cursor.columnNames

                while (cursor.moveToNext()) {
                    val rowData = mutableMapOf<String, String>()
                    for (columnName in columnNames) {
                        val value = cursor.getString(cursor.getColumnIndex(columnName)) ?: ""
                        rowData[columnName] = value
                    }
                    rows.add(rowData)
                }
            }
        } ?: Log.e(TAG, "Database is null!")

        return rows
    }

    @SuppressLint("Range")
    fun getimageByGodName(godName: String): List<Map<String, String>> {
        val rows = mutableListOf<Map<String, String>>()
        db?.let { database ->
            if (!database.isOpen) {
                Log.w(TAG, "Database not open for reading rows by god name: $godName")
                return emptyList()
            }

            val query = "SELECT * FROM imageauto WHERE godname = ?"
            val selectionArgs = arrayOf(godName)

            database.rawQuery(query, selectionArgs)?.use { cursor ->
                val columnNames = cursor.columnNames

                while (cursor.moveToNext()) {
                    val rowData = mutableMapOf<String, String>()
                    for (columnName in columnNames) {
                        val value = cursor.getString(cursor.getColumnIndex(columnName)) ?: ""
                        rowData[columnName] = value
                    }
                    rows.add(rowData)
                }
            }
        } ?: Log.e(TAG, "Database is null!")

        return rows
    }

    @SuppressLint("Range")
    fun getimageByholidayname(holidayname: String): List<Map<String, String>> {
        val rows = mutableListOf<Map<String, String>>()
        db?.let { database ->
            if (!database.isOpen) {
                Log.w(TAG, "Database not open for reading rows by god name: $holidayname")
                return emptyList()
            }

            val query = "SELECT * FROM imageauto WHERE holiday = ?"
            val selectionArgs = arrayOf(holidayname)

            database.rawQuery(query, selectionArgs)?.use { cursor ->
                val columnNames = cursor.columnNames

                while (cursor.moveToNext()) {
                    val rowData = mutableMapOf<String, String>()
                    for (columnName in columnNames) {
                        val value = cursor.getString(cursor.getColumnIndex(columnName)) ?: ""
                        rowData[columnName] = value
                    }
                    rows.add(rowData)
                }
            }
        } ?: Log.e(TAG, "Database is null!")

        return rows
    }




}