/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.distancematrix

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import nyxdev.hackatren.taralrt1.integration.network.NetworkService

@Module
object DistanceMatrixModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: DistanceMatrixController): DistanceMatrixView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_distancematrix, controller.container, false)
        return DistanceMatrixView(
                view = controller.rootView!!,
                event = controller as HasDistanceMatrixContract.Event,
                context = controller.context!!)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: DistanceMatrixController, view: DistanceMatrixView): HasDistanceMatrixContract.ViewMethod = DistanceMatrixViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasDistanceMatrixContract.ViewMethod,networkService: NetworkService): HasDistanceMatrixContract.Presenter
            = DistanceMatrixImpl(
            viewMethod = viewMethod,
            googleETARepository=networkService.etaRepository)

}