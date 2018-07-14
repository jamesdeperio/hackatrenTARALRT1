/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.nfcpage

import android.annotation.SuppressLint
import dagger.Binds
import dagger.Module
import dagger.Provides
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import android.support.v4.app.Fragment

@Module
object NFCPageModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: NFCPageController): NFCPageView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_nfcpage, controller.container, false)
        return NFCPageView(controller.rootView!!, controller as HasNFCPageContract.Event)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: NFCPageController, view: NFCPageView): HasNFCPageContract.ViewMethod = NFCPageViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasNFCPageContract.ViewMethod): HasNFCPageContract.Presenter = NFCPageImpl(viewMethod)

}