package com.example.myapp.views.fragment.profile

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.myapp.databinding.FragmentProfileBinding
import com.example.myapp.model.Contact
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {


    val binding by lazy {
        FragmentProfileBinding.inflate(layoutInflater).apply {
            model = viewModel
        }
    }

    val viewModel: ProfileVM by viewModels()

    var contactList: ArrayList<Contact> = ArrayList<Contact>()

    private val PROJECTION = arrayOf(
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
        ContactsContract.Contacts.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        checkPermission()
//        getContactList()
        return binding.root
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_CONTACTS
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            getContactList()
        } else {
            // Requesting the permission
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // PERMISSION GRANTED
            getContactList()

        } else {
            // PERMISSION NOT GRANTED
            Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()

        }
    }


    private fun getContactList() {
        val cr: ContentResolver = requireActivity().contentResolver
        val cursor = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            PROJECTION,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        Log.d("status", "fetching")
        if (cursor != null) {
            val mobileNoSet = HashSet<String>()
            try {
                val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val numberIndex =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                var name: String
                var number: String
                while (cursor.moveToNext()) {
                    name = cursor.getString(nameIndex)
                    number = cursor.getString(numberIndex)
                    number = number.replace(" ", "")
                    if (!mobileNoSet.contains(number)) {

                        contactList.add(Contact(name, number))
                        mobileNoSet.add(number)
                    }
                }
            } finally {
                Log.d("status", "fetchingClose")
                Log.d("status", "${contactList}")
                cursor.close()

                viewModel.adapter.setData(contactList)
            }
        }
    }

}
