package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.data.repo.BarRepositoryImpl
import com.comp.lyricsapp.data.repo.LineRepositoryImpl
import com.comp.lyricsapp.data.local.repo.LocalBarRepository
import com.comp.lyricsapp.data.local.repo.LocalLineRepository
import com.comp.lyricsapp.data.remote.repo.RemoteBarRepository
import com.comp.lyricsapp.data.remote.repo.RemoteLineRepository
import com.comp.lyricsapp.data.local.dao.BarDao
import com.comp.lyricsapp.data.local.dao.LineDao
import com.comp.lyricsapp.data.remote.api.BarApi
import com.comp.lyricsapp.data.remote.api.LineApi
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.BarWithLines
import com.comp.lyricsapp.domain.entities.Line
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

// Stub implementations used to satisfy repository constructors
private class StubBarDao: BarDao {
    override suspend fun insertBar(barDto: com.comp.lyricsapp.data.model.BarDto): Long = 0L
    override suspend fun insertLines(lines: List<com.comp.lyricsapp.data.model.LineDto>) {}
    override fun getBarWithLines(barId: Long): Flow<com.comp.lyricsapp.data.model.relations.BarWithLinesRelationEntity> = flowOf(com.comp.lyricsapp.data.model.relations.BarWithLinesRelationEntity(com.comp.lyricsapp.data.model.BarDto(0L,0L), emptyList()))
    override suspend fun updateBar(updatedBar: com.comp.lyricsapp.data.model.BarDto) {}
    override suspend fun deleteProjectBars(projectId: Long, barIds: List<Long>) {}
    override suspend fun deleteAllProjectBars(projectId: Long) {}
}

private class StubLineDao: LineDao {
    override suspend fun insertLine(line: com.comp.lyricsapp.data.model.LineDto) {}
    override suspend fun updateLine(updatedLine: com.comp.lyricsapp.data.model.LineDto) {}
    override suspend fun deleteBarLines(lines: List<com.comp.lyricsapp.data.model.LineDto>) {}
}

private class StubBarApi: BarApi
private class StubLineApi: LineApi

class BarRepositoryImplFake : BarRepositoryImpl(
    LocalBarRepository(StubBarDao()),
    RemoteBarRepository(StubBarApi())
) {
    var updatedBar: Bar? = null
    var createdBar: Bar? = null
    var deleteProjectBarsArgs: Pair<Long,List<Long>>? = null
    var deleteAllProjectBarsArg: Long? = null
    var getBarLinesArg: Long? = null

    override suspend fun updateBar(updatedBar: Bar) {
        this.updatedBar = updatedBar
    }

    override suspend fun deleteProjectBars(projectId: Long, projectBarIds: List<Long>) {
        deleteProjectBarsArgs = projectId to projectBarIds
    }

    override suspend fun deleteAllProjectBars(projectId: Long) {
        deleteAllProjectBarsArg = projectId
    }

    override suspend fun createBar(bar: Bar) {
        createdBar = bar
    }

    override fun getBarWithLines(barId: Long): Flow<BarWithLines> {
        getBarLinesArg = barId
        return flowOf(BarWithLines(Bar(barId, -1), emptyList()))
    }
}

class LineRepositoryImplFake : LineRepositoryImpl(
    LocalLineRepository(StubLineDao()),
    RemoteLineRepository(StubLineApi())
) {
    var insertedLine: Line? = null
    var updatedLine: Line? = null
    var deletedLines: List<Line>? = null
    var createdLine: Line? = null

    override suspend fun insertLine(newLine: Line) {
        insertedLine = newLine
    }

    override suspend fun updateLine(updatedLine: Line) {
        this.updatedLine = updatedLine
    }

    override suspend fun deleteBarLines(barLines: List<Line>) {
        deletedLines = barLines
    }

    override suspend fun createLine(newLine: Line) {
        createdLine = newLine
    }
}
