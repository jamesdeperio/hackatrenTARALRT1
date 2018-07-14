/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.monitoring

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nyxdev.hackatren.taralrt1.integration.network.RestRepository
import java.util.concurrent.TimeUnit

class MonitoringImpl(
        private val viewMethod: HasMonitoringContract.ViewMethod,
        private val adapter: MonitoringAdapter,
        private val restRepository: RestRepository
) : HasMonitoringContract.Presenter {

    override fun loadStations(): Disposable
        = restRepository.stationsRequest()
            .doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .map { it.stations }
            .flatMapIterable { it-> it }
            .doOnNext {
                adapter.stationList.add(it)
                adapter.notifyLastInserted()
            }
            .doOnComplete {
                Observable.interval(1000L, TimeUnit.MILLISECONDS)
                        .timeInterval()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.io())
                        .subscribe {_->
                            restRepository.stationsRequest()
                                    .doOnError { it.printStackTrace() }
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(Schedulers.newThread())
                                    .map { it.stations }
                                    .flatMapIterable { it-> it.withIndex() }
                                    .doOnNext {
                                        adapter.stationList[it.index].totalPassenger=it.value.totalPassenger
                                        adapter.stationList[it.index].heavy=it.value.heavy
                                        adapter.stationList[it.index].moderate=it.value.moderate
                                        adapter.stationList[it.index].lite=it.value.lite
                                        adapter.notifyItemIndexAt(it.index)
                                    }
                                    .subscribe()
                        }

            }
            .subscribe()
}