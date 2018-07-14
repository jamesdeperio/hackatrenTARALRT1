/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.claimreward

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ClaimRewardImpl(
       private val adapter: ClaimRewardAdapter
) : HasClaimRewardContract.Presenter {
    override fun loadLoadRewards(): Disposable
    =Observable.just(
            "Convert to beep",
            "Redeem 1 free ride subscription\n\n100pts",
            "Redeem 2 free rides subscription\n\n180pts",
            "Redeem 4 free rides subscription\n\n350pts",
            "Redeem 1 day free ride anywhere subscription\n\n1000pts"
    )
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.newThread())
            .doOnNext {
                adapter.rewardList.add(it)
                adapter.notifyLastInserted()
            }
            .subscribe()
}