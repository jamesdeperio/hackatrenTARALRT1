/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.login

import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nyxdev.hackatren.taralrt1.integration.dao.query.Query
import nyxdev.hackatren.taralrt1.integration.dao.table.AccountEntity
import nyxdev.hackatren.taralrt1.integration.network.RestRepository

class LoginImpl(
        private val viewMethod: HasLoginContract.ViewMethod,
        private val restRepository: RestRepository,
        private val insertReplace: Query.InsertReplace
) : HasLoginContract.Presenter {
    override fun loginAccount(accountEntity: AccountEntity):Disposable
    = restRepository.accountRequest(data = "${accountEntity.nfc}`${accountEntity.password}")
            .doOnError {
                viewMethod.showErrorDialog("No Internet Connection!")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .doOnNext {
                try {
                    if (it.accounts.size==0){
                        viewMethod.dismissLoadingDialog()
                        viewMethod.showErrorDialog("Wrong Credentials!")
                    }else{
                        insertReplace.saveAccount(accountEntity,it.accounts[0].fldAccountCode!!,it.accounts[0].fldEmailAddress!!,it.accounts[0].fldName!!)
                        viewMethod.dismissLoadingDialog()
                        viewMethod.gotoHomeFragment()
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                    viewMethod.dismissLoadingDialog()
                    viewMethod.showErrorDialog("Wrong Credentials!")
                }
            }.subscribe()
}