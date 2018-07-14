/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.main

import android.annotation.SuppressLint
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.scope.ActivityScope

@Module
object MainModule {
    @ActivityScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @SuppressLint("InflateParams")
    @ActivityScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: MainController): MainView {
        val view = controller.layoutInflater.inflate(R.layout.activity_main, null)
        controller.setContentView(view)
        return MainView(view = view, event = controller as HasMainContract.Event)
    }

    @ActivityScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: MainController, view: MainView): HasMainContract.ViewMethod
            = MainViewImpl(context = controller, view = view,fragmentManager=controller.supportFragmentManager!!)


    @ActivityScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasMainContract.ViewMethod): HasMainContract.Presenter = MainImpl(viewMethod)

}