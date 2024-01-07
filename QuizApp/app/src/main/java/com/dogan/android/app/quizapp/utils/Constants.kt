package com.dogan.android.app.quizapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.dogan.android.app.geonames.quizapp.R
import com.dogan.android.app.quizapp.models.CategoryModel

object Constants
{
    fun isNetworkAvailable(context: Context):Boolean
    {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    fun getCategoryItemList():ArrayList<CategoryModel>
    {
        val list = ArrayList<CategoryModel>()
        list.add(
            CategoryModel("9", R.drawable.general_knowledge, "General Knowledge")
        )
        list.add(
            CategoryModel("10",R.drawable.books,"Books")
        )
        list.add(
            CategoryModel("11",R.drawable.movies,"Film")
        )
        list.add(
            CategoryModel("12",R.drawable.music,"Music")
        )
        list.add(
            CategoryModel("13",R.drawable.musicals,"Musicals & Theatres")
        )
        list.add(
            CategoryModel("14",R.drawable.television,"Television")
        )
        list.add(
            CategoryModel("15", R.drawable.video_games,"Video Games")
        )
        list.add(
            CategoryModel("16",R.drawable.board_game,"Board Games")
        )
        list.add(
            CategoryModel("17",R.drawable.science,"Science & Nature")
        )
        list.add(
            CategoryModel("18",R.drawable.computer,"Computer")
        )
        list.add(
            CategoryModel("19",R.drawable.maths,"Mathematics")
        )
        list.add(
            CategoryModel("20",R.drawable.mythology,"Mythology")
        )
        list.add(
            CategoryModel("21",R.drawable.sport,"Sports")
        )
        list.add(
            CategoryModel("22",R.drawable.geography,"Geography")
        )
        list.add(
            CategoryModel("23",R.drawable.history,"History")
        )
        list.add(
            CategoryModel("24",R.drawable.politics,"Politics")
        )
        list.add(
            CategoryModel("25",R.drawable.art,"Art")
        )
        list.add(
            CategoryModel("26",R.drawable.celebrity,"Celebrities")
        )
        list.add(
            CategoryModel("27",R.drawable.animals,"Animals")
        )
        list.add(
            CategoryModel("28",R.drawable.vehicles,"Vehicles")
        )
        list.add(
            CategoryModel("29",R.drawable.comic,"Comics")
        )
        list.add(
            CategoryModel("30",R.drawable.gadgets,"Gadgets")
        )
        list.add(
            CategoryModel("31",R.drawable.anime,"Anime & Manga")
        )

        list.add(
            CategoryModel("32",R.drawable.cartoon,"Cartoons & Animations")
        )
        return list
    }
}