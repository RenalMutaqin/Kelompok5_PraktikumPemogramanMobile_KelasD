package ppm.b.kelompok4.tokoelektronik.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import ppm.b.kelompok4.tokoelektronik.model.Periferal

@Dao
interface PeriferalDao {
    @Query("SELECT * FROM Periferal")
    fun loadAll(): LiveData<List<Periferal>>

    @Query("SELECT * FROM Periferal")
    suspend fun getList(): List<Periferal>

    @Query("SELECT * FROM Periferal WHERE id = :id")
    suspend fun find(id: String): Periferal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Periferal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Periferal>)

    @Delete
    fun delete(item: Periferal)

    @Query("DELETE FROM Periferal WHERE id = :id")
    suspend fun delete(id: String)
}