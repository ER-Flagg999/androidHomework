package com.erflagg.myproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.erflagg.myproject.databinding.FragmentDetailsBinding

private var binding : FragmentDetailsBinding? = null

class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        setFilmsDetails()
    }

    private fun setFilmsDetails() {
        //Получаем наш фильм из переданного бандла
        val film = arguments?.get("film") as Film

        //Устанавливаем заголовок
        binding?.detailsToolbar?.title = film.title
        //Устанавливаем картинку
        binding?.detailsPoster?.setImageResource(film.poster)
        //Устанавливаем описание
        binding?.detailsDescription?.text = film.description
    }
}