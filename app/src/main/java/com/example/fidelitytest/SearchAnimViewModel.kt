package com.example.fidelitytest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchAnimViewModel : ViewModel() {
    var anims:  MutableLiveData<List<AnimsResult>> = MutableLiveData()

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getAnims(searchName: String) {
        coroutineScope.launch {
            val getAnimsDeferred = AnimApi.retrofitService.getAnim(searchName)
            try {
                // this will run on a thread managed by Retrofit
                val animsM = getAnimsDeferred.await()
                anims.value = animsM.results!!
                Log.e("names1", anims.value?.size.toString())
            } catch (e: Exception) {
                anims.value = ArrayList()
                Log.e("error", e.message.toString())
            }
        }
    }
}