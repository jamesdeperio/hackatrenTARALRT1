package nyxdev.hackatren.taralrt1.global.model.obj.station

import com.google.gson.annotations.SerializedName

data class StationCrowdVolume (
        @field:[SerializedName("fldStationName")]
        var fldStationName:String? = null,
        @field:[SerializedName("lite")]
        var lite:String? = null,
        @field:[SerializedName("Moderate")]
        var moderate:String? = null,
        @field:[SerializedName("Heavy")]
        var heavy:String? = null,
        @field:[SerializedName("TotalPassenger")]
        var totalPassenger:Any? = null
)
