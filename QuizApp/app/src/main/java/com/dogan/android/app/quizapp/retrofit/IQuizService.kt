package com.dogan.android.app.quizapp.retrofit

import com.dogan.android.app.quizapp.models.QuestionStats
import com.dogan.android.app.quizapp.models.QuizResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IQuizService
{
    @GET("api.php")
    fun getQuiz(
        @Query("amount") amount:Int,
        @Query("category") category:Int?,
        @Query("difficulty") difficulty:String?,
        @Query("type") type:String?
    ):Call<QuizResponse>
}

interface IQuestionStatsService{
    @GET("api_count_global.php")
    fun getData():Call<QuestionStats>
}