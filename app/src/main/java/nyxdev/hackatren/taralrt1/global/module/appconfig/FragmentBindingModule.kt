/**
 * @author github.com/jamesdeperio
 * @version codepocket template builder v1.0
 */
package nyxdev.hackatren.taralrt1.global.module.appconfig

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import nyxdev.hackatren.taralrt1.appmodule.ar.ARController
import nyxdev.hackatren.taralrt1.appmodule.ar.ARModule
import nyxdev.hackatren.taralrt1.appmodule.credentialpage.CredentialPageController
import nyxdev.hackatren.taralrt1.appmodule.credentialpage.CredentialPageModule
import nyxdev.hackatren.taralrt1.appmodule.crowdvolume.CrowdVolumeController
import nyxdev.hackatren.taralrt1.appmodule.crowdvolume.CrowdVolumeModule
import nyxdev.hackatren.taralrt1.appmodule.dashboard.DashboardController
import nyxdev.hackatren.taralrt1.appmodule.dashboard.DashboardModule
import nyxdev.hackatren.taralrt1.appmodule.distancematrix.DistanceMatrixController
import nyxdev.hackatren.taralrt1.appmodule.distancematrix.DistanceMatrixModule
import nyxdev.hackatren.taralrt1.appmodule.home.HomeController
import nyxdev.hackatren.taralrt1.appmodule.home.HomeModule
import nyxdev.hackatren.taralrt1.appmodule.loader.LoaderController
import nyxdev.hackatren.taralrt1.appmodule.loader.LoaderModule
import nyxdev.hackatren.taralrt1.appmodule.login.LoginController
import nyxdev.hackatren.taralrt1.appmodule.login.LoginModule
import nyxdev.hackatren.taralrt1.appmodule.mappage.MapPageController
import nyxdev.hackatren.taralrt1.appmodule.mappage.MapPageModule
import nyxdev.hackatren.taralrt1.appmodule.menu.MenuController
import nyxdev.hackatren.taralrt1.appmodule.menu.MenuModule
import nyxdev.hackatren.taralrt1.appmodule.monitoring.MonitoringController
import nyxdev.hackatren.taralrt1.appmodule.monitoring.MonitoringModule
import nyxdev.hackatren.taralrt1.appmodule.nfcpage.NFCPageController
import nyxdev.hackatren.taralrt1.appmodule.nfcpage.NFCPageModule
import nyxdev.hackatren.taralrt1.appmodule.qrpage.QRPageController
import nyxdev.hackatren.taralrt1.appmodule.qrpage.QRPageModule
import nyxdev.hackatren.taralrt1.appmodule.registration.RegistrationController
import nyxdev.hackatren.taralrt1.appmodule.registration.RegistrationModule
import nyxdev.hackatren.taralrt1.appmodule.reward.RewardController
import nyxdev.hackatren.taralrt1.appmodule.reward.RewardModule
import nyxdev.hackatren.taralrt1.global.scope.FragmentScope

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class FragmentBindingModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [LoaderModule::class])
    internal abstract fun loaderInjector(): LoaderController

    @FragmentScope
    @ContributesAndroidInjector(modules = [MenuModule::class])
    internal abstract fun menuInjector(): MenuController

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun loginInjector(): LoginController

    @FragmentScope
    @ContributesAndroidInjector(modules = [RegistrationModule::class])
    internal abstract fun registrationInjector(): RegistrationController

    @FragmentScope
    @ContributesAndroidInjector(modules = [QRPageModule::class])
    internal abstract fun qrPageInjector(): QRPageController

    @FragmentScope
    @ContributesAndroidInjector(modules = [NFCPageModule::class])
    internal abstract fun nfcPageInjector(): NFCPageController

    @FragmentScope
    @ContributesAndroidInjector(modules = [CredentialPageModule::class])
    internal abstract fun credentialPageInjector(): CredentialPageController

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun homeInjector(): HomeController

    @FragmentScope
    @ContributesAndroidInjector(modules = [DashboardModule::class])
    internal abstract fun dashboardInjector(): DashboardController

    @FragmentScope
    @ContributesAndroidInjector(modules = [CrowdVolumeModule::class])
    internal abstract fun crowdVolumeInjector(): CrowdVolumeController

    @FragmentScope
    @ContributesAndroidInjector(modules = [MapPageModule::class])
    internal abstract fun mapPageInjector(): MapPageController

    @FragmentScope
    @ContributesAndroidInjector(modules = [MonitoringModule::class])
    internal abstract fun monitoringInjector(): MonitoringController

    @FragmentScope
    @ContributesAndroidInjector(modules = [DistanceMatrixModule::class])
    internal abstract fun distanceMatrixInjector(): DistanceMatrixController

    @FragmentScope
    @ContributesAndroidInjector(modules = [RewardModule::class])
    internal abstract fun rewardInjector(): RewardController

    @FragmentScope
    @ContributesAndroidInjector(modules = [ARModule::class])
    internal abstract fun arInjector(): ARController

}