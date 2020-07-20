package com.inconsciente.colectiv.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MessageDao {
    @Query("select * from message")
    fun getMessages(): LiveData<List<Message>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(messages: List<Message>)

}

@Dao
interface ConfigDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConfig(config: Config)

    @Query("select * from config")
    fun getConfig(): Config


}

@Dao
interface AreaDao {
    @Query("select * from area")
    fun getAreas(): List<Area>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(areas: List<Area>)

}

@Database(entities = [Message::class, Config::class, Area::class], version = 3)
@TypeConverters(ZipcodesConverter::class)
abstract class InconscienteDatabase : RoomDatabase() {
    abstract val messageDao: MessageDao
    abstract val configDao: ConfigDao
    abstract val areaDao: AreaDao
}

private lateinit var INSTANCE: InconscienteDatabase

fun getDatabase(context: Context): InconscienteDatabase {
    synchronized(InconscienteDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                InconscienteDatabase::class.java,
                "inconsciente"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}