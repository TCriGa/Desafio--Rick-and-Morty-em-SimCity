package br.com.zup.rickandmortyemsimcity.data.datasource.model


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val info: Info = Info(),
    @SerializedName("results")
    val results: List<CharacterResult> = listOf()
)