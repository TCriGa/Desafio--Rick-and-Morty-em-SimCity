package br.com.zup.rickAndMortyEmSimCity.ui.characterList.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.rickAndMortyEmSimCity.ERROR_LIST_CHARACTER
import br.com.zup.rickAndMortyEmSimCity.season.CharacterEvents
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.domain.usecase.CharacterUseCase
import br.com.zup.rickAndMortyEmSimCity.season.CharacterInteraction
import br.com.zup.rickAndMortyEmSimCity.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(application: Application) : AndroidViewModel(application) {
    private val characterUseCase = CharacterUseCase(application)
    val characterListState = MutableLiveData<ViewState<List<CharacterResult>>>()
    val characterViewState = MutableLiveData<CharacterEvents>()

    fun getAllPersonage(page : Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    characterUseCase.getAllCharacterNetwork(page)
                }
                characterListState.value = response

            } catch (ex: Exception) {
                characterListState.value =
                    ViewState.Error(Throwable(ERROR_LIST_CHARACTER))
            }
        }
    }


    fun interpret(interector: CharacterInteraction) {
        when (interector) {
            is CharacterInteraction.ShowList -> getAllPersonage(interector.page)

            else -> {}
        }
    }

}