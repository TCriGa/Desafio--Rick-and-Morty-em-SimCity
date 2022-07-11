package br.com.zup.rickAndMortyEmSimCity.data.datasource.remote

import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterAPI {

    @GET("character")
    suspend fun getAllCharacterNetwork(
        @Query("page")
        pages: Int = 6
    ): CharacterResponse
}