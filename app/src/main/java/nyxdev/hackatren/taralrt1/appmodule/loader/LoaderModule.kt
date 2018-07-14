/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.loader

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import nyxdev.hackatren.taralrt1.integration.dao.query.Query

@Module
object LoaderModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: LoaderController): LoaderView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_loader, controller.container, false)
        return LoaderView(view = controller.rootView!!)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: LoaderController, view: LoaderView): HasLoaderContract.ViewMethod
            = LoaderViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasLoaderContract.ViewMethod,bool: Query.Bool): HasLoaderContract.Presenter
            = LoaderImpl(
            viewMethod = viewMethod,
            boolQuery=bool)

}