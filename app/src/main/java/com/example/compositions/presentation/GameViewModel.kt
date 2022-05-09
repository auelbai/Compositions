package com.example.compositions.presentation

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.compositions.data.GameRepositoryImpl
import com.example.compositions.domain.entity.GameSettings
import com.example.compositions.domain.entity.Level
import com.example.compositions.domain.entity.Question
import com.example.compositions.domain.useCase.GenerateQuestionUseCase
import com.example.compositions.domain.useCase.GetGameSettingsUseCase

class GameViewModel : ViewModel() {

    private val repository = GameRepositoryImpl

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)

    private lateinit var level: Level
    private lateinit var gameSettings: GameSettings

    private var timer: CountDownTimer? = null

    private var _formattedTime = MutableLiveData<String>()
    private val formattedTime: LiveData<String>
        get() = _formattedTime


    fun generateQuestion(maxSumValue: Int): Question {
        return generateQuestionUseCase.invoke(maxSumValue)
    }

    fun startGame(level: Level) {
        getGameSettings(level)
        startTimer()
    }

    private fun getGameSettings(level: Level) {
        this.level = level
        this.gameSettings = getGameSettingsUseCase.invoke(level)
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    private fun finishGame() {

    }

    private fun formatTime(millisUntilFinished: Long) : String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (SECONDS_IN_MINUTES * minutes)
        return String().format("%02d:%02d", minutes, leftSeconds)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {
        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTES = 60
    }

}