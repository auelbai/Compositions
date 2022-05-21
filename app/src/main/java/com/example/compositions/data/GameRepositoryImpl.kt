package com.example.compositions.data

import com.example.compositions.domain.entity.GameSettings
import com.example.compositions.domain.entity.Level
import com.example.compositions.domain.entity.Question
import com.example.compositions.domain.repository.GameRepository
import java.lang.Integer.min
import kotlin.math.max
import kotlin.random.Random

object GameRepositoryImpl: GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestionUseCase(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue - 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettingsUseCase(level: Level): GameSettings {
        return when(level) {
            Level.TEST -> GameSettings(
                10,
                3,
                30,
                10
            )
            Level.EASY -> GameSettings(
                10,
                5,
                50,
                20
            )
            Level.NORMAL -> GameSettings(
                20,
                10,
                70,
                30
            )
            Level.HARD -> GameSettings(
                100,
                15,
                90,
                40
            )
        }
    }
}