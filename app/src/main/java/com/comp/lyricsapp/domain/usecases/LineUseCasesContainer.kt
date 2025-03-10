package com.comp.lyricsapp.domain.usecases

data class LineUseCasesContainer(
    val createLineUseCase: CreateLineUseCase,
    val updateLineUseCase: UpdateLineUseCase,
    val deleteBarLineUseCase: DeleteBarLineUseCase
)