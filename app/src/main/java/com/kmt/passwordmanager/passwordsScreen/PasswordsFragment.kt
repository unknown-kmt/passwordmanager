package com.kmt.passwordmanager.passwordsScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kmt.passwordmanager.R
import com.kmt.passwordmanager.database.AuthInfoDatabase
import com.kmt.passwordmanager.databinding.FragmentPasswordsBinding

class PasswordsFragment : Fragment() {

    companion object {
        fun newInstance() = PasswordsFragment()
    }

    private lateinit var viewModel: PasswordsViewModel
    private lateinit var viewModelFactory: PasswordsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentPasswordsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_passwords,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dao = AuthInfoDatabase.getInstance(application).getSleepDatabaseDao()
        viewModelFactory = PasswordsViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory)[PasswordsViewModel::class.java]

        return binding.root
    }
}