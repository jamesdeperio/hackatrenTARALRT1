/**
 * @author github.com/jamesdeperio
 * @version codepocket template builder v1.0
 */
package nyxdev.hackatren.taralrt1.integration.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import nyxdev.hackatren.taralrt1.global.scope.Gson
import nyxdev.hackatren.taralrt1.global.scope.Xml
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



class NetworkService(
        context: Context
) : RetrofitManager(context) {
    val rssRepository = create(RSSRepository::class.java)  as RSSRepository
    val etaRepository = create(GoogleETARepository::class.java)  as GoogleETARepository
    val restRepository = create(RestRepository::class.java)  as RestRepository
    override fun initConverterFactory(): Converter.Factory {
        val tikXml= TikXml.Builder().exceptionOnUnreadXml(false).build()
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        return MultipleConverterFactory.Builder()
                .add(Xml::class.java, TikXmlConverterFactory.create(tikXml))
                .add(Gson::class.java, GsonConverterFactory.create(gsonBuilder.create()))
                .build()
    }

    override fun initConnectTimeOut(): Long = 600000
    override fun initReadTimeOut(): Long = 60000
    override fun initWriteTimeOut(): Long =60000

    override fun initRxAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()
    override fun initCacheSize(): Int = 10
    override fun initBaseURL(): String = "http://192.168.43.91:85/NyxSoft_TARALRT_Services.asmx/"
    override fun isDebugMode(): Boolean = true //todo
}