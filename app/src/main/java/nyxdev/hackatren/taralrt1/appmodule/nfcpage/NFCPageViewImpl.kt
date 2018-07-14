/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.nfcpage

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class NFCPageViewImpl(
        private val fragment: Fragment,
        private val view: NFCPageView
) : HasNFCPageContract.ViewMethod {
    @SuppressLint("SetTextI18n")
    override fun updateStatus(status: String): Job = launch(UI){
        view.tvStatus.text="STATUS: $status"
    }
}