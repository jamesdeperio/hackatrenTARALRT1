/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.loader

import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import nyxdev.hackatren.taralrt1.global.base.DIBaseFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoaderController : DIBaseFragment() {
    @field:[Inject] internal lateinit var presenter: HasLoaderContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasLoaderContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable

    override fun initialization(savedInstanceState: Bundle?) {}

    override fun onLoadEvent(savedInstanceState: Bundle?) {
        subscription.addAll(Observable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.newThread())
                .doOnNext {
                    if (presenter.isNoAccountData())
                        viewMethod.gotoMenuFragment()
                    else viewMethod.gotoHomeFragment()
                }
                .subscribe())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscription.dispose()
    }

}