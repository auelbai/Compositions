package com.example.compositions.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.compositions.R
import com.example.compositions.domain.entity.GameResult

interface OnClickListener {
    fun clickListener(option: Int)
}

@BindingAdapter("requiredAnswers")
fun requiredAnswers(textView: TextView, minCountOfRightAnswers: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        minCountOfRightAnswers
    )
}

@BindingAdapter("requiredPercentage")
fun requiredPercentage(textView: TextView, minCountOfPercentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        minCountOfPercentage
    )
}

@BindingAdapter("scoreAnswers")
fun scoreAnswers(textView: TextView, countOfRightAnswers: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        countOfRightAnswers
    )
}

@BindingAdapter("scorePercentage")
fun scorePercentage(textView: TextView, result: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        calculatePercent(result.countOfQuestions, result.countOfRightAnswers)
    )
}

private fun calculatePercent(countOfQuestions: Int, countOfRightAnswers: Int): String {
    return if (countOfQuestions == 0) {
        "0"
    } else {
        (countOfRightAnswers / countOfQuestions.toDouble() * 100).toString()
    }
}

@BindingAdapter("winnerImage")
fun winnerImage(imageView: ImageView, winner: Boolean) {
    if (winner) {
        imageView.setImageResource(R.drawable.ic_smile)
    } else {
        imageView.setImageResource(R.drawable.ic_sad)
    }
}

@BindingAdapter("sumQuestion")
fun sumQuestion(textView: TextView, sum: Int) {
    textView.text = sum.toString()
}

@BindingAdapter("leftNumber")
fun leftNumber(textView: TextView, visibleNumber: Int) {
    textView.text = visibleNumber.toString()
}

@BindingAdapter("enoughCount")
fun enoughCount(textView: TextView, goodState: Boolean) {
    textView.setTextColor(getColor(textView.context, goodState))
}

@BindingAdapter("enoughPercent")
fun enoughPercent(progressBar: ProgressBar, goodState: Boolean) {
    progressBar.progressTintList = ColorStateList.valueOf(getColor(progressBar.context, goodState))
}

private fun getColor(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("clickListener")
fun clickListener(textView: TextView, clickListener: OnClickListener) {
    textView.setOnClickListener {
        clickListener.clickListener(textView.text.toString().toInt())
    }
}