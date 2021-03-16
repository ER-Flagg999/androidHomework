package com.erflagg.myproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erflagg.myproject.databinding.FilmItemBinding

//в параметр передаем слушатель, чтобы мы потом могли обрабатывать нажатия из класса Activity
class FilmListRecyclerAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //Здесь у нас хранится список элементов для RV
    private val items = mutableListOf<Film>()

    //Этот метод нужно переопределить на возврат количества элементов в списке RV
    override fun getItemCount() = items.size

    //В этом методе мы привязываем наш ViewHolder и передаем туда "надутую" верстку нашего фильма
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    //В этом методе будет привязка полей из объекта Film к View из film_item.xml
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val filmViewHolder = holder as FilmViewHolder
//        filmViewHolder.itemBinding.title.text = items[position].title
//        filmViewHolder.itemBinding.description.text = items[position].description
//        filmViewHolder.itemBinding.poster.setImageResource(items[position].poster)
        when(holder) {
            is FilmViewHolder -> {
                holder.bind(items[position])
                holder.itemBinding.itemContainer.setOnClickListener {
                    clickListener.click(items[position])
                }
            }
        }

//        holder.itemView.setOnClickListener {
//            filmViewHolder.bind(items[position])
//        }

        }

    //Метод для добавления объектов в наш список
    fun addItems(list: List<Film>) {
        //Сначала очищаем(если не реализовать DiffUtils)
        items.clear()
        //Добавляем
        items.addAll(list)
        //Уведомляем RV, что пришел новый список, и ему нужно заново все "привязывать"
        notifyDataSetChanged()
    }


    //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(film: Film)
    }
}