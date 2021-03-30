package com.erflagg.myproject

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erflagg.myproject.databinding.FilmItemBinding


class FilmViewHolder(var itemBinding: FilmItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    private val title = itemBinding.title
    private val poster = itemBinding.poster
    private val description = itemBinding.description

    fun bind(film: Film) {
        title.text = film.title
        //Указываем контейнер, в котором будет "жить" наша картинка
        Glide.with(itemView)
            //Загружаем сам ресурс
            .load(film.poster)
            //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(poster)
        description.text = film.description
    }
}