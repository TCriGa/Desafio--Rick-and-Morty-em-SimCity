package br.com.zup.rickAndMortyEmSimCity.ui.characterList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.databinding.CharacterListItemBinding
import com.squareup.picasso.Picasso

class CharacterListAdapter(
    private var personageList: MutableList<CharacterResult>,
    private val clickPersonage : (CharacterResult : CharacterResult) -> Unit

    ) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CharacterListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    fun updateMovieList(newList: List<CharacterResult>) {
        personageList = newList as MutableList<CharacterResult>
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personage = personageList[position]
        holder.showPersonageInfo(personage)
        holder.binding.cvItemList.setOnClickListener {
            clickPersonage(personage)
        }
    }

    override fun getItemCount(): Int = personageList.size

    class ViewHolder(val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun showPersonageInfo(characterResult: CharacterResult){
            binding.textNamePersonage.text = characterResult.name
            Picasso.get().load(characterResult.image)
                .into(binding.imageIcRickMorty)
        }
    }
}