package com.example.tokoelektronik.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tokoelektronik.model.Periferal

@Dao
interface PeriferalDao {
    @Query("SELECT * FROM Periferal ORDER BY nama DESC")
    fun loadAll(): LiveData<List<Periferal>>
    @Query("SELECT * FROM Periferal ORDER BY nama DESC")
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