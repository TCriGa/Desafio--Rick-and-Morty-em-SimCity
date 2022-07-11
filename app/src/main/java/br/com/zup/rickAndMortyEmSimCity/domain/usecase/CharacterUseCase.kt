package br.com.zup.rickAndMortyEmSimCity.domain.usecase

import android.app.Application
import br.com.zup.rickAndMortyEmSimCity.ERROR_BD
import br.com.zup.rickAndMortyEmSimCity.ERROR_FAVORITE_LIST
import br.com.zup.rickAndMortyEmSimCity.ERROR_CHARACTER
import br.com.zup.rickAndMortyEmSimCity.data.datasource.local.CharacterDataBase
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.domain.repository.CharacterRepository
import br.com.zup.rickAndMortyEmSimCity.ui.viewstate.ViewState

class CharacterUseCase(application: Application) {
    private val characterDao = CharacterDataBase.getDatabase(application).characterDao()
    private val characterRepository = CharacterRepository(characterDao)

    private suspend fun getInformationCharacter(): ViewState<List<CharacterResult>> {
        return try {
            val characterResult = characterRepository.getInformationCharacter()
            ViewState.Success(characterResult)

        } catch (ex: Exception) {
            ViewState.Error(Exception(ERROR_BD))
        }
    }

    suspend fun getAllCharacterNetwork(): ViewState<List<CharacterResult>> {
        return try {
            val response = characterRepository.geAllCharacterNetwork()
            characterRepository.insertInformationCharacterDB(response.results)
            ViewState.Success(response.results)
            getInformationCharacter()
        } catch (ex: Exception) {
            getInformationCharacter()
        }
    }


    suspend fun getCharactersFavorite(): ViewState<List<CharacterResult>> {
        return try {
            val character = characterRepository.getCharactersFavorite()
            ViewState.Success(character)
        } catch (ex: Exception) {
            ViewState.Error(
                Exception(
                    ERROR_FAVORITE_LIST
                )
            )
        }
    }

    suspend fun updateCharacter(characterResult: CharacterResult): ViewState<CharacterResult> {
        return try {
            characterRepository.updateCharacter(characterResult)
            ViewState.Success(characterResult)
        } catch (ex: Exception) {
            ViewState.Error(Exception(ERROR_CHARACTER))
        }
    }

}
