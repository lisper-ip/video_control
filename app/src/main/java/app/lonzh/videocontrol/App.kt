package app.lonzh.videocontrol

import app.lonzh.baselibrary.common.BaseApp
import app.lonzh.travel.startup.CommonMainStartUp
import app.lonzh.videocontrol.startup.CommonThreadStartUp
import com.rousetime.android_startup.StartupManager

class App : BaseApp(){

    override fun onCreate() {
        super.onCreate()
        StartupManager.Builder()
            .addStartup(CommonMainStartUp())
            .addStartup(CommonThreadStartUp())
            .build(this)
            .start()
            .await()
    }
}