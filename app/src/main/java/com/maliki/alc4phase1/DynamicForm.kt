package com.maliki.alc4phase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R.attr.colorPrimaryDark
import android.R.attr.colorAccent
import android.view.ViewGroup
import androidx.core.widget.TextViewCompat.setTextAppearance
import android.os.Build
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_dynamic_form.*
import android.widget.ArrayAdapter




class DynamicForm : AppCompatActivity() {
    var questions: HashMap<String, String> = HashMap()
    var baseView: LinearLayout? = null
    var answers: ArrayList<String>? = null
    var btn: Button? = null
    var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_form)
//        baseView = findViewById(R.id.baseLayout)
//        btn = findViewById(R.id.form_btn)

        // questions with kind of view to be created
        questions["What is your name"] = "dropdown"
        questions["How many children do you have"] = "text"
        questions["How many wives do you have"] = "text"
        questions["Where do you source your items"] = "dropdown"
        questions["Where do you live"] = "checkbox"
        answers = ArrayList()
//        val submit = findViewById(R.id.submit_btn)
        form_btn.setOnClickListener { view ->
            for (quest in questions.entries) {
                val textViewIpName = TextView(applicationContext)
                textViewIpName.text = quest.key
                textViewIpName.setTextColor(resources.getColor(R.color.colorPrimaryDark))
                textViewIpName.setTypeface(textViewIpName.typeface, Typeface.BOLD)
                val lp1 =
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                lp1.weight = 1f

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    textViewIpName.setTextAppearance(applicationContext, android.R.style.TextAppearance_Medium)
                } else {
                    textViewIpName.setTextAppearance(android.R.style.TextAppearance_Medium)
                }
                textViewIpName.layoutParams = lp1
                textViewIpName.maxLines = 1
                baseLayout.addView(textViewIpName)

                when (quest.value) {
                    "dropdown" -> {
                        val inputSpinner = Spinner(applicationContext)
                        val spinnerParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        spinnerParams.setMargins(0, 5, 0, 5)
                        inputSpinner.layoutParams = spinnerParams
                        // populate spinner with String Array
                        val adapter = populateSpinner()
                        inputSpinner.adapter = adapter
                        // set textColor
                        textViewIpName.setTextColor(resources.getColor(R.color.colorPrimaryDark))
                        // add the spinner(s) to the parent layout
                        baseLayout.addView(inputSpinner)
                    }

                    "text" -> {
                        val inputEditText = EditText(applicationContext)
                        val EditTextParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        EditTextParams.setMargins(0, 5, 0, 5)
                        inputEditText.layoutParams = EditTextParams
                        // set textColor
                        textViewIpName.setTextColor(resources.getColor(R.color.colorPrimaryDark))
                        // add the EditText(s) to the parent layout
                        baseLayout.addView(inputEditText)
                    }

                    "checkbox" -> {
                        val inputCheckBox = CheckBox(applicationContext)
                        val checkBoxParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        checkBoxParams.setMargins(0, 5, 0, 5)
                        inputCheckBox.layoutParams = checkBoxParams
                        inputCheckBox.text = "location"
                        inputCheckBox.setTextColor(resources.getColor(R.color.colorAccent))
                        inputCheckBox.id = R.id.checkbox
                        // set textColor
                        textViewIpName.setTextColor(resources.getColor(R.color.colorPrimaryDark))
                        // add the checkbox(es) to the parent layout
                        baseLayout.addView(inputCheckBox)
                    }
                }
                // Get number of children views in the parent linear layout
                count = baseLayout.childCount
            }
        }

        /*
    *  get views at different position and retrieve their values
    * */
        submit_btn.setOnClickListener{
            answers!!.clear()
            for (i in 0 until count) {
            val view1: View = baseLayout.getChildAt(i);
            if (view1 is EditText) {
                val edit:String  =  view1.text.toString()
                if (!edit.isEmpty()) {
                    answers!!.add(edit);
                }else{
                    Toast.makeText(this, "Please enter the required text", Toast.LENGTH_SHORT).show();
                }

            } else if (view1 is Spinner) {
                val text:String  = view1.selectedItem.toString()
                if (!text.equals("Select info")) {
                    answers!!.add(text);
                }else{
                    Toast.makeText(this, "Please select an entry", Toast.LENGTH_SHORT).show();
                }

            } else if (view1 is CheckBox) {
                val checkbox:String  =  view1.text.toString();
                answers!!.add(checkbox);
            }
            Log.d("view1", "onCreate: $view1$count");
        }
            Log.d("values", "  $answers");
        }
    }

    private fun populateSpinner(): ArrayAdapter<String> {
        val arraySpinner = arrayOf("select info", "1", "2", "3", "4", "5", "6", "7")
        val adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        return adapter
    }
}
