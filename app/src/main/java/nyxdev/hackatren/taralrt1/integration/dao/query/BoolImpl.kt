package nyxdev.hackatren.taralrt1.integration.dao.query

import nyxdev.hackatren.taralrt1.integration.dao.table.DaoSession

class BoolImpl(private val daoSession: DaoSession) : Query.Bool {
    override fun isAccountEmpty(): Boolean
        = daoSession.accountEntityDao.queryBuilder().count()==0L
}
