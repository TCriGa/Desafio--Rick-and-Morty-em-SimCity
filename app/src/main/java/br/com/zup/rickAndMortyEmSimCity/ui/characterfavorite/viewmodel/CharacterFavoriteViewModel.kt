package br.com.zup.rickAndMortyEmSimCity.ui.characterfavorite.viewmodel

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

class CharacterFavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val characterUseCase = CharacterUseCase(application)
    val characterListFavoriteState = MutableLiveData<ViewState<List<CharacterResult>>>()
    val characterListDisfavorState = MutableLiveData<ViewState<CharacterResult>>()

    fun getCharactersFavorite() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    characterUseCase.getCharactersFavorite()
                }
                characterListFavoriteState.value = response
            } catch (ex: Exception) {
                characterListFavoriteState.value = ViewState.Error(
                    Throwable(
                        "Não foi possível " +
                                "carregar a lista de personagens favoritos! Tente novamente! :D"
                    )
                )
            }
        }
    }
}