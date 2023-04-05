package com.kmt.passwordmanager.editScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.kmt.passwordmanager.R
import com.kmt.passwordmanager.database.AuthInfoDatabase
import com.kmt.passwordmanager.database.model.AuthInfoRecord
import com.kmt.passwordmanager.databinding.FragmentEditRecordBinding
import com.kmt.passwordmanager.passwordgenerator.PasswordGenerationUtil


class EditRecordFragment() : Fragment() {

    private lateinit var viewModel: EditRecordViewModel
    private lateinit var viewModelFactory: EditRecordViewModelFactory

    private lateinit var name: EditText
    private lateinit var login: EditText
    private lateinit var password: EditText
    private lateinit var url: EditText
    private lateinit var caption: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentEditRecordBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_edit_record,
            container,
            false
        )

        val application = requireActivity().application
        val dao = AuthInfoDatabase.getInstance(application).getSleepDatabaseDao()
        viewModelFactory = EditRecordViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory)[EditRecordViewModel::class.java]

        name = binding.recordName
        login = binding.recordLogin
        password = binding.recordPassword
        url = binding.recordUrl
        caption = binding.recordCaption

        val id = EditRecordFragmentArgs.fromBundle(requireArguments()).recordID
        val record = viewModel.getRecord(id)

        if (id != -1L) {
            if (record != null) {
                bindEditButton(binding, record)
                fillFields(record)
            }
        } else {
            bindAddingAction(binding)
        }

        binding.cancel.setOnClickListener(View.OnClickListener { back(it) })
        bindPasswordGenerate(binding)

        return binding.root
    }

    private fun fillFields(record: AuthInfoRecord) {
        name.setText(record.name, TextView.BufferType.NORMAL)
        login.setText(record.login, TextView.BufferType.NORMAL)
        password.setText(record.password, TextView.BufferType.NORMAL)
        url.setText(record.url, TextView.BufferType.NORMAL)
        caption.setText(record.caption, TextView.BufferType.NORMAL)
    }

    private fun bindEditButton(binding: FragmentEditRecordBinding, record: AuthInfoRecord) {
        binding.submit.setOnClickListener(View.OnClickListener { view ->
            val errors = validateFields(view)

            if (errors.isNotEmpty()) {
                Toast.makeText(view.context, errors, Toast.LENGTH_LONG).show()
                return@OnClickListener
            }

            record.name = name.text.toString()
            record.password = password.text.toString()
            record.login = login.text.toString()
            record.url = url.text.toString()
            record.caption = caption.text.toString()

            viewModel.editAuthData(record)

            back(view)
        })
    }

    private fun bindAddingAction(binding: FragmentEditRecordBinding) {
        binding.submit.setOnClickListener(View.OnClickListener { view ->
            val errors = validateFields(view)

            if (errors.isNotEmpty()) {
                Toast.makeText(view.context, errors, Toast.LENGTH_LONG).show()
                return@OnClickListener
            }

            viewModel.addNewAuthData(
                AuthInfoRecord(
                    name = name.text.toString(),
                    login = login.text.toString(),
                    password = password.text.toString(),
                    url = url.text.toString(),
                    caption = caption.text.toString()
                )
            )

            back(view)
        })
    }

    private fun validateFields(view: View) : String {
        val errors = StringBuilder()
        restoreColors()

        if (name.text.isEmpty()) {
            errors.append(view.context.resources.getString(R.string.name_not_set)).append("\n")
            setErrorColor(name)
        }

        if (login.text.isEmpty()) {
            errors.append(view.context.resources.getString(R.string.login_not_set)).append("\n")
            setErrorColor(login)
        }
        if (password.text.isEmpty()) {
            errors.append(view.context.resources.getString(R.string.password_not_set)).append("\n")
            setErrorColor(password)
        }

        return errors.toString()
    }

    private fun restoreColors() {
        name.setTextColor(resources.getColor(R.color.text_color))
        login.setTextColor(resources.getColor(R.color.text_color))
        password.setTextColor(resources.getColor(R.color.text_color))
    }

    private fun setErrorColor(element: TextView) {
        element.setTextColor(resources.getColor(R.color.error_color))
    }

    private fun back(view: View) {
        view.findNavController().navigate(EditRecordFragmentDirections.actionEditRecordFragmentToPasswordsFragment())
    }

    private fun bindPasswordGenerate(binding: FragmentEditRecordBinding) {
        binding.generateButton.setOnClickListener(View.OnClickListener {
            if (binding.passLength.text.isEmpty()) {
                binding.passLength.setText("6")
            }

            val password = PasswordGenerationUtil.generate(
                binding.passLength.text.toString().toInt(),
                binding.passUseLowerCase.isChecked,
                binding.passUseUpperCase.isChecked,
                binding.passUseNumbers.isChecked,
                binding.passUseSpecial.isChecked
            )

            binding.recordPassword.setText(password)
        })
    }
}