package org.firmanmardiyanto.yourmate.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val uid: String? = null,
    val name: String? = null,
    val email: String? = null,
    val status: String? = "offline",
    val profileImage: String? = "https://firebasestorage.googleapis.com/v0/b/yourmate-406e4.appspot.com/o/default-profile-picture.png?alt=media&token=7a104a65-ed48-44e3-9f90-f17442719416",
    val messagingToken: String? = null
) : Parcelable