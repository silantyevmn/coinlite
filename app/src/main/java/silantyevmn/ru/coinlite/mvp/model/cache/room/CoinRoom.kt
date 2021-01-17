package silantyevmn.ru.coinlite.mvp.model.cache.room

import android.arch.persistence.room.Room
import com.yurentsy.watchingyou.App
import com.yurentsy.watchingyou.mvp.model.cache.Cache
import com.yurentsy.watchingyou.mvp.model.entity.Person
import io.reactivex.Observable

class CoinRoom : Cache {
    private val DATABASE_NAME = "persons.db"
    private val database: CoinRoomAbs
    fun put(person: Person): Observable<Boolean> {
        return Observable.just<Any>(person)
            .map { person1: Any? ->
                database.photoDao().insert(person1)
                true
            }
    }

    fun putAll(list: List<Person>): Observable<Boolean> {
        return Observable.just<List<Person>>(list)
            .map { personList: List<Person>? ->
                database.photoDao().insertAll(personList)
                true
            }
    }

    val persons: Observable<List<Any>>
        get() = Observable.just<List<Person>>(database.photoDao().list)

    fun updatePerson(person: Person): Observable<Boolean> {
        return Observable.just<Any>(person)
            .map { person1: Any? ->
                database.photoDao().update(person1)
                true
            }
    }

    fun insert(person: Person): Observable<Boolean> {
        return Observable.just<Any>(person)
            .map { person1: Any ->
                val maxId = database.photoDao().maxId + 1
                person1.setId(maxId.toString())
                database.photoDao().insert(person1)
                true
            }
    }

    fun delete(person: Person): Observable<Boolean> {
        return Observable.just<Any>(person)
            .map { person1: Any? ->
                database.photoDao().delete(person)
                true
            }
    }

    init {
        database = Room.databaseBuilder(App.getInstance(), CoinRoomAbs::class.java, DATABASE_NAME)
            .allowMainThreadQueries().build()
    }
}