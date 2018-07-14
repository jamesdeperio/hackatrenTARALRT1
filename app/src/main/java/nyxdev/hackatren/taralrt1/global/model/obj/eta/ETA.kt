package nyxdev.hackatren.taralrt1.global.model.obj.eta

import com.google.gson.annotations.SerializedName


data class ETA(
        @field:[SerializedName(value = "rows")]
        var rows: MutableList<Rows> = ArrayList()
)
