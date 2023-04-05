package com.kmt.passwordmanager.editScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kmt.passwordmanager.R
import com.kmt.passwordmanager.database.AuthInfoDatabaseDao
import com.kmt.passwordmanager.database.model.AuthInfoRecord

class EditRecordFragment() : Fragment() {

    private lateinit var viewModel: EditRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_record, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditRecordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}