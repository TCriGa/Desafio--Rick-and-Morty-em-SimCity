package br.com.zup.rickAndMortyEmSimCity.ui.characterList.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.databinding.CharacterListItemBinding
import com.squareup.picasso.Picasso

class CharacterListAdapter(
    private var characterList: MutableList<CharacterResult>,
    private val clickCharacter: (CharacterResult: CharacterResult) -> Unit


) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CharacterListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personage = characterList[position]
        holder.showPersonageInfo(personage)
        holder.binding.cvItemList.setOnClickListener {
            clickCharacter(personage)
        }
    }

    override fun getItemCount(): Int = characterList.size

    fun updateCharacterList(newList: MutableList<CharacterResult>) {
        characterList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: CharacterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun showPersonageInfo(characterResult: CharacterResult) {
            binding.textNamePersonage.text = characterResult.name
            Picasso.get().load(characterResult.image)
                .into(binding.imageIcRickMorty)

        }
    }
}