package com.dogan.android.app.quizapp.utils

import android.content.Context
import android.util.Log
import com.dogan.android.app.quizapp.models.Category
import com.dogan.android.app.quizapp.models.QuestionStats
import com.dogan.android.app.quizapp.models.QuizQuestion
import com.dogan.android.app.quizapp.models.QuizResponse
import com.dogan.android.app.quizapp.retrofit.IQuizService
import com.dogan.android.app.quizapp.retrofit.IQuestionStatsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizClass(private val context: Context)
{
    fun getQuizList(amount:Int, category:Int?, difficulty:String?, type:String?, callback: IQuestionListCallback)
    {
        if (Constants.isNetworkAvailable(context)){
            val pbDialog = Utils.showProgressBar(context)
            val retrofit:Retrofit = Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            val service: IQuizService = retrofit.create(IQuizService::class.java)

            val dataCall:Call<QuizResponse> = service.getQuiz(
                amount, category, difficulty, type
            )

            dataCall.enqueue(object :Callback<QuizResponse>{
                override fun onResponse(call: Call<QuizResponse>, response: Response<QuizResponse>)
                {
                    pbDialog.cancel()
                    if (response.isSuccessful){
                        val responseData: QuizResponse = response.body()!!
                        val questionList = responseData.results

                        callback.onQuestionListFetched(questionList)
                        Log.e("Debug", questionList.toString())
                    }else{
                        Utils.showToast(context, "Response Failed")
                    }
                }

                override fun onFailure(call: Call<QuizResponse>, t: Throwable)
                {
                    pbDialog.cancel()
                    Utils.showToast(context, "Failure in Response")
                }
            })
        }else
            Utils.showToast(context, "Network is not available")
    }
    interface IQuestionListCallback{
        fun onQuestionListFetched(list:List<QuizQuestion>)
    }

    fun getQuestionStatsList(callback: IQuestionStatCallback)
    {
        if (Constants.isNetworkAvailable(context))
        {
            val pbDialog = Utils.showProgressBar(context)
            val retrofit:Retrofit = Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            val service: IQuestionStatsService = retrofit.create(IQuestionStatsService::class.java)

            val dataCall:Call<QuestionStats> = service.getData()

            dataCall.enqueue(object:Callback<QuestionStats>{
                override fun onResponse(
                    call: Call<QuestionStats>,
                    response: Response<QuestionStats>
                )
                {
                    pbDialog.cancel()
                    if (response.isSuccessful){
                        val questionStats: QuestionStats = response.body()!!
                        val categoryMap = questionStats.categories
                        callback.onQuestionStatFetched(categoryMap)
                    }else{
                        Utils.showToast(context, "Error Code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<QuestionStats>, t: Throwable)
                {
                    pbDialog.cancel()
                    Utils.showToast(context, "API call failure")
                }
            })
        }
        else
            Utils.showToast(context, "Network is not available")
    }

    interface IQuestionStatCallback {
        fun onQuestionStatFetched(map:Map<String, Category>)
    }
}