package br.com.zup.rickAndMortyEmSimCity.ui.viewstate

import androidx.lifecycle.MutableLiveData
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult

class ViewState {

    var character = MutableLiveData<List<CharacterResult>>()
    var state: MutableLiveData<State> = MutableLiveData()

    enum class State(message: String) {
        SUCCESS("Informação carregada com Sucesso"), ERROR("Não foi possível carregar a lista de informações ")
    }
}