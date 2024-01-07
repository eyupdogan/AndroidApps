package com.dogan.android.app.quizapp.models

data class QuizResponse
(
    var response_code:Int = 0,
    var results: List<QuizQuestion>
)

data class QuizQuestion
(
    var type: String,
    var difficulty: String,
    var category: String,
    var question: String,
    var correct_answer: String,
    var incorrect_answers: ArrayList<String>
)
