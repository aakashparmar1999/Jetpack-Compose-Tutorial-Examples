package com.example.jetpackapp.loginandsignup

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePref(context: Context) {

    val dataStore: DataStore<Preferences>


    init {
        dataStore = context.applicationContext.createDataStore(
            name = "Profile",
            migrations = listOf(SharedPreferencesMigration(context, "profile"))
        )
    }

    companion object {
        val Name = preferencesKey<String>("name")
        val Username = preferencesKey<String>("username")
        val Password = preferencesKey<String>("password")
        val IsLogin = preferencesKey<Boolean>("isLogin")
    }

    /*   suspend fun getdata() {
                dataStore.data.collect { profile ->
                    try {

                    }catch (ex: Exception){
                        ex.printStackTrace()
                    }

                }
        }*/
    val getStatus: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[IsLogin] ?: false
        }

    suspend fun insertData(name: String, username: String, password: String) {
        dataStore.edit {
            it[Name] = name
            it[Username] = username
            it[Password] = password
            it[IsLogin] = false
            Log.e("TAG", "insertData: $name")
            Log.e("TAG", "insertData: $username")
            Log.e("TAG", "insertData: $password")
        }
    }

}