package com.example.test

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

class ContactsActivity : AppCompatActivity() {
    //var tv_phonebook: TextView? = null
    //var arrayList: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        //tv_phonebook = findViewById(R.id.tv_phonebook)
        //arrayList = ArrayList()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS), 1
            )
        } else {
            getcontact()
        }
    }

    @SuppressLint("Range")
    private fun getcontact() : JSONArray{
        val resolver: ContentResolver = contentResolver

        val cursor = resolver.query(
            //ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        val mainJsonArray = JSONArray()

        if (cursor!!.count > 0) {
            while (cursor.moveToNext()) {
                val personJsonObj = JSONObject()
                val id =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                personJsonObj.accumulate("NAME", name.replace("\\", "\\\\").replace("'", "\\'").replace("\"", "\\\""))

                val orgCursor = contentResolver.query(
                    ContactsContract.Data.CONTENT_URI, null,
                    ContactsContract.Data.CONTACT_ID + "=?", arrayOf(id), null
                )

                val phoneJsonArray = JSONArray()
                if (orgCursor!!.count > 0) {
                    while (orgCursor.moveToNext()) {

                        if (orgCursor.getString(orgCursor.getColumnIndex(ContactsContract.Data.MIMETYPE)).equals(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
                            val phoneNum = orgCursor.getString(orgCursor.getColumnIndex(ContactsContract.Data.DATA1))
                            phoneJsonArray.put(phoneNum.replace(" ", "").replace("-", ""))
                        }
                    }
                }
                orgCursor.close()
                personJsonObj.accumulate("PHONE_NUMBERS", phoneJsonArray)
                mainJsonArray.put(personJsonObj)
/*
                arrayList!!.add(
                    """
                    $name
                    $mobile
                    """.trimIndent()
                )
                tv_phonebook!!.text = arrayList.toString()
 */
            }
        }
        cursor.close()
        return mainJsonArray
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getcontact()
            }
        }
    }
}