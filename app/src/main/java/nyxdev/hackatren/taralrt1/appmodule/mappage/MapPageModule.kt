/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.mappage

import android.annotation.SuppressLint
import dagger.Binds
import dagger.Module
import dagger.Provides
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import android.support.v4.app.Fragment

@Module
object MapPageModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: MapPageController): MapPageView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_mappage, controller.container, false)
        return MapPageView(controller.rootView!!, controller as HasMapPageContract.Event)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: MapPageController, view: MapPageView): HasMapPageContract.ViewMethod = MapPageViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasMapPageContract.ViewMethod): HasMapPageContract.Presenter = MapPageImpl(viewMethod)

}