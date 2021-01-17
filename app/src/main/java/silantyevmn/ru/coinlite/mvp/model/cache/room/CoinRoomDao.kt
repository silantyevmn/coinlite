package silantyevmn.ru.coinlite.mvp.model.cache.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface CoinRoomDao {
    @get:Query("SELECT * FROM person")
    val list: List<Any?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(person: Person?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: List<Person?>?)

    @Update
    fun update(person: Person?)

    @Delete
    fun delete(person: Person?)

    @get:Query("SELECT MAX(id) FROM person")
    val maxId: Int
}