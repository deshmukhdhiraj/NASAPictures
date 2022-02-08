package com.nasapictures.base

import android.app.Application
import androidx.lifecycle.*
import com.sensibol.android.base.displayName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
@HiltViewModel
open class BaseViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    protected val context
        get() = getApplication<Application>()
    init {
        Timber.d("init: $displayName")
    }

    protected val _failure: MutableLiveData<Exception> by lazy { MutableLiveData() }

    val failure: LiveData<Exception> get() = _failure

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared: $displayName")
    }

    protected fun launchUseCases(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                block()
            } catch (e: Exception) {
                _failure.postValue(e)
            }
        }
    }
}