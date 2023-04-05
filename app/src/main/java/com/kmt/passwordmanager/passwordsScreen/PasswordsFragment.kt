package com.kmt.passwordmanager.passwordsScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kmt.passwordmanager.R
import com.kmt.passwordmanager.database.AuthInfoDatabase
import com.kmt.passwordmanager.databinding.FragmentPasswordsBinding

class PasswordsFragment : Fragment() {
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

        val application = requireActivity().application
        val dao = AuthInfoDatabase.getInstance(application).getSleepDatabaseDao()
        viewModelFactory = PasswordsViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory)[PasswordsViewModel::class.java]

        val adapter = AuthInfoAdapter()
        binding.recordsList.adapter = adapter

        viewModel.records.observe(viewLifecycleOwner, Observer { records ->
            if (records != null) {
                adapter.data = records
            }
        })

        // Adding Auth data
        binding.add.setOnClickListener(View.OnClickListener { view ->
            view.findNavController().navigate(PasswordsFragmentDirections.actionPasswordsFragmentToEditRecordFragment(-1))
        })

        return binding.root
    }
}