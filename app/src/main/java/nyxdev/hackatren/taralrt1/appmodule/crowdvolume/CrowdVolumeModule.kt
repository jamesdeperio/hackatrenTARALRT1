/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.crowdvolume

import android.annotation.SuppressLint
import dagger.Binds
import dagger.Module
import dagger.Provides
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import android.support.v4.app.Fragment

@Module
object CrowdVolumeModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: CrowdVolumeController): CrowdVolumeView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_crowdvolume, controller.container, false)
        return CrowdVolumeView(controller.rootView!!, controller as HasCrowdVolumeContract.Event)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: CrowdVolumeController, view: CrowdVolumeView): HasCrowdVolumeContract.ViewMethod = CrowdVolumeViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasCrowdVolumeContract.ViewMethod): HasCrowdVolumeContract.Presenter = CrowdVolumeImpl(viewMethod)

}