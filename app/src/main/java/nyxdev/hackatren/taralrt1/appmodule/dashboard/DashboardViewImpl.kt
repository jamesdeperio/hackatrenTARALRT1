/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.dashboard

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class DashboardViewImpl(
        private val fragment: Fragment,
        private val view: DashboardView
) : HasDashboardContract.ViewMethod {
    @SuppressLint("SetTextI18n")
    override fun updatePoints(points: String) :Job = launch(UI){
        view.tvPoints.text="LRT-1 POINTS: $points pts"
    }
}