/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.credentialpage

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.appmodule.menu.MenuView

class CredentialPageView(
        view: View,
        event: HasCredentialPageContract.Event) {
    val tvEmail= view.findViewById<TextView>(R.id.tvEmail)!!.apply {
        if (MenuView.isOpenSignIn) this.visibility=View.GONE
    }
    val tvName= view.findViewById<TextView>(R.id.tvName)!!.apply {
        if (MenuView.isOpenSignIn) this.visibility=View.GONE
    }
    val tbName= view.findViewById<EditText>(R.id.tbName)!!.apply {
        if (MenuView.isOpenSignIn) this.visibility=View.GONE
        this.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                event.onTypingEmailDoneEvent()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
    }
    val tbEmail= view.findViewById<EditText>(R.id.tbEmail)!!.apply {
        if (MenuView.isOpenSignIn) this.visibility=View.GONE
        this.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                event.onTypingEmailDoneEvent()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
    }
    val tbPassword= view.findViewById<EditText>(R.id.tbPassword)!!.apply {
        this.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                event.onTypingPasswordDoneEvent()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
    }
}