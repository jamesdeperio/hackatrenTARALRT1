package nyxdev.hackatren.taralrt1.integration.dao.query

import nyxdev.hackatren.taralrt1.integration.dao.table.AccountEntity
import nyxdev.hackatren.taralrt1.integration.dao.table.DaoSession

class InsertReplaceImpl(private val daoSession: DaoSession) : Query.InsertReplace {
    override fun saveAccount(accountEntity: AccountEntity, fldAccountCode: String, fldEmailAddress: String, fldName: String) {
        val account=AccountEntity()
        account.password=accountEntity.password
        account.nfc=accountEntity.nfc
        account.accountID=fldAccountCode
        account.email=fldEmailAddress
        daoSession.accountEntityDao.insertOrReplace(account)
    }
}
