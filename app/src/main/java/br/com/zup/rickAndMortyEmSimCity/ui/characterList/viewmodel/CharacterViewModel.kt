package br.com.zup.rickAndMortyEmSimCity.ui.characterList.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.domain.usecase.CharacterUseCase
import br.com.zup.rickAndMortyEmSimCity.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val characterUseCase = CharacterUseCase(application)
    val characterListState = MutableLiveData<ViewState<List<CharacterResult>>>()

    fun getAllPersonage() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    characterUseCase.getAllCharacterNetwork()
                }
                characterListState.value = response

            } catch (ex: Exception) {
                characterListState.value =
                    ViewState.Error(Throwable("Não foi possível carregar a lista!"))
            }
        }
    }
}