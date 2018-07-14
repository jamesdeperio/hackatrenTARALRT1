/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.monitoring

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import nyxdev.hackatren.taralrt1.integration.network.NetworkService

@Module
object MonitoringModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()


    @FragmentScope
    @Provides
    @JvmStatic
    fun provideAdapter(controller: MonitoringController): MonitoringAdapter {
        val adapter = MonitoringAdapter()
        val  viewHolder= MonitorViewholder(context = controller.context!!, adapter = adapter)
        viewHolder.setContentView(R.layout.item_list_station)
        adapter.addViewHolder(viewHolder)
        return adapter
    }


    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: MonitoringController,adapter: MonitoringAdapter): MonitoringView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_monitoring, controller.container, false)
        return MonitoringView(
                view = controller.rootView!!,
                event = controller as HasMonitoringContract.Event,
                adapter =adapter)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: MonitoringController, view: MonitoringView): HasMonitoringContract.ViewMethod
            = MonitoringViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(networkService: NetworkService,viewMethod: HasMonitoringContract.ViewMethod,adapter: MonitoringAdapter): HasMonitoringContract.Presenter
            = MonitoringImpl(
            viewMethod = viewMethod,
            adapter= adapter,
            restRepository= networkService.restRepository)

}