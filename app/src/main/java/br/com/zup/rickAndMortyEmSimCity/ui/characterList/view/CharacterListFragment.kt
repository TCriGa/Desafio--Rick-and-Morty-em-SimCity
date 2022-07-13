package br.com.zup.rickAndMortyEmSimCity.ui.characterList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickAndMortyEmSimCity.BUNDLE_KEY
import br.com.zup.rickAndMortyEmSimCity.R
import br.com.zup.rickAndMortyEmSimCity.season.CharacterEvents
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.databinding.FragmentCharacterListBinding
import br.com.zup.rickAndMortyEmSimCity.season.CharacterInteraction
import br.com.zup.rickAndMortyEmSimCity.ui.characterList.view.adapter.CharacterListAdapter
import br.com.zup.rickAndMortyEmSimCity.ui.characterList.viewmodel.CharacterListViewModel
import br.com.zup.rickAndMortyEmSimCity.ui.viewstate.ViewState
import java.util.*

class CharacterListFragment : Fragment() {
    private var page = 1
    private lateinit var binding: FragmentCharacterListBinding

    private val adapterList: CharacterListAdapter by lazy {
        CharacterListAdapter(arrayListOf(), this::goToPersonageDetail)
    }


    private val viewModel: CharacterListViewModel by lazy {
        ViewModelProvider(this)[CharacterListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycleView()
        viewModel.getAllPersonage(page)
        scrollPaginationList()
        initObserver()
    }

    private fun initObserver() {
        viewModel.characterListState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    adapterList.updateCharacterList(it.data.toMutableList())
                    goToFavoriteList(it.data)
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()

                }
                else -> {}
            }
        }
    }

    private fun goToFavoriteList(characterResult: List<CharacterResult>) {
        val bundle = bundleOf(BUNDLE_KEY to characterResult)
        binding.floatingActionButton.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_personageListFragment_to_characterFavoriteFragment, bundle)

        }
    }

    private fun setRecycleView() {
        binding.rvListPersonage.adapter = adapterList
    }

    private fun goToPersonageDetail(characterResult: CharacterResult) {
        val bundle = bundleOf(BUNDLE_KEY to characterResult)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_personageListFragment_to_characterDetailFragment, bundle)
    }

    private fun scrollPaginationList() {

        binding.rvListPersonage.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                @NonNull recyclerView: RecyclerView, dx: Int, dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0)
                 {
                    page++
                    viewModel.interpret(CharacterInteraction.ShowList(page = page))
                }
            }
        })
    }
}



