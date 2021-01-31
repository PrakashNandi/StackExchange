package com.candyspace.stackexchange.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Prakash Nandi on 31/01/21.
 */
class User() : Parcelable {
    var badge_counts: BadgeCounts? = null
    var account_id: Int = 0
    var is_employee: Boolean = false
    var last_modified_date: Long = 0
    var last_access_date: Long = 0
    var reputation_change_year: Int = 0
    var reputation_change_quarter: Int = 0
    var reputation_change_month: Int = 0
    var reputation_change_week: Int = 0
    var reputation_change_day: Int = 0
    var reputation: Int = 0
    var creation_date: Long = 0
    var user_type: String? = ""
    var user_id: Int = 0
    var accept_rate: Int = 0
    var location: String? = ""
    var website_url: String? = ""
    var link: String? = ""
    var profile_image: String? = ""
    var display_name: String? = ""

    constructor(parcel: Parcel) : this() {
        badge_counts = parcel.readParcelable(BadgeCounts::class.java.classLoader)
        account_id = parcel.readInt()
        is_employee = parcel.readByte() != 0.toByte()
        last_modified_date = parcel.readLong()
        last_access_date = parcel.readLong()
        reputation_change_year = parcel.readInt()
        reputation_change_quarter = parcel.readInt()
        reputation_change_month = parcel.readInt()
        reputation_change_week = parcel.readInt()
        reputation_change_day = parcel.readInt()
        reputation = parcel.readInt()
        creation_date = parcel.readLong()
        user_type = parcel.readString()
        user_id = parcel.readInt()
        accept_rate = parcel.readInt()
        location = parcel.readString()
        website_url = parcel.readString()
        link = parcel.readString()
        profile_image = parcel.readString()
        display_name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(badge_counts, flags)
        parcel.writeInt(account_id)
        parcel.writeByte(if (is_employee) 1 else 0)
        parcel.writeLong(last_modified_date)
        parcel.writeLong(last_access_date)
        parcel.writeInt(reputation_change_year)
        parcel.writeInt(reputation_change_quarter)
        parcel.writeInt(reputation_change_month)
        parcel.writeInt(reputation_change_week)
        parcel.writeInt(reputation_change_day)
        parcel.writeInt(reputation)
        parcel.writeLong(creation_date)
        parcel.writeString(user_type)
        parcel.writeInt(user_id)
        parcel.writeInt(accept_rate)
        parcel.writeString(location)
        parcel.writeString(website_url)
        parcel.writeString(link)
        parcel.writeString(profile_image)
        parcel.writeString(display_name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}