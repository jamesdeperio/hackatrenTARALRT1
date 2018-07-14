/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.distancematrix

import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nyxdev.hackatren.taralrt1.integration.network.GoogleETARepository

class DistanceMatrixImpl(
        private val viewMethod: HasDistanceMatrixContract.ViewMethod,
        private val googleETARepository: GoogleETARepository
) : HasDistanceMatrixContract.Presenter {
    override fun loadETA(currentStation: String, destinationStation: String): Disposable
    = googleETARepository.etaRequest("https://maps.googleapis.com/maps/api/distancematrix/json?origins=$currentStation&destinations=$destinationStation&departure_time=now&mode=transit&key=AIzaSyCeQBxnGWk8p7KhP95N96tMelzp3Df5sFY")
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .map { it.rows[0].elements[0] }
            .doOnNext {
                viewMethod.showLoadingDialog()
                if (it.fare!=null)
                viewMethod.loadETA(it)
            }
            .doOnComplete { viewMethod.dismissLoadingDialog() }
            .subscribe()
}