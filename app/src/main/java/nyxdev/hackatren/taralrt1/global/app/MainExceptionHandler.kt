/**
 * @author github.com/jamesdeperio
 * @version codepocket template builder v1.0
 */
package nyxdev.hackatren.taralrt1.global.app

import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*

class MainExceptionHandler : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread?, e: Throwable?) {
        e!!.printStackTrace()
    }
}
