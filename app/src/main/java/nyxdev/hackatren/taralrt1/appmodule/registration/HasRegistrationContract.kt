/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.registration

import android.support.v4.view.ViewPager
import cn.pedant.SweetAlert.SweetAlertDialog
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.coroutines.experimental.Job
import nyxdev.hackatren.taralrt1.integration.dao.table.AccountEntity

interface HasRegistrationContract {
    interface Event : ViewPager.OnPageChangeListener {
        fun onBackPressedEvent()
        fun onPrevEvent()
        fun onNextEvent()
        override fun onPageScrollStateChanged(state: Int) {}
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        fun onCredentialReceivedEvent(): Consumer<in Any>
        fun onConfirmSuccessCreatingEvent(dialog: SweetAlertDialog)
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
        fun showSuccessDialog():Job
        fun gotoHomeFragment():Job
    }

    interface Presenter {
        fun createAccount(accountEntity: AccountEntity): Disposable
    }

}