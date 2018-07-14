/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.main

import kotlinx.coroutines.experimental.Job

interface HasMainContract {
    interface Event

    interface ViewMethod {
        fun gotoLoaderFragment():Job
    }

    interface Presenter {
    }

}