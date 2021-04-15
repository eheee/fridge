package com.heee.fridgetube.ui

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

open class BaseFragment : Fragment() {

    protected fun navigateUp() {
        try {
            if (parentFragmentManager.backStackEntryCount == 0) {
                activity?.finish()
            } else {
                findNavController().navigateUp()
            }
        } catch (e: Exception) {
            Log.w("BaseFragment", e)
        }
    }
}