/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.reward

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope
import nyxdev.hackatren.taralrt1.integration.dao.query.Query
import nyxdev.hackatren.taralrt1.integration.network.NetworkService

@Module
object RewardModule {
    @FragmentScope
    @Provides
    @JvmStatic
    fun provideSubscription() = CompositeDisposable()

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideAdapter(controller: RewardController): RewardAdapter {
        val adapter = RewardAdapter()
        val  viewHolderOption= RewardViewholderOption(context = controller.context!!, adapter = adapter,event=controller as HasRewardContract.Event)
        val  viewHolderTitle= RewardViewholderTitle(context = controller.context!!, adapter = adapter)
        val  viewHolderHistory= RewardViewholderHistory(context = controller.context!!, adapter = adapter)
        viewHolderOption.setContentView(R.layout.item_list_reward_option)
        viewHolderTitle.setContentView(R.layout.item_list_reward_title)
        viewHolderHistory.setContentView(R.layout.item_list_reward_history)
        adapter.addViewHolder(viewHolderOption)
        adapter.addViewHolder(viewHolderTitle)
        adapter.addViewHolder(viewHolderHistory)
        return adapter
    }


    @FragmentScope
    @Provides
    @JvmStatic
    fun provideComponent(controller: RewardController,adapter: RewardAdapter): RewardView {
        controller.rootView = controller.layoutInflater.inflate(R.layout.fragment_reward, controller.container, false)
        return RewardView(
                view = controller.rootView!!,
                adapter= adapter)
    }

    @FragmentScope
    @Provides
    @JvmStatic
    fun provideViewMethod(controller: RewardController, view: RewardView): HasRewardContract.ViewMethod
            = RewardViewImpl(
            fragment = controller as Fragment,
            view = view)

    @FragmentScope
    @Provides
    @JvmStatic
    fun providePresenter(networkService: NetworkService,viewMethod: HasRewardContract.ViewMethod,adapter: RewardAdapter,selectQuery:Query.Select): HasRewardContract.Presenter
            = RewardImpl(
            viewMethod = viewMethod,
            adapter= adapter,
            selectQuery=selectQuery,
            restRepository=networkService.restRepository)

}