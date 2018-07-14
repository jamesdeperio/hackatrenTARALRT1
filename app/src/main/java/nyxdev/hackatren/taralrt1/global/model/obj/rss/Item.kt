package nyxdev.hackatren.taralrt1.global.model.obj.rss

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "item")
data class Item (
        @field:[PropertyElement]
        var title:String? = null,
        @field:[PropertyElement]
        var pubDate:String? = null,
        @field:[PropertyElement(name = "content:encoded", writeAsCData = true)]
        var content:String? = null,
        var image:String? = null
)
