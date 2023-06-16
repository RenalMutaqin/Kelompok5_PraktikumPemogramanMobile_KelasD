package com.example.tokoelektronik.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tokoelektronik.model.Smartphone

@Dao
interface SmartphoneDao {
    @Query("SELECT * FROM Smartphone ORDER BY model DESC")
    fun loadAll(): LiveData<List<Smartphone>>
    @Query("SELECT * FROM Smartphone ORDER BY model DESC")
    suspend fun getList(): List<Smartphone>
    @Query("SELECT * FROM Smartphone WHERE id = :id")
    suspend fun find(id: String): Smartphone?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Smartphone)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Smartphone>)
    @Delete
    fun delete(item: Smartphone)
    @Query("DELETE FROM Smartphone WHERE id = :id")
    suspend fun delete(id: String)
}