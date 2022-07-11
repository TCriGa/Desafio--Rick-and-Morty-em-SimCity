package br.com.zup.rickAndMortyEmSimCity.ui.characterList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.rickAndMortyEmSimCity.BUNDLE_KEY
import br.com.zup.rickAndMortyEmSimCity.R
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.databinding.FragmentCharacterListBinding
import br.com.zup.rickAndMortyEmSimCity.ui.characterList.viewmodel.CharacterViewModel
import br.com.zup.rickAndMortyEmSimCity.ui.characterfavorite.view.CharacterFavoriteAdapter
import br.com.zup.rickAndMortyEmSimCity.ui.viewstate.ViewState

class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding

    private val adapterList: CharacterListAdapter by lazy {
        CharacterListAdapter(arrayListOf(), this::goToPersonageDetail)
    }


    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
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
        exhibitRecycleView()
        viewModel.getAllPersonage()
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

    private fun exhibitRecycleView() {
        binding.rvListPersonage.adapter = adapterList
    }

    private fun goToPersonageDetail(characterResult: CharacterResult) {
        val bundle = bundleOf(BUNDLE_KEY to characterResult)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_personageListFragment_to_characterDetailFragment, bundle)
    }
}



