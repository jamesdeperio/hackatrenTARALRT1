package nyxdev.hackatren.taralrt1.global.model.obj.rss

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss", writeNamespaces = [
    "content=http://purl.org/rss/1.0/modules/content/",
    "wfw=http://wellformedweb.org/CommentAPI/",
    "dc=http://purl.org/dc/elements/1.1/",
    "atom=http://www.w3.org/2005/Atom",
    "sy=http://purl.org/rss/1.0/modules/syndication/",
    "slash=http://purl.org/rss/1.0/modules/slash/"
])
data class RSS (
        @field:[Element(name = "channel")]
        var channel: Channel? = null
)
