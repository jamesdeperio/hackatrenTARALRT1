/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.credentialpage

import android.annotation.SuppressLint
import dagger.Binds
import dagger.Module
import dagger.Provides
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import android.support.v4.app.Fragment

@Module
object CredentialPageModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: CredentialPageController): CredentialPageView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_credentialpage, controller.container, false)
        return CredentialPageView(controller.rootView!!, controller as HasCredentialPageContract.Event)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: CredentialPageController, view: CredentialPageView): HasCredentialPageContract.ViewMethod = CredentialPageViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasCredentialPageContract.ViewMethod): HasCredentialPageContract.Presenter = CredentialPageImpl(viewMethod)

}