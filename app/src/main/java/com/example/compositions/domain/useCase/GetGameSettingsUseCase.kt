package com.example.compositions.domain.useCase

import com.example.compositions.domain.entity.GameSettings
import com.example.compositions.domain.entity.Level
import com.example.compositions.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level) : GameSettings {

        return repository.getGameSettingsUseCase(level)
    }
}