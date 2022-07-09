package br.com.zup.rickAndMortyEmSimCity.ui.characterList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.rickAndMortyEmSimCity.BUNDLE_KEY
import br.com.zup.rickAndMortyEmSimCity.R
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.databinding.FragmentCharacterListBinding
import br.com.zup.rickAndMortyEmSimCity.ui.characterList.viewmodel.CharacterViewModel

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
        viewModel.getAllPersonage()
        exhibitRecycleView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.characterResponse.observe(this.viewLifecycleOwner) {
            adapterList.updateMovieList(it)
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