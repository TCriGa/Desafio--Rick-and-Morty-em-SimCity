package br.com.zup.rickAndMortyEmSimCity.data.datasource.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CharacterResult(
    @SerializedName("created")
    var created: String = "",
    @SerializedName("episode")
    var episode: List<String> = listOf(),
    @SerializedName("gender")
    var gender: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("image")
    var image: String = "",
    @SerializedName("location")
    var location:@RawValue Location = Location(),
    @SerializedName("name")
    var name: String = "",
    @SerializedName("origin")
    var origin:@RawValue Origin = Origin(),
    @SerializedName("species")
    var species: String = "",
    @SerializedName("status")
    var status: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("url")
    var url: String = ""
) : Parcelable