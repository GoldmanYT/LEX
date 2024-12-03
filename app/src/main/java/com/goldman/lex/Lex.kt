package com.goldman.lex

class Lex {
    private val variantCount = 4
    var definition: String = ""
    var answer: String = ""
    var answerIndex = 0

    fun generateWord(): Pair<String, List<String>> {
        val keys = WORDS.keys.shuffled().take(variantCount).toList()
        val answers = keys.map { WORDS.getOrDefault(it, "") }

        answerIndex = (0..<variantCount).random()
        definition = keys[answerIndex]
        answer = answers[answerIndex]

        return Pair(keys[answerIndex], answers)
    }
}