/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.qrpage

import android.annotation.SuppressLint
import dagger.Binds
import dagger.Module
import dagger.Provides
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import android.support.v4.app.Fragment

@Module
object QRPageModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: QRPageController): QRPageView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_qrpage, controller.container, false)
        return QRPageView(controller.rootView!!, controller as HasQRPageContract.Event)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: QRPageController, view: QRPageView): HasQRPageContract.ViewMethod = QRPageViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasQRPageContract.ViewMethod): HasQRPageContract.Presenter = QRPageImpl(viewMethod)

}