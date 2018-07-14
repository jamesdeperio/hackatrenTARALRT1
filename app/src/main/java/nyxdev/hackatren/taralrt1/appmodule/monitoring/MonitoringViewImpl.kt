/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.monitoring

import android.content.Context
import android.support.v4.app.Fragment

class MonitoringViewImpl(
        private val fragment: Fragment,
        private val view: MonitoringView
) : HasMonitoringContract.ViewMethod