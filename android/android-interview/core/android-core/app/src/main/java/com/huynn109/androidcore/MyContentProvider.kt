package com.huynn109.androidcore

import android.content.*
import android.database.Cursor
import android.net.Uri
import androidx.room.Room


class MyContentProvider : ContentProvider() {

    companion object {
        const val TAG = "MyContentProvider"
        private val AUTHORITY = "com.huynn109.androidcore.MyContentProvider.provider"
        private val CODE_USER_DIR = 1
        private val CODE_USER_ITEM = 2
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$TABLE_NAME")
    }
    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, TABLE_NAME, CODE_USER_DIR)
        addURI(AUTHORITY, "$TABLE_NAME/*", CODE_USER_ITEM)
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val code: Int = sUriMatcher.match(uri)
        return if (code == CODE_USER_DIR || code == CODE_USER_ITEM) {
            val context = context ?: return null
            val user: UserDao? = AppDatabase.getInstance(context)?.userDao()
            val cursor: Cursor? = if (code == CODE_USER_DIR) {
                user?.getAll()
            } else {
                user?.findByName(first = "12", last = "34")
            }
            cursor?.setNotificationUri(context.contentResolver, uri)
            cursor
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }
}