package com.example.movieapp
import com.example.movieapp.model.repositories.Repository
import com.example.movieapp.model.repositories.RepositoryImpl
import com.example.movieapp.ui.itemPage.ItemPageViewModel
import com.example.movieapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    //View models
    viewModel { MainViewModel(get()) }
    viewModel { ItemPageViewModel(get()) }
}