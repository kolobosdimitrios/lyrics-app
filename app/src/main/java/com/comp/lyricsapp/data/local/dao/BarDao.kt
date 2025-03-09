package com.comp.lyricsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.comp.lyricsapp.data.model.BarDto
import com.comp.lyricsapp.data.model.LineDto
import com.comp.lyricsapp.data.model.relations.BarWithLinesRelationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BarDao {

    @Insert
    suspend fun insertBar(barDto: BarDto): Long

    @Insert
    suspend fun insertLines(lines: List<LineDto>)

    @Transaction
    @Query("SELECT * FROM bars WHERE id = :barId")
    fun getBarWithLines(barId: Long) : Flow<BarWithLinesRelationEntity>

    @Update
    suspend fun updateBar(updatedBar: BarDto)

    @Transaction
    @Query("DELETE  FROM bars WHERE projectId = :projectId AND id in (:barIds)")
    suspend fun deleteProjectBars(projectId: Long, barIds: List<Long>)

    @Transaction
    @Query("DELETE FROM BARS WHERE projectId = :projectId")
    suspend fun deleteAllProjectBars(projectId: Long)



}