package nyxdev.hackatren.taralrt1.integration.dao.query

import nyxdev.hackatren.taralrt1.integration.dao.table.AccountEntity

interface Query {
    interface Select {
        fun getProfile(): AccountEntity
    }

    interface Bool {
        fun isAccountEmpty(): Boolean
    }

    interface Truncate
    interface InsertReplace {
        fun saveAccount(accountEntity: AccountEntity, fldAccountCode: String, fldEmailAddress: String, fldName: String)
    }
}