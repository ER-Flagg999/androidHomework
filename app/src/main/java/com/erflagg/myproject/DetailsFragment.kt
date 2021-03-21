package com.erflagg.myproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.erflagg.myproject.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding : FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    //Получаем наш фильм из переданного бандла
    private lateinit var film : Film

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilmsDetails()
        setFavoriteImage()

        binding.detailsFabFavorites.setOnClickListener {
            if(!film.isInFavorites) {
                film.isInFavorites = true
                setFavoriteImage()
            } else {
                film.isInFavorites = false
                setFavoriteImage()
            }
        }

        binding.detailsFabShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Зацени фильм: ${film.title} \n\n ${film.description}"
            )
            intent.type = "text/plan"
            startActivity(Intent.createChooser(intent, "Отправить:"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setFilmsDetails() {
        film = arguments?.get("film") as Film
        //Устанавливаем заголовок
        binding.detailsToolbar.title = film.title
        //Устанавливаем картинку
        binding.detailsPoster.setImageResource(film.poster)
        //Устанавливаем описание
        binding.detailsDescription.text = film.description

    }

    private fun setFavoriteImage() {
        binding.detailsFabFavorites.setImageResource(
            if (film.isInFavorites) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
        )
    }
}