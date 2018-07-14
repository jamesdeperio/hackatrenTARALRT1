package nyxdev.hackatren.taralrt1.global.model.event

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

data class GoogleAuth(
        var task: Task<GoogleSignInAccount>? = null
)
