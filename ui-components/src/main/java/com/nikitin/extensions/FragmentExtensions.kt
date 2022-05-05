package com.nikitin.extensions

import androidx.fragment.app.Fragment

fun Fragment.requireGrandParentFragment(): Fragment {
    return requireParentFragment().requireParentFragment()
}