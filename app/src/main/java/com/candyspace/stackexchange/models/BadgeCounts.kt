package com.candyspace.stackexchange.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Prakash Nandi on 31/01/21.
 */
class BadgeCounts() : Parcelable {
    var bronze: Int = 0
    var silver: Int = 0
    var gold: Int = 0

    constructor(parcel: Parcel) : this() {
        bronze = parcel.readInt()
        silver = parcel.readInt()
        gold = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bronze)
        parcel.writeInt(silver)
        parcel.writeInt(gold)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BadgeCounts> {
        override fun createFromParcel(parcel: Parcel): BadgeCounts {
            return BadgeCounts(parcel)
        }

        override fun newArray(size: Int): Array<BadgeCounts?> {
            return arrayOfNulls(size)
        }
    }
}