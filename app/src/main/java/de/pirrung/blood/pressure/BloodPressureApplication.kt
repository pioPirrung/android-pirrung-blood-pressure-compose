package de.pirrung.blood.pressure

import android.app.Application
import de.pirrung.feature.blood.pressure.di.bloodPressureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BloodPressureApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BloodPressureApplication)
            modules(
                bloodPressureModule
            )
        }
    }

}