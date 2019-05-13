package com.example.simplefund2

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val assetType = listOf("Indonesia", "Singapure", "Malaysia")
        val assetType_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, assetType)
        sp_nationality.adapter = assetType_adapter

        txt_expired_date.setOnClickListener { showDatePicker(it.id) }
        txt_expired_date.setOnFocusChangeListener { v, hasFocus -> if (hasFocus) showDatePicker(v.id) }

        txt_birth_date.setOnClickListener { showDatePicker(it.id) }
        txt_birth_date.setOnFocusChangeListener { v, hasFocus -> if (hasFocus) showDatePicker(v.id) }

        txt_spouse_birth_date.setOnClickListener { showDatePicker(it.id) }
        txt_spouse_birth_date.setOnFocusChangeListener { v, hasFocus -> if (hasFocus) showDatePicker(v.id) }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun showDatePicker(id: Int) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            findViewById<EditText>(id).setText("" + dayOfMonth + "-" + month.toString() + "-" + year.toString())
        }, year, month, day).show()
    }
}
