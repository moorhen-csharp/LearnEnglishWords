package com.example.learnenglishwords

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.learnenglishwords.databinding.ActivityLearnWordBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityLearnWordBinding? = null

    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityLearnWordBinding must bot be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onCreate() ")


        val trainer = LearnWordsTrainer()
        showNextQuestion(trainer)

        with(binding){
            btnContinue.setOnClickListener {
                layoutResult.isVisible = false
                markAnsverNeutral(layoutAnsver1, tvVariantNumber1, tvVariantValue1)
                markAnsverNeutral(layoutAnsver2, tvVariantNumber2, tvVariantValue2)
                markAnsverNeutral(layoutAnsver3, tvVariantNumber3, tvVariantValue3)
                markAnsverNeutral(layoutAnsver4, tvVariantNumber4, tvVariantValue4)
                showNextQuestion(trainer)
            }

            btnSkip.setOnClickListener {
                showNextQuestion(trainer)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onResume() ")
    }



    private fun showNextQuestion(trainer: LearnWordsTrainer) {
        val firstQuestion: Question? = trainer.getNextQuestion()
        with(binding){
            if (firstQuestion == null || firstQuestion.variants.size < NUMBER_OF_ANSWERS) {
                tvQuestionword.isVisible = false
                layoutVariants.isVisible = false
                btnSkip.text = "Complete"
            } else {
                btnSkip.isVisible = true
                tvQuestionword.isVisible = true
                tvQuestionword.text = firstQuestion.correctAnswer.original

                tvVariantValue1.text = firstQuestion.variants[0].translate
                tvVariantValue2.text = firstQuestion.variants[1].translate
                tvVariantValue3.text = firstQuestion.variants[2].translate
                tvVariantValue4.text = firstQuestion.variants[3].translate

                layoutAnsver1.setOnClickListener {
                    if (trainer.checkAnswer(0)) {
                        markAnsverCorrect(layoutAnsver1, tvVariantNumber1, tvVariantValue1)
                        showResultMessage(true)
                    } else {
                        markAnsverWrong(layoutAnsver1, tvVariantNumber1, tvVariantValue1)
                        showResultMessage(false)
                    }
                }

                layoutAnsver2.setOnClickListener {
                    if (trainer.checkAnswer(1)) {
                        markAnsverCorrect(layoutAnsver2, tvVariantNumber2, tvVariantValue2)
                        showResultMessage(true)
                    } else {
                        markAnsverWrong(layoutAnsver2, tvVariantNumber2, tvVariantValue2)
                        showResultMessage(false)
                    }
                }

                layoutAnsver3.setOnClickListener {
                    if (trainer.checkAnswer(2)) {
                        markAnsverCorrect(layoutAnsver3, tvVariantNumber3, tvVariantValue3)
                        showResultMessage(true)
                    } else {
                        markAnsverWrong(layoutAnsver3, tvVariantNumber3, tvVariantValue3)
                        showResultMessage(false)
                    }
                }

                layoutAnsver4.setOnClickListener {
                    if (trainer.checkAnswer(3)) {
                        markAnsverCorrect(layoutAnsver4, tvVariantNumber4, tvVariantValue4)
                        showResultMessage(true)
                    } else {
                        markAnsverWrong(layoutAnsver4, tvVariantNumber4, tvVariantValue4)
                        showResultMessage(false)
                    }
                }
            }
        }
    }

    private fun markAnsverWrong(
        layoutAnsver: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
    ) {
        layoutAnsver.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_wrong
        )

        tvVariantNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variants_wrong
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white)
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.wrongAnsverColor)
        )

    }

    private fun markAnsverCorrect(
        layoutAnsver: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
    ) {
        layoutAnsver.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_correct
        )

         tvVariantNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variants_correct
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white)
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.correctAnsverColor)
        )


    }

    private fun markAnsverNeutral(
        layoutAnsver: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
    ) {
        layoutAnsver.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers
        )


        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.textVariantsColor
            )
        )

        tvVariantNumber.apply {
            background = ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.shape_rounded_variants,
            )
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.textVariantsColor,
                )
            )
        }
    }

    private  fun showResultMessage(isCorrect: Boolean) {
        val color: Int
        val resultIconResourse: Int
        val messageText: String
        if (isCorrect){
            color = ContextCompat.getColor(this, R.color.correctAnsverColor)
            resultIconResourse = R.drawable.ic_correct
            messageText = "Correct"
        }
        else{
            color = ContextCompat.getColor(this, R.color.wrongAnsverColor)
            resultIconResourse = R.drawable.ic_wrong
            messageText = "Wrong"
        }

        with(binding){
            btnSkip.isVisible = false
            layoutResult.isVisible = true
            btnContinue.setTextColor(color)
            layoutResult.setBackgroundColor(color)
            tvResultMessage.text = messageText
            isResultIcon.setImageResource(resultIconResourse)
        }

    }
}




