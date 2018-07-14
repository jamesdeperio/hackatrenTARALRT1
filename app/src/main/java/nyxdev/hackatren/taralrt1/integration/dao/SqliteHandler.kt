package nyxdev.hackatren.taralrt1.integration.dao

import android.content.Context
import android.util.Log
import nyxdev.hackatren.taralrt1.integration.dao.table.DaoMaster
import org.greenrobot.greendao.database.Database

/**
* Created by jamesdeperio on 11/29/2017.
**/

class SqliteHandler(context: Context) : DaoMaster.DevOpenHelper(context, "hackatren_db", null) {
    override fun onUpgrade(db: Database?, oldVersion: Int, newVersion: Int) {
        Log.e("Version", "old:$oldVersion  new:$newVersion")
    }
}