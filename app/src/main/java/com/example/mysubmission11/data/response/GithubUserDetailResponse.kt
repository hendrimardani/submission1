package com.example.mysubmission11.data.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UserDetail(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String,
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString()!!,
		parcel.readInt(),
		parcel.readInt(),
		parcel.readString()!!
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(login)
		parcel.writeInt(followers)
		parcel.writeInt(following)
		parcel.writeString(name)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<UserDetail> {
		override fun createFromParcel(parcel: Parcel): UserDetail {
			return UserDetail(parcel)
		}

		override fun newArray(size: Int): Array<UserDetail?> {
			return arrayOfNulls(size)
		}
	}
}
