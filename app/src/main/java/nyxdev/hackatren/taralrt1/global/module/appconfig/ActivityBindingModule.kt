/**
 * @author github.com/jamesdeperio
 * @version codepocket template builder v1.0
 */
package nyxdev.hackatren.taralrt1.global.module.appconfig

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import nyxdev.hackatren.taralrt1.appmodule.main.MainController
import nyxdev.hackatren.taralrt1.appmodule.main.MainModule
import nyxdev.hackatren.taralrt1.global.scope.ActivityScope

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun mainInjector(): MainController

}