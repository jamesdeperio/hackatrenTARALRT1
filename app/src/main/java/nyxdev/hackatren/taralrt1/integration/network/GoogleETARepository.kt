package nyxdev.hackatren.taralrt1.integration.network

import io.reactivex.Observable
import nyxdev.hackatren.taralrt1.global.model.obj.eta.ETA
import nyxdev.hackatren.taralrt1.global.scope.Gson
import retrofit2.http.GET
import retrofit2.http.Url

interface GoogleETARepository {
    @GET @Gson
    fun etaRequest(@Url url:String ): Observable<ETA>
}
