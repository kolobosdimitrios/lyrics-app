package com.comp.lyricsapp.domain.usecases

data class ProjectUseCasesContainer(
    val createProjectUseCase: CreateProjectUseCase,
    val updateProjectUseCase: UpdateProjectUseCase,
    val deleteProjectUseCase: DeleteProjectUseCase,
    val getAllProjectsUseCase: GetAllProjectsUseCase,
    val getProjectWithBarsUseCase: GetProjectWithBarsUseCase
) {
}