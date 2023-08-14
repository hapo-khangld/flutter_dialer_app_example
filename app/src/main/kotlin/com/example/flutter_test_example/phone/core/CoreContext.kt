package com.example.flutter_test_example.phone.core

import android.content.Context
import androidx.lifecycle.*

class CoreContext(val context: Context) : LifecycleOwner, ViewModelStoreOwner {

    private val _lifecycleRegistry = LifecycleRegistry(this)
    override val lifecycle: Lifecycle
        get() = _lifecycleRegistry

    private val _viewModelStore = ViewModelStore()

    override val viewModelStore: ViewModelStore
        get() = _viewModelStore

}