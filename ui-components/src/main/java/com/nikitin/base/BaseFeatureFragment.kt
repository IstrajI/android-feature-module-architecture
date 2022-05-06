package com.nikitin.base

import androidx.viewbinding.ViewBinding
import com.nikitin.ui_components.DeepLinkNavigator
import javax.inject.Inject

abstract class BaseFeatureFragment<VB : ViewBinding> : BaseFragment<VB>() {
    @Inject
    protected lateinit var navigator: DeepLinkNavigator
}