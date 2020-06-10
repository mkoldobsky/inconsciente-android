package com.inconsciente.colectiv.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface InconscienteDao {
    @Query("select * from messageentity")
    fun getMessages(): LiveData<List<MessageEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( videos: List<MessageEntity>)
}

@Database(entities = [MessageEntity::class], version = 1)
abstract class InconscienteDatabase: RoomDatabase() {
    abstract val inconscienteDao: InconscienteDao
}

private lateinit var INSTANCE: InconscienteDatabase

fun getDatabase(context: Context): InconscienteDatabase {
    synchronized(InconscienteDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                InconscienteDatabase::class.java,
                "inconsciente").build()
        }
    }
    return INSTANCE
}