package br.com.zup.rickandmortyemsimcity.ui.personageList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickandmortyemsimcity.URL_BASE_IMAGE
import br.com.zup.rickandmortyemsimcity.data.datasource.model.CharacterResult
import br.com.zup.rickandmortyemsimcity.databinding.PersonageListItemBinding
import com.squareup.picasso.Picasso

class PersonageListAdapter(
    private var personageList: MutableList<CharacterResult>,
    private val clickPersonage : (CharacterResult : CharacterResult) -> Unit

    ) : RecyclerView.Adapter<PersonageListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PersonageListItemBinding.inflate(
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

    class ViewHolder(val binding: PersonageListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun showPersonageInfo(characterResult: CharacterResult){
            binding.textNamePersonage.text = characterResult.name
            Picasso.get().load(URL_BASE_IMAGE +characterResult.image)
                .into(binding.imageIcRickMorty)
        }
    }
}