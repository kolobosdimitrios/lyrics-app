package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.data.local.dao.LineDao
import com.comp.lyricsapp.domain.entities.Line

data class BarLineIds(val barId: Long, val lineId: Long )

sealed abstract class LineUseCase<I,T>(protected val lineDao: LineDao) {
    suspend operator abstract fun invoke(input: I): T
}


class CreateLinesUseCase(lineDao: LineDao): LineUseCase<List<Line>, Unit>(lineDao) {

    override suspend fun invoke(newLines: List<Line>) {
        TODO("Not yet implemented")
    }

}

class CreateLineUseCase(lineDao: LineDao): LineUseCase<Line, Unit>(lineDao) {

    override suspend fun invoke(newLine: Line) {
        TODO("Not yet implemented")
    }

}


class GetLinesUseCase(lineDao: LineDao):LineUseCase<Long, List<Line>>(lineDao) {

    override suspend fun invoke(barId: Long): List<Line> {
        TODO("Not yet implemented")
    }
}

class GetLineUseCase(lineDao: LineDao): LineUseCase<Long, Line>(lineDao){
    override suspend fun invoke(lineId: Long): Line {
        TODO("Not yet implemented")
    }

}

class UpdateLine(lineDao: LineDao): LineUseCase<Line, Unit>(lineDao) {

    override suspend fun invoke(updatedLine: Line) {
        TODO("Not yet implemented")
    }
}

class DeleteLine(lineDao: LineDao): LineUseCase<Long, Unit>(lineDao) {

    override suspend fun invoke(lineId: Long) {
        TODO("Not yet implemented")
    }
}

class DeleteBarLines(lineDao: LineDao): LineUseCase<Long, Unit>(lineDao){
    override suspend fun invoke(barId: Long) {
        TODO("Not yet implemented")
    }

}


class DeleteBarLine(lineDao: LineDao): LineUseCase<BarLineIds ,Unit>(lineDao){
    override suspend fun invoke(barLineIds: BarLineIds) {
        TODO("Not yet implemented")
    }

}