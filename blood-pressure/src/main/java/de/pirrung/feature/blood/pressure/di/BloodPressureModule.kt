package de.pirrung.feature.blood.pressure.di

import android.app.Application
import androidx.room.Room
import de.pirrung.feature.blood.pressure.data.data_source.BloodPressureDao
import de.pirrung.feature.blood.pressure.data.data_source.BloodPressureDatabase
import de.pirrung.feature.blood.pressure.data.repository.BloodPressureRepositoryImpl
import de.pirrung.feature.blood.pressure.domain.repository.BloodPressureRepository
import de.pirrung.feature.blood.pressure.domain.use_case.*
import de.pirrung.feature.blood.pressure.presentation.blood_pressure.BloodPressureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bloodPressureModule = module {
    single {
        provideDatabase(get())
    }

    single {
        provideDao(get())
    }

    single<BloodPressureRepository> {
        BloodPressureRepositoryImpl(get())
    }

    single {
        AddBloodPressureMeasurement(get())
    }

    single {
        DeleteBloodPressureMeasurement(get())
    }

    single {
        GetBloodPressureMeasurement(get())
    }

    single {
        GetFirstTenBloodPressureMeasurements(get())
    }

    single {
        GetBloodPressureMeasurements(get())
    }

    viewModel {
        BloodPressureViewModel(get(), get(), get(), get())
    }
}

private fun provideDatabase(application: Application): BloodPressureDatabase =
    Room.databaseBuilder(
        application,
        BloodPressureDatabase::class.java,
        BloodPressureDatabase.DB_NAME
    )
        .build()

private fun provideDao(db: BloodPressureDatabase): BloodPressureDao = db.bloodPressureDao