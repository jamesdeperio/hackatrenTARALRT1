/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.nfcpage

import kotlinx.coroutines.experimental.Job
import nyxdev.hackatren.taralrt1.global.module.nfc.NfcManager

interface HasNFCPageContract {
    interface Event : NfcManager.CallBack

    interface ViewMethod {
        fun updateStatus(status: String):Job
    }

    interface Presenter

}