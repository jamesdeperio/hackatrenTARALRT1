package nyxdev.hackatren.taralrt1.integration.dao.query

import nyxdev.hackatren.taralrt1.integration.dao.table.AccountEntity
import nyxdev.hackatren.taralrt1.integration.dao.table.DaoSession

class SelectImpl(private var daoSession: DaoSession) : Query.Select {
    override fun getProfile(): AccountEntity {
        daoSession.accountEntityDao.detachAll()
        return daoSession.accountEntityDao.queryBuilder().unique()
    }
}
