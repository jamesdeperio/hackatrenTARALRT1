/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.loader

import nyxdev.hackatren.taralrt1.integration.dao.query.Query

class LoaderImpl(
        private val viewMethod: HasLoaderContract.ViewMethod,
        private val boolQuery: Query.Bool
) : HasLoaderContract.Presenter {
    override fun isNoAccountData(): Boolean = boolQuery.isAccountEmpty()
}