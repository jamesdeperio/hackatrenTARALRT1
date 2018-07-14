/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.login

import android.support.v4.app.Fragment
import android.view.View
import jdp.pocketlib.builder.PageBuilder
import jdp.pocketlib.util.Navigate
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.appmodule.credentialpage.CredentialPageController
import nyxdev.hackatren.taralrt1.appmodule.home.HomeController
import nyxdev.hackatren.taralrt1.appmodule.nfcpage.NFCPageController
import nyxdev.hackatren.taralrt1.global.util.Constant

class LoginViewImpl(
        private val fragment: Fragment,
        private val view: LoginView
) : HasLoginContract.ViewMethod {
    override fun dismissLoadingDialog(): Job= launch(UI) {
        view.loadingDialog.dismiss()
    }

    override fun showLoadingDialog(): Job = launch(UI) {
        view.loadingDialog.show()
    }

    override fun showErrorDialog(error: String): Job= launch(UI) {
        view.errorDialog.contentText=error
        view.errorDialog.show()
    }

    override fun gotoHomeFragment(): Job = launch(UI) {
        Navigate.using(fragmentManager = fragment.fragmentManager!!)
                .change(layoutID = R.id.main_container)
                .from(currentFragment = fragment)
                .to(fragmentToChange = HomeController())
                .withBackStack(isBackstackEnabled = false)
                .commitAllowingStateLoss()
    }

    override fun showPrevButton(): Job  = launch(UI) {
        view.btnPrev.visibility=View.VISIBLE
    }

    override fun hidePrevButton(): Job = launch(UI) {
        view.btnPrev.visibility=View.GONE
    }

    override fun changeNextToCreate(shouldChange: Boolean): Job = launch(UI) {
        if (shouldChange) view.btnNext.text = Constant.SIGN_IN
        else view.btnNext.text=Constant.NEXT
    }

    override fun moveViewPagerForward(): Job = launch(UI) {
        if (view.viewPager.currentItem!=view.viewPager.adapter!!.count-1){
            view.btnPrev.visibility= View.VISIBLE
            view.viewPager.currentItem+=1
        }  else view.btnNext.text = Constant.SIGN_IN
    }

    override fun getNextButtonText(): String = view.btnNext.text.toString()

    override fun buildViewPager(): Job = launch(UI) {
        PageBuilder.build {
            this.setFragmentManager(fragmentManager = fragment.childFragmentManager!!)
            this.setViewPager(viewPager = view.viewPager)
            this.addPage(Constant.EMPTY, NFCPageController())
            this.addPage(Constant.EMPTY, CredentialPageController())
        }
    }

    override fun moveViewPagerBackward(): Job = launch(UI) {
        if (view.viewPager.currentItem==1){
            view.btnNext.text = Constant.NEXT
            view.viewPager.currentItem=0
            view.btnPrev.visibility= View.GONE
        } else view.viewPager.currentItem-=1
    }
}