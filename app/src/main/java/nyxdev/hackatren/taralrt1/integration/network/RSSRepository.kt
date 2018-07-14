package nyxdev.hackatren.taralrt1.integration.network

import io.reactivex.Observable
import nyxdev.hackatren.taralrt1.global.model.obj.rss.RSS
import nyxdev.hackatren.taralrt1.global.scope.Xml
import retrofit2.http.GET
import retrofit2.http.Url

interface RSSRepository {
    @GET @Xml
    fun announcementRequest(@Url url:String="http://lrmc.ph/category/news/feed/" ): Observable<RSS>
}
