/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.registration

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
import nyxdev.hackatren.taralrt1.appmodule.qrpage.QRPageController
import nyxdev.hackatren.taralrt1.global.util.Constant

class RegistrationViewImpl(
        private val fragment: Fragment,
        private val view: RegistrationView
) : HasRegistrationContract.ViewMethod {
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

    override fun showSuccessDialog(): Job = launch(UI) {
        view.successDialog.show()
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
        if (shouldChange) view.btnNext.text = Constant.CREATE
        else view.btnNext.text=Constant.NEXT
    }

    override fun moveViewPagerForward(): Job = launch(UI) {
        if (view.viewPager.currentItem!=view.viewPager.adapter!!.count-1){
            view.btnPrev.visibility= View.VISIBLE
            view.viewPager.currentItem+=1
        }  else view.btnNext.text = Constant.CREATE
    }

    override fun getNextButtonText(): String = view.btnNext.text.toString()

    override fun buildViewPager(): Job = launch(UI) {
        PageBuilder.build {
            this.setFragmentManager(fragmentManager = fragment.childFragmentManager!!)
            this.setViewPager(viewPager = view.viewPager)
            this.addPage(Constant.EMPTY, CredentialPageController())
            this.addPage(Constant.EMPTY, QRPageController())
            this.addPage(Constant.EMPTY, NFCPageController())
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