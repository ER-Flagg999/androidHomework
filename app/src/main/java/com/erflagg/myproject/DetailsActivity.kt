package com.erflagg.myproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erflagg.myproject.databinding.ActivityDetailsBinding

private var binding : ActivityDetailsBinding? = null

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setFilmsDetails()
    }

    private fun setFilmsDetails() {
        //Получаем наш фильм из переданного бандла
        val film = intent.extras?.get("film") as Film

        //Устанавливаем заголовок
        binding?.detailsToolbar?.title = film.title
        //Устанавливаем картинку
        binding?.detailsPoster?.setImageResource(film.poster)
        //Устанавливаем описание
        binding?.detailsDescription?.text = film.description
    }
}