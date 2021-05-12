package com.huynn109.androidcore

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT COUNT(*) FROM user")
    fun count(): Int

    @Query("SELECT * FROM user")
    fun getAll(): Cursor

    @Query(
        "SELECT * FROM user WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): Cursor

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)
}