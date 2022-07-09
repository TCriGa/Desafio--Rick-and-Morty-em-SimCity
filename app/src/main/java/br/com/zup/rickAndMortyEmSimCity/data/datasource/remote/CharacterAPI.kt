package br.com.zup.rickAndMortyEmSimCity.data.datasource.remote

import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResponse
import retrofit2.http.GET

interface CharacterAPI {

    @GET("character")
    suspend fun getAllCharacterNetwork(
        ): CharacterResponse
}