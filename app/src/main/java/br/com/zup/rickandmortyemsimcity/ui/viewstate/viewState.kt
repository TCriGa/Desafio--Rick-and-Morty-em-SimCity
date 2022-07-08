package br.com.zup.rickandmortyemsimcity.ui.viewstate

import androidx.lifecycle.MutableLiveData
import br.com.zup.rickandmortyemsimcity.data.datasource.model.CharacterResult

class ViewState {

    var character = MutableLiveData<List<CharacterResult>>()
    var state: MutableLiveData<State> = MutableLiveData()

    enum class State(message: String) {
        SUCCESS("Informação carregada com Sucesso"), ERROR("Não foi possível carregar a lista de informações ")
    }
}