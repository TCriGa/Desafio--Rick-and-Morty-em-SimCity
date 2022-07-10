package br.com.zup.rickAndMortyEmSimCity.domain.usecase

import android.app.Application
import br.com.zup.rickAndMortyEmSimCity.data.datasource.local.CharacterDataBase
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.domain.repository.CharacterRepository
import br.com.zup.rickAndMortyEmSimCity.ui.viewstate.ViewState

class CharacterUseCase(application: Application) {
    private val characterDao = CharacterDataBase.getDatabase(application).characterDao()
    private val characterRepository = CharacterRepository(characterDao)

    suspend fun getInformationCharacter(): ViewState<List<CharacterResult>> {
        return try {
            val characterResult = characterRepository.getInformationCharacter()
            ViewState.Success(characterResult)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de personagens do Banco de Dados"))
        }
    }

    suspend fun getAllCharacterNetwork(): ViewState<List<CharacterResult>>{
       return try {
           val response = characterRepository.geAllCharacterNetwork()
           characterRepository.insertInformationCharacterDB(response.results)
           ViewState.Success(response.results)
       }catch (ex:Exception){
           getInformationCharacter()
       }
    }

    suspend fun updateCharacter(characterResult: CharacterResult):ViewState<CharacterResult>{
        return try {
            characterRepository.updateCharacter(characterResult)
            ViewState.Success(characterResult)
        }catch (ex: Exception){
            ViewState.Error(Exception("Não foi possível atualizar o personagem"))
        }
    }
}
