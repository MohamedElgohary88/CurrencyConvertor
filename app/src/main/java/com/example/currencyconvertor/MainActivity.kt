package com.example.currencyconvertor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    // هنا عملنا تاجيل لعملية  initlizion مع هذه المتغيرات
    private lateinit var todropdownmenu: AutoCompleteTextView
    private lateinit var fromdropdownmenu: AutoCompleteTextView
    private lateinit var convert: Button
    private lateinit var amountET: TextInputEditText
    private lateinit var resultET: TextInputEditText
    private lateinit var toolbar: Toolbar

    // هنا عرفنا المتغيرات الخاصة باسماء العملات واختصارات اسماء العملات
    private val egyptionpound = "EgyptionPound"
    private val amricandollar = "Amrican Dollar"
    private val AED = "AED" ; private val GBP = "GBP" ; private val SAR = "SAR"
    private val KWD = "KWD" ; private val EUR = "EUR" ; private val Bitcoin = "Bitcoin"
    private val QAR = "QAR" ; private val CNY = "CNY" ; private val INR = "INR"
    private val AUD = "AUD" ; private val BRL = "BRL" ; private val KRW = "KRW"

    // هنا عملنا جدول باستخدام map of لقيمة كل عملة من العملات
    private val values = mapOf(amricandollar to 1.0, egyptionpound to 15.71, AED to 3.67,
        GBP to 0.73, SAR to 3.75, KWD to 0.302, EUR to 0.88, Bitcoin to 0.0000228724,
        QAR to 3.64, CNY to 6.35, INR to 75.66, AUD to 1.40, BRL to 5.21, KRW to 1.198)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initliazViews()
        populatedropdownmenu()

 /////////////////////////////////   (( اعمل implementation ل toolbar عشان الكود يشتغل ))  ////////////

        toolbar.inflateMenu(R.menu.options_menu)
             toolbar.setOnMenuItemClickListener { menuItem ->
                 when (menuItem.itemId) {
                     R.id.share -> {
                         Toast.makeText(this, "share", Toast.LENGTH_SHORT).show()
                     }
                     R.id.settings -> {
                         Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
                     }
                     R.id.contact -> {
                         Toast.makeText(this, "contact", Toast.LENGTH_SHORT).show()
                     }
                 }
                 true
             }

        // ده addTextChangedListener جواه
        amountET.addTextChangedListener { calculateresult() }
        fromdropdownmenu.setOnItemClickListener { adapterView, view, i, l -> calculateresult() }
        todropdownmenu.setOnItemClickListener { adapterView, view, i, l -> calculateresult() }
    }

    // دى دالة لتعريف متغيرات dropdownmenu فى الخارج عشان تنظيم الكود
    private fun populatedropdownmenu() {
        val listofCountry = listOf(egyptionpound, amricandollar, AED, GBP,
            SAR, KWD, EUR, Bitcoin, QAR, CNY, INR, AUD, BRL, KRW)

        val adapter = ArrayAdapter(this, R.layout.drop_down_list_item, listofCountry)
        todropdownmenu.setAdapter(adapter)
        fromdropdownmenu.setAdapter(adapter)
    }

    //  initlizion دى دالة لعملية
    private fun initliazViews() {
        convert = findViewById(R.id.convert)
        amountET = findViewById(R.id.amount_edit_text)
        resultET = findViewById(R.id.result_edit_text)
        todropdownmenu = findViewById(R.id.to_currency_menu)
        fromdropdownmenu = findViewById(R.id.from_auto_complete_text_view)
        toolbar = findViewById(R.id.toolbar)
    }

    // دالة لعملية التحويل
    private fun calculateresult() {
        if (amountET.text.toString().isNotEmpty()) {
            val amount = amountET.text.toString().toDouble()
            val tovalue = values[todropdownmenu.text.toString()]
            val fromvalue = values[fromdropdownmenu.text.toString()]
            val result = amount.times(tovalue!!.div(fromvalue!!))
            val formatedresult = String.format("%.2f", result)
            resultET.setText(formatedresult)
        } else {
            amountET.setError("amount filed required")
        }
    }

/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
            }
            R.id.contact -> {
                Toast.makeText(this, "contact", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    */

}



