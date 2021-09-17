package com.example.fidelitytest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


enum class AnimApiStatus { LOADING, ERROR, DONE }
class ListAnimViewModel : ViewModel() {

    private val _anims = MutableLiveData<List<AnimsResult>>()
    val anims: MutableLiveData<List<AnimsResult>> = _anims

    //API status
    private val _status = MutableLiveData<AnimApiStatus>()
    val status: LiveData<AnimApiStatus> = _status

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getAnims()
    }

    private fun getAnims() {
        coroutineScope.launch {
            val getAnimsDeferred = AnimApi.retrofitService.getAnim("naruto")
            try {
                _status.value = AnimApiStatus.LOADING
                // this will run on a thread managed by Retrofit
                val animsM = getAnimsDeferred.await()
                _anims.value = animsM.results!!
                _status.value = AnimApiStatus.DONE
                Log.e("names1", anims.value?.size.toString())
            } catch (e: Exception) {
                _status.value = AnimApiStatus.ERROR
                _anims.value = ArrayList()
                Log.e("error", e.message.toString())
            }
        }
    }
}