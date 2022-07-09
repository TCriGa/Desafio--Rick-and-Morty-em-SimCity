package br.com.zup.rickAndMortyEmSimCity.data.datasource.model


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val info: Info = Info(),
    @SerializedName("results")
    val results: List<CharacterResult> = listOf()
)