package com.inconsciente.colectiv.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MessageDao {
    @Query("select * from message")
    fun getMessages(): LiveData<List<Message>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( videos: List<Message>)

}

@Dao
interface ConfigDao{
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertConfig(config: Config)
    @Query("select * from config")
    fun getConfig(): Config
}

@Database(entities = [Message::class, Config::class], version = 1)
abstract class InconscienteDatabase: RoomDatabase() {
    abstract val messageDao: MessageDao
    abstract val configDao: ConfigDao
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