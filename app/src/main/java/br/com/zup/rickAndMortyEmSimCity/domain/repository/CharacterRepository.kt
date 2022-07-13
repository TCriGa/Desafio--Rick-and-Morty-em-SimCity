package br.com.zup.rickAndMortyEmSimCity.domain.repository

import br.com.zup.rickAndMortyEmSimCity.data.datasource.local.dao.CharacterDAO
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResponse
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult
import br.com.zup.rickAndMortyEmSimCity.data.datasource.remote.RetrofitService

class CharacterRepository(private val characterDAO: CharacterDAO) {

    suspend fun getInformationCharacter(): List<CharacterResult> =
        characterDAO.getInformationCharacter()

    suspend fun insertInformationCharacterDB(listCharacter: List<CharacterResult>) =
        characterDAO.insertInformationCharacter(listCharacter)

    suspend fun geAllCharacterNetwork(page : Int): CharacterResponse =
        RetrofitService.apiService.getAllCharacterNetwork(page)

    suspend fun getCharactersFavorite(): List<CharacterResult> =
        characterDAO.getCharactersFavorite()

    suspend fun updateCharacter(character: CharacterResult) =
        characterDAO.updateCharacter(character)
}