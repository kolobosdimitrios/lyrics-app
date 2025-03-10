package com.comp.lyricsapp.domain.usecases

data class BarUseCasesContainer(
    val createBarUseCase: CreateBar,
    val updateBarUseCase: UpdateBar,
    val deleteProjectBar: DeleteProjectBar,
    val deleteProjectBars: DeleteProjectBars,
    val getBarLinesUseCase: GetBarLinesUseCase
)