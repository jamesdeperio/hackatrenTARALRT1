/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.registration

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import nyxdev.hackatren.taralrt1.integration.network.NetworkService

@Module
object RegistrationModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: RegistrationController): RegistrationView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_registration, controller.container, false)
        return RegistrationView(
                view = controller.rootView!!,
                event = controller as HasRegistrationContract.Event,
                context= controller.context!!)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: RegistrationController, view: RegistrationView): HasRegistrationContract.ViewMethod = RegistrationViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasRegistrationContract.ViewMethod,networkService: NetworkService): HasRegistrationContract.Presenter = RegistrationImpl(viewMethod,networkService.restRepository)

}