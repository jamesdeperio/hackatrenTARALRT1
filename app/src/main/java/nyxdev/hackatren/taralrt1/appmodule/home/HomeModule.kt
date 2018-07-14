/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.home

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope

@Module
object HomeModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: HomeController): HomeView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_home, controller.container, false)
        return HomeView(
                view = controller.rootView!!,
                event = controller as HasHomeContract.Event,
                context= controller.context!!)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: HomeController, view: HomeView): HasHomeContract.ViewMethod = HomeViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasHomeContract.ViewMethod): HasHomeContract.Presenter = HomeImpl(viewMethod)

}