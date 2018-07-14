/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.nfcpage

import android.view.View
import android.widget.TextView
import nyxdev.hackatren.taralrt1.R

class NFCPageView(
        view: View,
        event: HasNFCPageContract.Event) {
    val tvStatus= view.findViewById<TextView>(R.id.tvStatus)!!
}