/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.loader

import kotlinx.coroutines.experimental.Job

interface HasLoaderContract {

    interface ViewMethod {
        fun gotoMenuFragment():Job
        fun gotoHomeFragment():Job
    }

    interface Presenter {
        fun isNoAccountData(): Boolean
    }

}