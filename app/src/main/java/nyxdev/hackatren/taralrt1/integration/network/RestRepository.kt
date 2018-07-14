package nyxdev.hackatren.taralrt1.integration.network

import io.reactivex.Observable
import nyxdev.hackatren.taralrt1.global.model.obj.account.Accounts
import nyxdev.hackatren.taralrt1.global.model.obj.account.Rewards
import nyxdev.hackatren.taralrt1.global.model.obj.station.Stations
import nyxdev.hackatren.taralrt1.global.scope.Gson
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RestRepository {
    @FormUrlEncoded
    @POST("CommuterAccounts_SaveRecord")
    fun createAccountRequest(
            @Field("_IsEdit") editMode: String="false",
            @Field("_strInputs") data: String,
            @Field("_strLicenseKey") license: String="NYXSOFT2018"
            ): Observable<ResponseBody>

    @FormUrlEncoded
    @POST("LoadCommuterAccounts_Where_JSON")
    @Gson
    fun accountRequest(
            @Field("_strInputWhereStatement") data:String,
            @Field("_strLicenseKey") license: String="NYXSOFT2018"
            ): Observable<Accounts>
    @FormUrlEncoded
    @POST("LoadRewards_Where_JSON")
    @Gson
    fun rewardRequest(
            @Field("_strInputWhereStatement") data:String,
            @Field("_strLicenseKey") license: String="NYXSOFT2018"
    ): retrofit2.Call<Rewards>

    @FormUrlEncoded
    @POST("LoadRewards_Where_JSON")
    @Gson
    fun rewardsRequest(
            @Field("_strInputWhereStatement") data:String,
            @Field("_strLicenseKey") license: String="NYXSOFT2018"
    ): Observable<Rewards>

    @FormUrlEncoded
    @POST("LoadStationCrownVolume_Where_JSON")
    @Gson
    fun stationsRequest(
            @Field("_strInputWhereStatement") data:String="",
            @Field("_strLicenseKey") license: String="NYXSOFT2018"
    ): Observable<Stations>

    @FormUrlEncoded
    @POST("Rewards_SaveRecord")
    @Gson
    fun savePointsRequest (
            @Field("_strInputs") data:String="",
            @Field("_strLicenseKey") license: String="NYXSOFT2018"
    ): Observable<ResponseBody>
}
