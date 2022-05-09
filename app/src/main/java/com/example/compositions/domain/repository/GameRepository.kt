package com.example.compositions.domain.repository

import com.example.compositions.domain.entity.GameSettings
import com.example.compositions.domain.entity.Level
import com.example.compositions.domain.entity.Question

interface GameRepository {

    fun generateQuestionUseCase(
        maxSumValue: Int,
        countOfOptions: Int
    ) : Question

    fun getGameSettingsUseCase(level: Level) : GameSettings
}