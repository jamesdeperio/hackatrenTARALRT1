package nyxdev.hackatren.taralrt1.global.model.obj.account

import com.google.gson.annotations.SerializedName

data class Profile (
@field:[SerializedName("fldAccountCode")]
 var fldAccountCode:String? = null,
@field:[SerializedName("fldName")]
var fldName:String?=null,
@field:[SerializedName("fldEmailAddress")]
var fldEmailAddress:String? =null
)
