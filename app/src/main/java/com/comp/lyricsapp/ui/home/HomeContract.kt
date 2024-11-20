package com.comp.lyricsapp.ui.home

import com.comp.lyricsapp.data.model.ProjectEntity

interface HomeContract {

    interface View {

        fun createProject(project: ProjectEntity)
        fun deleteProject(project: ProjectEntity)
        fun openProject(project: ProjectEntity)
        fun showProjects(projectEntities: List<ProjectEntity>)
        fun showError(message: String)
    }

    interface Presenter{
        fun loadProjects()
        fun attach(view: View)
        fun detach()
    }

}