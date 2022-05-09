package com.example.compositions.domain.useCase

import com.example.compositions.domain.entity.Question
import com.example.compositions.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int) : Question {
        return repository.generateQuestionUseCase(maxSumValue, COUNT_OF_OPTIONS)
    }

    companion object{
        const val COUNT_OF_OPTIONS = 6
    }
}