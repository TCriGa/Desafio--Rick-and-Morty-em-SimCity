package br.com.zup.rickAndMortyEmSimCity.data.datasource.local.dao

import androidx.room.*
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM character ORDER BY name ASC")
    fun getInformationCharacter(): List<CharacterResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertInformationCharacter(listCharacter :List<CharacterResult>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCharacter(character: CharacterResult)

    @Query("SELECT * FROM character WHERE isFavorite = 1")
    fun getCharactersFavorite(): List<CharacterResult>
}