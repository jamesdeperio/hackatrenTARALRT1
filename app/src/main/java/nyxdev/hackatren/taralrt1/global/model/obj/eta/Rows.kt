package nyxdev.hackatren.taralrt1.global.model.obj.eta

import com.google.gson.annotations.SerializedName

data class Rows(
        @field:[SerializedName(value = "elements")]
        var elements: MutableList<Element> = ArrayList()
)
