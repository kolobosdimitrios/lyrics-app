package com.comp.lyricsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.comp.lyricsapp.data.model.LineDto
import kotlinx.coroutines.flow.Flow

@Dao
interface LineDao {

    @Delete
    suspend fun deleteLine(lineDto: LineDto)

    @Update
    suspend fun updateLine(newLineDto: LineDto)

    @Transaction
    @Query("SELECT * FROM LINES WHERE id = :lineId")
    fun getLine(lineId: Long) : Flow<LineDto>


}