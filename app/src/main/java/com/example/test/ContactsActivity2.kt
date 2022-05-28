package com.example.test

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

@Suppress("DEPRECATION")
class ContactsActivity2 : AppCompatActivity() {
    var name: EditText? = null
    var city:EditText? = null
    var state:EditText? = null
    var country:EditText? = null
    var mProgressDialog: ProgressDialog? = null
    private val INSERTDATA_URL = "paste file URL here"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts2)

        name = findViewById(R.id.editTextname)
        city = findViewById(R.id.editTextcity)
        state = findViewById(R.id.editTextstate)
        country = findViewById(R.id.editTextcountry)

        val insertdata = findViewById<Button?>(R.id.buttoninsertdata).setOnClickListener() {
            val Name = name?.text.toString().trim { it <= ' '}.lowercase(Locale.getDefault())
            val City = city?.text.toString().trim {it <= ' '}.lowercase(Locale.getDefault())
            val State = state?.text.toString().trim { it <= ' ' }.lowercase(Locale.getDefault())
            val Country = country?.text.toString().trim { it <= ' ' }.lowercase(Locale.getDefault())
            if (Name == "" || City == "" || State == "" || Country == "") {
                Toast.makeText(applicationContext, "Please Enter Detail", Toast.LENGTH_SHORT).show()
            }
            InsertData()
        }
    }

    private fun InsertData() {
        val Name = name?.text.toString().trim().lowercase()
        val City = city?.text.toString().trim().lowercase()
        val State = state?.text.toString().trim().lowercase()
        val Country = country?.text.toString().trim().lowercase()
        if (Name == "" || City == "" || State == "" || Country == "") {
            Toast.makeText(applicationContext, "Please Enter Detail", Toast.LENGTH_SHORT).show()
        }
        else {
            register(Name, City, State, Country)
        }
    }

    private fun register(Name: String, City: String, State: String, Country: String) {
        class RegisterUsers :
            AsyncTask<String?, Void?, String?>() {
            var loading: ProgressDialog? = null
            var ruc = RegisterUserClass()
            @Deprecated("Deprecated in Java")
            override fun onPreExecute() {
                super.onPreExecute()
                mProgressDialog = ProgressDialog(applicationContext)
                mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                mProgressDialog!!.setMessage("Please wait....")
                mProgressDialog!!.isIndeterminate = true
                mProgressDialog!!.setCancelable(false)
                mProgressDialog!!.progress = 0
                mProgressDialog!!.setProgressNumberFormat(null)
                mProgressDialog!!.setProgressPercentFormat(null)
                mProgressDialog!!.show()
            }

            @Deprecated("Deprecated in Java")
            override fun onPostExecute(s: String?) {
                super.onPostExecute(s)
                mProgressDialog!!.dismiss()
                Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }

            @Deprecated("Deprecated in Java")
            override fun doInBackground(vararg params: String?): String? {
                val data = HashMap<String, String>()
                data["Name"] = params[0].toString()
                data["City"] = params[1].toString()
                data["State"] = params[2].toString()
                data["Country"] = params[3].toString()
                return ruc.sendPostRequest(INSERTDATA_URL, data)
            }

        }

        val ru = RegisterUsers()
        ru.execute(Name, City, State, Country)
    }
}