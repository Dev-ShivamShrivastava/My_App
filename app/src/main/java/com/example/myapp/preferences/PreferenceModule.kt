package com.example.myapp.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object PreferenceModule {



    @Singleton
    @Provides
    fun getPreference(
        @ApplicationContext context:Context
    ): SharedPreferences {
        return  context.getSharedPreferences(
            "MySharedPref",
            Context.MODE_PRIVATE
        )
    }

    @Singleton
    @Provides
    fun getEditor(
         sharedPreferences: SharedPreferences
    ): SharedPreferences.Editor {
        return  sharedPreferences.edit()
    }



}