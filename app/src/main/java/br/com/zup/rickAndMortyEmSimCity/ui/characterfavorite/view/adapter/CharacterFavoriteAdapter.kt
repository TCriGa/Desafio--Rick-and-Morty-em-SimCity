package br.com.zup.rickAndMortyEmSimCity.ui.characterfavorite.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.databinding.CharacterListItemBinding
import com.squareup.picasso.Picasso

class CharacterFavoriteAdapter(
    private var characterList: MutableList<CharacterResult>,
    private val clickDisfavorCharacter: (CharacterResult: CharacterResult) -> Unit

) : RecyclerView.Adapter<CharacterFavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CharacterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]
        holder.showCharacterInfo(character)
        holder.binding.cvItemList.setOnClickListener {
            clickDisfavorCharacter(character)
        }
    }

    override fun getItemCount(): Int = characterList.size

    fun updateCharacterList(newList: List<CharacterResult>) {
        characterList = newList as MutableList<CharacterResult>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: CharacterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun showCharacterInfo(characterResult: CharacterResult) {
            binding.textNamePersonage.text = characterResult.name
            Picasso.get().load(characterResult.image)
                .into(binding.imageIcRickMorty)
        }
    }
}