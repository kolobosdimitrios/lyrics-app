package com.comp.lyricsapp.ui.home

import android.view.View
import com.comp.lyricsapp.data.model.ProjectEntity
import com.comp.lyricsapp.data.repo.ProjectRepository
import com.comp.lyricsapp.ui.home.HomeContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePresenter(val projectRepository: ProjectRepository) : HomeContract.Presenter{

    private var view : HomeContract.View? = null
    private val presenterScope = CoroutineScope(Dispatchers.IO)


    override fun loadProjects() {

    }

    override fun attach(view: HomeContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }



}