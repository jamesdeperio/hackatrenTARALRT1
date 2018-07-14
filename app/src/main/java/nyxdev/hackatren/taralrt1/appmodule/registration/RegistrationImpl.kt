/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.registration

import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nyxdev.hackatren.taralrt1.global.util.Constant
import nyxdev.hackatren.taralrt1.integration.dao.table.AccountEntity
import nyxdev.hackatren.taralrt1.integration.network.RestRepository
import java.util.*

class RegistrationImpl(
        private val viewMethod: HasRegistrationContract.ViewMethod,
        private val restRepository: RestRepository
) : HasRegistrationContract.Presenter {
    override fun createAccount(accountEntity: AccountEntity): Disposable
            = restRepository.createAccountRequest(data = "```${accountEntity.name}`${accountEntity.email}````${accountEntity.nfc}`${accountEntity.rfid}`${accountEntity.name}`${Constant.DATE_FORMAT.format(Date())}")
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .map { it.string() }
            .doOnError {
                it.printStackTrace()
            viewMethod.dismissLoadingDialog()
                viewMethod.showErrorDialog("Could not create account. Try again!")
            }
            .doOnNext {
                viewMethod.dismissLoadingDialog()
                if (it!!.contains("false"))
                    viewMethod.showErrorDialog("Email or Card is already used!")
                else viewMethod.showSuccessDialog()
            }.subscribe()

}