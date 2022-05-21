package com.example.compositions.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compositions.domain.entity.Level
import java.lang.RuntimeException

class GameViewModelFactory(
    private val application: Application,
    private val level: Level
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(application, level) as T
        }
        throw RuntimeException("unknown view model class $modelClass")
    }
}