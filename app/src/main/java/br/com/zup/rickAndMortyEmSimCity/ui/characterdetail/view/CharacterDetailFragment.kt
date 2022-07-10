package br.com.zup.rickAndMortyEmSimCity.ui.characterdetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.zup.rickAndMortyEmSimCity.*
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.databinding.FragmentCharacterDetailBinding
import com.squareup.picasso.Picasso

class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        getPassedData()
        return binding.root

    }

    private fun getPassedData(){
        val characterDetail = arguments?.getParcelable<CharacterResult>(BUNDLE_KEY)
        characterDetail?.let {
            Picasso.get().load(it.image)
                .into(binding.imageRickSanches)
            binding.textNamePersonage.text = "$NAME ${it.name}"
            binding.textSpecie.text = "$SPECIE ${it.species}"
            binding.textStatus.text = "$STATUS ${ it.status }"
            binding.textGender.text = "$GENDER  ${it.gender}"

            (activity as AppCompatActivity).supportActionBar?.title = it.name
        }

    }
}