package com.example.compositions.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.compositions.R
import com.example.compositions.databinding.FragmentGameFinishedBinding
import com.example.compositions.domain.entity.GameResult
import com.example.compositions.domain.entity.GameSettings
import java.lang.RuntimeException

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEmoji()
        bindViews()
    }

    private fun bindViews() {
        with(binding) {
            buttonRetry.setOnClickListener {
                retryGame()
            }
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                args.result.gameSettings.minCountOfRightAnswers.toString()
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                args.result.countOfRightAnswers.toString()
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                args.result.gameSettings.minPercentOfRightAnswers.toString()
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                calculatePercent()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun calculatePercent(): String {
        return if (args.result.countOfQuestions == 0) {
            "0"
        } else {
            (args.result.countOfRightAnswers / args.result.countOfQuestions.toDouble() * 100).toString()
        }
    }

    private fun setEmoji() {
        if (args.result.winner) {
            binding.emojiResult.setImageResource(R.drawable.ic_smile)
        } else {
            binding.emojiResult.setImageResource(R.drawable.ic_sad)
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

    companion object {
        const val GAME_RESULT = "gameResult"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT, gameResult)
                }
            }
        }
    }
}