/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.dashboard

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import nyxdev.hackatren.taralrt1.integration.dao.query.Query
import nyxdev.hackatren.taralrt1.integration.network.NetworkService

@Module
object DashboardModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()


    @FragmentScope
    @Provides
    @JvmStatic
    fun provideAdapter(controller: DashboardController): DashboardAdapter {
        val adapter =DashboardAdapter()
        val  viewholderWithImage=DashboardViewholder(context = controller.context!!, adapter = adapter)
        viewholderWithImage.setContentView(R.layout.item_list_announcement)
         adapter.addViewHolder(viewholderWithImage)
        return adapter
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: DashboardController,adapter: DashboardAdapter): DashboardView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_dashboard, controller.container, false)
        return DashboardView(
                view = controller.rootView!!,
                context = controller.context!!,
                adapter=adapter)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: DashboardController, view: DashboardView): HasDashboardContract.ViewMethod
            = DashboardViewImpl(fragment = controller as Fragment, view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(select: Query.Select,viewMethod: HasDashboardContract.ViewMethod,adapter: DashboardAdapter, networkManager: NetworkService): HasDashboardContract.Presenter
            = DashboardImpl(
            viewMethod = viewMethod,
            adapter=adapter,
            rssRepository= networkManager.rssRepository,
            restReposity=networkManager.restRepository,
            selectQuery=select)

}