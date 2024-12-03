package com.goldman.lex

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val lex = Lex()

    private val answerButtons: MutableList<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        answerButtons.addAll(listOf(R.id.word1, R.id.word2, R.id.word3, R.id.word4)
            .map { findViewById(it) })
        val buttonContinue: Button = findViewById(R.id.button_continue)

        val data = lex.generateWord()
        updateContents(data)

        for ((index, button) in answerButtons.withIndex()) {
            button.setOnClickListener { onClick(index) }
        }
        buttonContinue.setOnClickListener { onContinue() }
    }

    @SuppressLint("SetTextI18n")
    private fun onClick(index: Int) {
        if (index == lex.answerIndex) {
            updateContents(lex.generateWord())
        } else {
            val buttonContinue: Button = findViewById(R.id.button_continue)
            val guessTextView: TextView = findViewById(R.id.guess)

            for (button in answerButtons) {
                button.visibility = Button.GONE
            }
            buttonContinue.visibility = Button.VISIBLE

            val definition = lex.definition
            val answer = lex.answer
            guessTextView.text = "Неверно\n\n\n$definition - $answer\n\n\n"
        }
    }

    private fun onContinue() {
        val buttonContinue: Button = findViewById(R.id.button_continue)

        for (button in answerButtons) {
            button.visibility = Button.VISIBLE
        }
        buttonContinue.visibility = Button.GONE

        updateContents(lex.generateWord())
    }

    @SuppressLint("SetTextI18n")
    private fun updateContents(data: Pair<String, List<String>>) {
        val guess: TextView = findViewById(R.id.guess)

        guess.text = "\n${data.first}\n"
        for ((button, answer) in answerButtons zip data.second) {
            button.text = answer
        }
    }
}