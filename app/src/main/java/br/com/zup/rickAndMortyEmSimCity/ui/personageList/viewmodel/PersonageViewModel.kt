package br.com.zup.rickAndMortyEmSimCity.ui.personageList.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.data.datasource.remote.RetrofitService
import br.com.zup.rickAndMortyEmSimCity.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonageViewModel : ViewModel() {
    private val _characterResponse = MutableLiveData<List<CharacterResult>>()
    val characterResponse: LiveData<List<CharacterResult>> = _characterResponse
   private val viewState = ViewState()
    fun getAllPersonage() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitService.apiService.getAllCharacterNetwork()
                }
                _characterResponse.value = response.results
            } catch (ex: Exception) {
                viewState.state.value = ViewState.State.ERROR

            }
        }
    }
}