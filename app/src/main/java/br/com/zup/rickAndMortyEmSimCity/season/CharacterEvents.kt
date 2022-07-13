package br.com.zup.rickAndMortyEmSimCity.season

sealed class CharacterEvents {
    data class Loading(val status: Boolean) : CharacterEvents()
    data class ShowDetail(val character: Character) : CharacterEvents()
}