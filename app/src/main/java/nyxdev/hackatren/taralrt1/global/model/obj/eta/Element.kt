package nyxdev.hackatren.taralrt1.global.model.obj.eta

import com.google.gson.annotations.SerializedName

data class Element(
        @field:[SerializedName(value = "duration")]
        var duration: Duration? = null,
        @field:[SerializedName(value = "fare")]
        var fare: Fare? = null,
        @field:[SerializedName(value = "distance")]
        var distance: Distance? = null


)
