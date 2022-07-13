package br.com.zup.rickAndMortyEmSimCity.season

sealed class CharacterInteraction {

    data class ShowList(val page: Int) : CharacterInteraction()
    data class CharacterDetail(val character: Character) : CharacterInteraction()

}