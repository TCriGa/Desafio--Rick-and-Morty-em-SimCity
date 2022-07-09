package br.com.zup.rickAndMortyEmSimCity.ui.personageList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.databinding.FragmentPersonageListBinding
import br.com.zup.rickAndMortyEmSimCity.ui.personageList.viewmodel.PersonageViewModel

class PersonageListFragment : Fragment() {

    private lateinit var binding: FragmentPersonageListBinding

    private val adapterList : PersonageListAdapter by lazy {
        PersonageListAdapter(arrayListOf(), this :: goToPersonageDetail)
    }

    private val viewModel : PersonageViewModel by lazy {
        ViewModelProvider(this)[PersonageViewModel :: class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonageListBinding.inflate(inflater,container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllPersonage()
        exhibitRecycleView()
        initObserver()
    }

    private fun initObserver(){
        viewModel.characterResponse.observe(this.viewLifecycleOwner){
            adapterList.updateMovieList(it)
        }
    }

    private fun exhibitRecycleView(){
        binding.rvListPersonage.adapter = adapterList
    }

    private fun goToPersonageDetail(characterResult: CharacterResult){

    }
}