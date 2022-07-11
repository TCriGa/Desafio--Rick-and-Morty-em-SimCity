package br.com.zup.rickAndMortyEmSimCity.ui.characterdetail.viewmodel

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

class CharacterDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val characterUseCase = CharacterUseCase(application)
    val characterFavoriteDetailState = MutableLiveData<ViewState<CharacterResult>>()

    fun updateDetailFavorite(characterResult: CharacterResult) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    characterUseCase.updateCharacter(characterResult)
                }
                characterFavoriteDetailState.value = response
            } catch (ex: Exception) {
                characterFavoriteDetailState.value =
                    ViewState.Error(Throwable("Não foi possivel atualizar o filme"))
            }
        }
    }

}