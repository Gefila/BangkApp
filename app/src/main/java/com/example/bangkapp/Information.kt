package com.example.bangkapp

import android.os.Parcel
import android.os.Parcelable

data class Information(
    val id:Int?,
    val email:String,
    val username: String,
    val comment: String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(email)
        parcel.writeString(username)
        parcel.writeString(comment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Information> {
        override fun createFromParcel(parcel: Parcel): Information {
            return Information(parcel)
        }

        override fun newArray(size: Int): Array<Information?> {
            return arrayOfNulls(size)
        }
    }
}

