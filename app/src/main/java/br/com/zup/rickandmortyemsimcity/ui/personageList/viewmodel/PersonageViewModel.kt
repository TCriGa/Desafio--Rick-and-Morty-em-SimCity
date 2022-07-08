package br.com.zup.rickandmortyemsimcity.ui.personageList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.rickandmortyemsimcity.data.datasource.remote.RetrofitService
import br.com.zup.rickandmortyemsimcity.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonageViewModel : ViewModel() {
    var viewSate = ViewState()

    fun getAllPersonage() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    RetrofitService.apiService.getAllCharacterNetwork()
                }
                viewSate.state.value = ViewState.State.SUCCESS
            } catch (ex: Exception) {
                viewSate.state.value = ViewState.State.ERROR
            }


        }
    }


}