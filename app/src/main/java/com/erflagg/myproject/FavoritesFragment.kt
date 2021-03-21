package com.erflagg.myproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.erflagg.myproject.databinding.FragmentFavoritesBinding
import com.erflagg.myproject.databinding.FragmentHomeBinding


class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favoriteList: List<Film> = emptyList()
        binding.favoritesRecycler
                .apply {
                    filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                        override fun click(film: Film) {
                            (requireActivity() as MainActivity).launchDetailsFragment(film)
                        }
                    })
                    adapter = filmsAdapter
                    val decorator = TopSpacingItemDecoration(8)
                    addItemDecoration(decorator)
                }
        filmsAdapter.addItems(favoriteList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}