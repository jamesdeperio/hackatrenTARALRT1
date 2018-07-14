/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.login

import android.support.v4.view.ViewPager
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.coroutines.experimental.Job
import nyxdev.hackatren.taralrt1.integration.dao.table.AccountEntity

interface HasLoginContract {
    interface Event : ViewPager.OnPageChangeListener {
        fun onBackPressedEvent()
        fun onPrevEvent()
        fun onNextEvent()
        override fun onPageScrollStateChanged(state: Int) {}
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        fun onCredentialReceivedEvent(): Consumer<in Any>
    }
    interface ViewMethod {
        fun moveViewPagerBackward(): Job
        fun buildViewPager(): Job
        fun getNextButtonText(): String
        fun moveViewPagerForward():Job
        fun changeNextToCreate(shouldChange: Boolean):Job
        fun hidePrevButton():Job
        fun showPrevButton():Job
        fun dismissLoadingDialog():Job
        fun showLoadingDialog():Job
        fun showErrorDialog(error:String):Job
        fun gotoHomeFragment():Job
    }

    interface Presenter {
        fun loginAccount(accountEntity: AccountEntity):Disposable
    }
}