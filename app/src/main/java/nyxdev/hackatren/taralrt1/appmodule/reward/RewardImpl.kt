/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.reward

import nyxdev.hackatren.taralrt1.global.model.obj.reward.Reward
import nyxdev.hackatren.taralrt1.global.util.RewardListType
import nyxdev.hackatren.taralrt1.integration.dao.query.Query
import nyxdev.hackatren.taralrt1.integration.network.RestRepository

class RewardImpl(
        private val viewMethod: HasRewardContract.ViewMethod,
        private val adapter: RewardAdapter,
        private val selectQuery: Query.Select,
        private val restRepository: RestRepository
) : HasRewardContract.Presenter {
    override fun loadDefaultItemList() {
        val option = Reward()
        option.type= RewardListType.OPTION
        option.accountID= "ACCOUNTID: "+selectQuery.getProfile().accountID
        val  rewards=restRepository.rewardRequest(data = selectQuery.getProfile().accountID!!).execute().body()!!.rewards
        var points="0"
        rewards.forEach {
            points= (points.toFloat()+ it.fldRewardRate!!.toFloat()).toString()
        }
        option.points=if (rewards.size==0) "LRT-1 POINTS: 0 pt" else "LRT-1 POINTS: $points pt"
        adapter.rewardList.add(option)
        adapter.notifyLastInserted()

        val title = Reward()
        title.type= RewardListType.TITLE
        adapter.rewardList.add(title)
        adapter.notifyLastInserted()

        rewards.forEach {
            points= (points.toFloat()+ it.fldRewardRate!!.toFloat()).toString()
        }

        for (x in 0 until  2){
            val history = Reward()
            history.type= RewardListType.HISTORY
            adapter.rewardList.add(history)
            adapter.notifyLastInserted()

        }
    }
}