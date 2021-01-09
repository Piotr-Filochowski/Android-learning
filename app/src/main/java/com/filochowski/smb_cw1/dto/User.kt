package com.filochowski.smb_cw1.dto

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GetTokenResult
import java.io.Serializable

data class User(
    var name: String?,
    var email: String?,
    var photoUrl: Uri?,
    var verifiedEmail: Boolean,
    var uid: String,
    var tokenResult: Task<GetTokenResult>
) : Serializable {

}