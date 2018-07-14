package nyxdev.hackatren.taralrt1.global.model.obj.rss

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "channel")
data class Channel (
        @field:[Element(name = "item")]
        var itemList: MutableList<Item>? = null
)
