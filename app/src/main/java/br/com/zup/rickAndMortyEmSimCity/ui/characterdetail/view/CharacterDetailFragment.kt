package br.com.zup.rickAndMortyEmSimCity.ui.characterdetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.zup.rickAndMortyEmSimCity.*
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.databinding.FragmentCharacterDetailBinding
import br.com.zup.rickAndMortyEmSimCity.ui.characterList.viewmodel.CharacterViewModel
import br.com.zup.rickAndMortyEmSimCity.ui.characterdetail.viewmodel.CharacterDetailViewModel
import br.com.zup.rickAndMortyEmSimCity.ui.viewstate.ViewState
import com.squareup.picasso.Picasso

class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPassedData()
        initObserver()
    }

    private val viewModel: CharacterDetailViewModel by lazy {
        ViewModelProvider(this)[CharacterDetailViewModel::class.java]
    }

    private fun getPassedData() {
        val characterDetail = arguments?.getParcelable<CharacterResult>(BUNDLE_KEY)
        characterDetail?.let {
            Picasso.get().load(it.image)
                .into(binding.imageRickSanches)
            binding.textNamePersonage.text = "$NAME ${it.name}"
            binding.textSpecie.text = "$SPECIE ${it.species}"
            binding.textStatus.text = "$STATUS ${it.status}"
            binding.textGender.text = "$GENDER  ${it.gender}"
            clickImageFavorite(it)
            (activity as AppCompatActivity).supportActionBar?.title = it.name
        }
    }

    private fun initObserver() {
        viewModel.characterFavoriteDetailState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    binding.imageFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.root.context,
                            if (it.data.isFavorite)
                                R.drawable.ic_favorite

                            else
                                R.drawable.ic_disfavor
                        )
                    )
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

    private fun updateDetailCharacter(characterResult: CharacterResult) {
        viewModel.updateDetailFavorite(characterResult)
    }

    private fun clickImageFavorite(characterResult: CharacterResult) {
        binding.imageFavorite.setOnClickListener {
            characterResult.isFavorite = !characterResult.isFavorite
            updateDetailCharacter(characterResult)
           if (characterResult.isFavorite){
               Toast.makeText(
                   context,
                   "Personagem ${characterResult.name} foi favoritado com sucesso!",
                   Toast.LENGTH_LONG
               ).show()
           }else{
               Toast.makeText(
                   context,
                   "Personagem ${characterResult.name} foi desfavoritado com sucesso!",
                   Toast.LENGTH_LONG
               ).show()
           }
        }
    }
}
