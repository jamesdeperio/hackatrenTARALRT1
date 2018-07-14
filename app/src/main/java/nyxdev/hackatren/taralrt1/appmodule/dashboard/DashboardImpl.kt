/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.dashboard

import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nyxdev.hackatren.taralrt1.global.model.obj.rss.Channel
import nyxdev.hackatren.taralrt1.global.model.obj.rss.RSS
import nyxdev.hackatren.taralrt1.global.util.Constant
import nyxdev.hackatren.taralrt1.integration.dao.query.Query
import nyxdev.hackatren.taralrt1.integration.network.RSSRepository
import nyxdev.hackatren.taralrt1.integration.network.RestRepository
import java.util.regex.Pattern


class DashboardImpl(
        private val viewMethod: HasDashboardContract.ViewMethod,
        private val adapter: DashboardAdapter,
        private val rssRepository: RSSRepository,
        private val restReposity: RestRepository,
        private val selectQuery: Query.Select
) : HasDashboardContract.Presenter {
    override fun loadPoints(): Disposable = restReposity.rewardsRequest(data = selectQuery.getProfile().accountID)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .map { it.rewards }
            .doOnNext {
                var points="0"
                it.forEach {
                    points = (points.toFloat()+ it.fldRewardRate!!.toFloat()).toString()
                }
                viewMethod.updatePoints(if (it.size==0)"0" else points)

            }
            .subscribe()

    override fun loadAnnouncement(): Disposable = rssRepository.announcementRequest().doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .map(RSS::channel)
            .map(Channel::itemList)
            .flatMapIterable { it-> it }
            .doOnError { it.printStackTrace() }
            .doOnNext {
                val pList= it!!.content!!.split("</p>")
                var newContent=""
                pList.withIndex()
                        .filter { it.index!=0 }
                        .forEach {
                            newContent+= it.value
                                    .replace("<.*?>".toRegex(), "")
                                    .replace("&#.*?;".toRegex(), "") + Constant.NEWLINE
                        }
                val p = Pattern.compile(Pattern.quote("src=\"") + "(.*?)" + Pattern.quote("\""))
                val m = p.matcher(it.content!!)
                while (m.find()){
                    it.image=m.group(1)
                }
                it.content=newContent
                adapter.announcementList.add(it)
                adapter.notifyLastInserted()
            }.subscribe ()
}