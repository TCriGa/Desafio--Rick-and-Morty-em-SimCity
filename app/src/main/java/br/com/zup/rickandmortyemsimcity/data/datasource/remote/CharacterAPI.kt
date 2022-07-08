package br.com.zup.rickandmortyemsimcity.data.datasource.remote

import br.com.zup.rickandmortyemsimcity.data.datasource.model.CharacterResponse
import retrofit2.http.GET

interface CharacterAPI {

    @GET("/character")
    suspend fun getAllCharacterNetwork(
        ): CharacterResponse
}