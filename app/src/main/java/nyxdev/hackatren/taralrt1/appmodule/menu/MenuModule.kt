/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.menu

import android.support.v4.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope

@Module
object MenuModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideGoogleSignInClient(controller: MenuController, googleSignInOptions: GoogleSignInOptions): GoogleSignInClient
            = GoogleSignIn.getClient(controller.activity!!, googleSignInOptions)

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: MenuController): MenuView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_menu, controller.container, false)
        return MenuView(
                view = controller.rootView!!,
                event = controller as HasMenuContract.Event,
                context= controller.context!!)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: MenuController, view: MenuView): HasMenuContract.ViewMethod = MenuViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasMenuContract.ViewMethod): HasMenuContract.Presenter = MenuImpl(viewMethod)

}