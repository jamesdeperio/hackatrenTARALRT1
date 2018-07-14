/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.credentialpage

import android.support.v4.app.Fragment

class CredentialPageViewImpl(
        private val fragment: Fragment,
        private val view: CredentialPageView
) : HasCredentialPageContract.ViewMethod {
    override fun getPassword(): String = view.tbPassword.text.toString()

    override fun getEmail(): String =view.tbEmail.text.toString()

    override fun getName():String = view.tbName.text.toString()
}