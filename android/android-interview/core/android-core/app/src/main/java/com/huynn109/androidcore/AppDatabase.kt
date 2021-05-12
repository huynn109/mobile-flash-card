package com.huynn109.androidcore

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var sInstance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "ex")
                    .build()
                sInstance?.populateInitialData()
            }
            return sInstance
        }
    }

    open fun switchToInMemory(context: Context) {
        sInstance = Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            AppDatabase::class.java
        ).build()
    }

    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
    private fun populateInitialData() {
        if (userDao().count() == 0) {
            runInTransaction {
                val user = User()
                for (i in 0..100) {
                    user.firstName = "first name $i"
                    user.lastName = "last name $i"
                    userDao().insert(user)
                }
            }
        }
    }
}