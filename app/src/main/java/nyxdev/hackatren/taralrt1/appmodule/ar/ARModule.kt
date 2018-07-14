/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.ar

import android.support.v4.app.Fragment
import android.view.WindowManager
import cn.easyar.Engine
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import nyxdev.hackatren.taralrt1.integration.dao.query.Query
import nyxdev.hackatren.taralrt1.integration.network.NetworkService

@Module
object ARModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: ARController,networkService: NetworkService,select: Query.Select): ARView {
        controller.activity!!.window.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_ar, controller.container, false)
        Engine.initialize(controller.activity!!, "OnLer1ucA2Dr4nEpfMgWUf7yp68a3OIzDFN57oKUK11FSIwt3A8XA3URkkNob5EKwv0ngJM5vKB1JrPUQEg7xeN0q0eyvvsPho6er9kp7yNQRabyryCFN52XN3QrshNlNJrgjhXr35k9JIYqTCA2JnolYFxzzI6UqapHh7SuXX8tPLweUhvEGSZ24QR3WWbuW42e67ZW")
        return ARView(
                view = controller.rootView!!,
                event = controller as HasARContract.Event,
                context= controller.context!!,
                restRespository=networkService.restRepository,
                selectQuery =select)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: ARController, view: ARView): HasARContract.ViewMethod
            = ARViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(viewMethod: HasARContract.ViewMethod): HasARContract.Presenter = ARImpl(viewMethod)

}