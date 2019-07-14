package com.maliki.alc4phase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import julianfalcionelli.magicform.validation.ValidationNotEmpty
import julianfalcionelli.magicform.base.FormField
import android.util.Patterns
import julianfalcionelli.magicform.validation.ValidationRegex
import julianfalcionelli.magicform.MagicForm
import julianfalcionelli.magicform.base.FormError
import julianfalcionelli.magicform.base.ValidatorCallbacks
import kotlinx.android.synthetic.main.activity_dynamic_form_lib.*


class DynamicFormLib : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_form_lib)
        add.setOnClickListener {
            MagicForm()
                .addField(
                    FormField(pass)
                        .addValidation(ValidationRegex(Patterns.EMAIL_ADDRESS).setMessage("invalid email"))
                )
                .addField(
                    FormField(email)
                        .addValidation(ValidationNotEmpty().setMessage("Required Field"))
                )
                .setListener(object : ValidatorCallbacks{
                    override fun onSuccess() {
                       Log.d("UI","Created")
                    }

                    override fun onFailed(errors: MutableList<FormError>?) {
                        Log.d("UI","Failed")                    }
                })

        }
//        val mMagicForm = MagicForm()
//            .addField(
//                FormField(pass)
//                    .addValidation(ValidationRegex(Patterns.EMAIL_ADDRESS).setMessage("invalid email"))
//            )
//            .addField(
//                FormField(email)
//                    .addValidation(ValidationNotEmpty().setMessage("Required Field"))
//            )
//            .setListener(this)

    }

}

