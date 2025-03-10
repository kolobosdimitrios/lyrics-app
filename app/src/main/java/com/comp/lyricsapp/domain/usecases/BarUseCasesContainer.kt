package com.comp.lyricsapp.domain.usecases

data class BarUseCasesContainer(
    val createBar: CreateBar,
    val updateBar: UpdateBar,
    val deleteProjectBar: DeleteProjectBar,
    val deleteProjectBars: DeleteProjectBars,
    val getBarLinesUseCase: GetBarLinesUseCase
)