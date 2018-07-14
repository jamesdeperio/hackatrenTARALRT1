package nyxdev.hackatren.taralrt1.global.model.obj.station

import com.google.gson.annotations.SerializedName

data class Stations (
        @field:[SerializedName("StationCrownVolume")]
        var stations : MutableList<StationCrowdVolume> =ArrayList<StationCrowdVolume>()
)
