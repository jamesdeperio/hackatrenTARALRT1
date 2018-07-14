package nyxdev.hackatren.taralrt1.global.model.obj.account

import com.google.gson.annotations.SerializedName

data class Accounts (
        @field:[SerializedName("CycleCountTrans")]
        var accounts: MutableList<Profile> =ArrayList<Profile>()
)
