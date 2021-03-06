package com.erflagg.myproject

import android.os.Bundle
import android.transition.Scene
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erflagg.myproject.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.merge_home_screen_content.*
import java.util.*


class HomeFragment : Fragment() {

    private val filmsDataBase = listOf(
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

    //private var _mergeBinding: MergeHomeScreenContentBinding?= null
    //private val mergeBinding get() = _mergeBinding!!

    private var _homeBinding: FragmentHomeBinding? = null
    private val homeBinding get() = _homeBinding!!

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _homeBinding = FragmentHomeBinding.inflate(inflater, container,false)

        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //_mergeBinding = MergeHomeScreenContentBinding.bind(homeBinding.homeFragmentRoot)

        val scene = Scene.getSceneForLayout(homeBinding.homeFragmentRoot, R.layout.merge_home_screen_content, requireContext())
        //Создаем анимацию выезда поля поиска сверху
        val searchSlide = Slide(Gravity.TOP).addTarget(R.id.search_view)
        //Создаем анимацию выезда RV снизу
        val recyclerSlide = Slide(Gravity.BOTTOM).addTarget(R.id.main_recycler)
        //Создаем экземпляр TransitionSet, который объединит все наши анимации
        val customTransition = TransitionSet().apply {
            //Устанавливаем время, за которое будет проходить анимация
            duration = 500
            //Добавляем сами анимации
            addTransition(recyclerSlide)
            addTransition(searchSlide)
        }
        //Также запускаем через TransitionManager, но вторым параметром передаем нашу кастомную анимацию
        TransitionManager.go(scene, customTransition)

        searchViewInitializing()

        ininRecycler()

        //Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)



    }

    private fun searchViewInitializing() {
        search_view.setOnClickListener {
            search_view.isIconified = false
        }

        search_view.setOnQueryTextListener(object  : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }
                val result = filmsDataBase.filter {
                    it.title.toLowerCase(Locale.getDefault()).contains(newText.toLowerCase(Locale.getDefault()))
                }
                filmsAdapter.addItems(result)
                return true
            }

        })
    }

    private fun ininRecycler() {

        main_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                        search_view.visibility = View.INVISIBLE
                } else {
                    search_view.visibility = View.VISIBLE
                }
            }

        })

        //находим наш RV
        main_recycler.apply {
            //Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс,
            //оставим его пока пустым, он нам понадобится во второй части задания
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                override fun click(film: Film) {
                    (requireActivity() as MainActivity).launchDetailsFragment(film)
                }
            })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
//        _mergeBinding = null
        _homeBinding = null
    }

}