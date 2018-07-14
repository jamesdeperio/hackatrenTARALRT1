/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.distancematrix

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.global.base.DIBaseFragment
import javax.inject.Inject

class DistanceMatrixController : DIBaseFragment(), HasDistanceMatrixContract.Event {
    @field:[Inject] internal lateinit var presenter: HasDistanceMatrixContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasDistanceMatrixContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable

    override fun initialization(savedInstanceState: Bundle?) {

    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscription.dispose()
    }

    override fun onCheckETAEvent(currentStation: String, destinationStation: String) {
        if (currentStation!=destinationStation)
        subscription.add(presenter.loadETA(currentStation,destinationStation))
        else viewMethod.showErrorDialog("Destination must not be your current station.")
      }
}