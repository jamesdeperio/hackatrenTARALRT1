package nyxdev.hackatren.taralrt1.global.model.obj.account

import com.google.gson.annotations.SerializedName

data class Rewards (
        @field:[SerializedName("Rewards")]
        var rewards : MutableList<Rate> =ArrayList<Rate>()
)
