/**
 * @author github.com/jamesdeperio
 * @version codepocket template builder v1.0
 */
package nyxdev.hackatren.taralrt1.global.module.appconfig

import android.app.Application
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Binds
import dagger.Module
import dagger.Provides
import nyxdev.hackatren.taralrt1.global.module.nfc.NfcManager
import nyxdev.hackatren.taralrt1.global.module.nfc.NfcManagerImpl
import nyxdev.hackatren.taralrt1.global.scope.ApplicationScope
import nyxdev.hackatren.taralrt1.integration.bus.CredentialBus
import nyxdev.hackatren.taralrt1.integration.bus.EventBus
import nyxdev.hackatren.taralrt1.integration.bus.MenuBus
import nyxdev.hackatren.taralrt1.integration.dao.SqliteHandler
import nyxdev.hackatren.taralrt1.integration.dao.query.*
import nyxdev.hackatren.taralrt1.integration.dao.table.DaoMaster
import nyxdev.hackatren.taralrt1.integration.dao.table.DaoSession
import nyxdev.hackatren.taralrt1.integration.network.NetworkService
import org.greenrobot.greendao.database.Database

@Module
abstract class ApplicationBindingModule {
    @Binds
    internal abstract fun bindContext(application: Application): Context

    @Module
    companion object Provider {
        @ApplicationScope
        @Provides
        @JvmStatic
        fun provideEventBus(): EventBus = EventBus()

        @ApplicationScope
        @Provides
        @JvmStatic
        fun provideMenuBus(): MenuBus = MenuBus()

        @ApplicationScope
        @Provides
        @JvmStatic
        fun provideCredentialBus(): CredentialBus = CredentialBus()

        @ApplicationScope
        @Provides
        @JvmStatic
        fun provideNFC(): NfcManager.Cycle = NfcManagerImpl()


        @ApplicationScope
        @Provides
        @JvmStatic
        internal fun provideInsertReplaceQuery(daoSession: DaoSession): Query.InsertReplace = InsertReplaceImpl(daoSession = daoSession)

        @ApplicationScope
        @Provides
        @JvmStatic
        internal fun provideSelectQuery(daoSession: DaoSession): Query.Select = SelectImpl(daoSession = daoSession)

        @ApplicationScope
        @Provides
        @JvmStatic
        internal fun provideBoolQuery(daoSession: DaoSession): Query.Bool = BoolImpl(daoSession = daoSession)

        @ApplicationScope
        @Provides
        @JvmStatic
        internal fun provideTruncateQuery(daoSession: DaoSession): Query.Truncate = TruncateImpl(daoSession = daoSession)

        @ApplicationScope
        @Provides
        @JvmStatic
        internal fun provideSqliteHandler(context: Context): SqliteHandler
                = SqliteHandler(context)

        @ApplicationScope
        @Provides
        @JvmStatic
        internal fun provideDatabase(sqliteHandler: SqliteHandler): Database
                = sqliteHandler.writableDb

        @ApplicationScope
        @Provides
        @JvmStatic
        internal fun providesDaoSession(database: Database): DaoSession
                = DaoMaster(database).newSession()

        @ApplicationScope
        @Provides
        @JvmStatic
        internal fun provideNetworkService(context: Context): NetworkService = NetworkService(context = context)

        @ApplicationScope
        @Provides
        @JvmStatic
        internal fun provideGoogleSignInOptions(): GoogleSignInOptions
                =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
    }
}
