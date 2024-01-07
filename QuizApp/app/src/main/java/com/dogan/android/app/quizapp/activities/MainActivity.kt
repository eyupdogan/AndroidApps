package com.dogan.android.app.quizapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.dogan.android.app.geonames.quizapp.databinding.ActivityMainBinding
import com.dogan.android.app.quizapp.adapter.GridAdapter
import com.dogan.android.app.quizapp.models.Category
import com.dogan.android.app.quizapp.models.QuizQuestion
import com.dogan.android.app.quizapp.utils.Constants
import com.dogan.android.app.quizapp.utils.QuizClass
import com.dogan.android.app.quizapp.utils.Utils
import retrofit2.Callback

class MainActivity : AppCompatActivity()
{
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        val quizClass = QuizClass(this)

        val rvCategoryList = binding?.rvCategoryList
        rvCategoryList?.layoutManager = GridLayoutManager(this, 2)

        quizClass.getQuestionStatsList(object : QuizClass.IQuestionStatCallback{
            override fun onQuestionStatFetched(map: Map<String, Category>)
            {
                val adapter = GridAdapter(Constants.getCategoryItemList(), map)
                rvCategoryList?.adapter = adapter
                adapter.setOnTouchResponse(object:GridAdapter.IOnTouchResponse{
                    override fun onClick(id: Int)
                    {
                        Utils.showToast(this@MainActivity, "Id is $id")
                    }
                })
            }
        })

    }
}