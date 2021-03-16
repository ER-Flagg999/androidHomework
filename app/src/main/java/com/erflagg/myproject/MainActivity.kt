package com.erflagg.myproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.erflagg.myproject.databinding.ActivityMainBinding

private var binding : ActivityMainBinding? = null

val filmsDataBase = listOf(
        Film("Star Wars", R.drawable.swepisode4, "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader."),
        Film("Star Wars: Episode V - The Empire Strikes Back", R.drawable.swepisode5, "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader and a bounty hunter named Boba Fett all over the galaxy."),
        Film("Star Wars: Episode VI - Return of the Jedi", R.drawable.swepisode6, "After a daring mission to rescue Han Solo from Jabba the Hutt, the Rebels dispatch to Endor to destroy the second Death Star. Meanwhile, Luke struggles to help Darth Vader back from the dark side without falling into the Emperor's trap."),
        Film("Star Wars: Episode I - The Phantom Menace", R.drawable.swepisode1, "Two Jedi escape a hostile blockade to find allies and come across a young boy who may bring balance to the Force, but the long dormant Sith resurface to claim their original glory."),
        Film("Star Wars: Episode II - Attack of the Clones", R.drawable.swepisode2, "Ten years after initially meeting, Anakin Skywalker shares a forbidden romance with Padmé Amidala, while Obi-Wan Kenobi investigates an assassination attempt on the senator and discovers a secret clone army crafted for the Jedi."),
        Film("Star Wars: Episode III - Revenge of the Sith ", R.drawable.swepisode3, "Three years into the Clone Wars, the Jedi rescue Palpatine from Count Dooku. As Obi-Wan pursues a new threat, Anakin acts as a double agent between the Jedi Council and Palpatine and is lured into a sinister plan to rule the galaxy."),
        Film("Star Wars: Episode VII - The Force Awakens", R.drawable.swepisode7, "As a new threat to the galaxy rises, Rey, a desert scavenger, and Finn, an ex-stormtrooper, must join Han Solo and Chewbacca to search for the one hope of restoring peace."),
        Film("Star Wars: Episode VIII - The Last Jedi", R.drawable.swepisode8, "Rey develops her newly discovered abilities with the guidance of Luke Skywalker, who is unsettled by the strength of her powers. Meanwhile, the Resistance prepares for battle with the First Order."),
        Film("Star Wars: Episode IX - The Rise of Skywalker", R.drawable.swepisode9, "The surviving members of the resistance face the First Order once again, and the legendary conflict between the Jedi and the Sith reaches its peak bringing the Skywalker saga to its end."),
)

private lateinit var filmsAdapter: FilmListRecyclerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initNavigation()

        //находим наш RV
        binding?.mainRecycler?.apply {
            //Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс,
            //оставим его пока пустым, он нам понадобится во второй части задания
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
                override fun click(film: Film) {
                    //Создаем бандл и кладем туда объект с данными фильма
                    val bundle = Bundle()
                    //Первым параметром указывается ключ, по которому потом будем искать, вторым сам
                    //передаваемы объект
                    bundle.putParcelable("film", film)
                    //Запускаем наше активити
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    .putExtras(bundle)
                    //Запускаем активити через интент
                    startActivity(intent)
                }
            })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
//Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)


    }

    private fun initNavigation() {

        binding?.topAppBar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this@MainActivity, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        binding?.bottomNavigation?.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this@MainActivity, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this@MainActivity, "Посмотреть позже", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.complation -> {
                    Toast.makeText(this@MainActivity, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}