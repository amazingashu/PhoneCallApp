package com.acrony.phonecallapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.SmsManager

import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    internal var TAG = MainActivity::class.java.simpleName
    lateinit var context: Context

    lateinit var edtMobile: EditText
    lateinit var btnCall: Button
    lateinit var btnCallView: Button

    lateinit var edt_message: EditText
    lateinit var btn_message: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            context = this

            edtMobile = findViewById(R.id.edt_mobile) as EditText
            edt_message = findViewById(R.id.edt_message) as EditText
            btnCall = findViewById(R.id.btn_call) as Button
            btnCallView = findViewById(R.id.btn_call_view) as Button
            btn_message = findViewById(R.id.btn_message) as Button

            btnCall.setOnClickListener {

                if (edtMobile.text != null && edtMobile.text.toString().trim { it <= ' ' }.length > 0) {

                    val callIntent = Intent(Intent.ACTION_CALL)

                    callIntent.data = Uri.parse("tel:" + edtMobile.text.toString())

                    startActivity(callIntent)

                }
                else run { Toast.makeText(context, "Please enter the mobile number", Toast.LENGTH_LONG).show() }
            }


            btnCallView.setOnClickListener {
                if (edtMobile.text != null && edtMobile.text.toString().trim { it <= ' ' }.length > 0) {

                    val callIntent = Intent(Intent.ACTION_VIEW)

                    callIntent.data = Uri.parse("tel:" + edtMobile.text.toString())

                    startActivity(callIntent)

                } else run { Toast.makeText(context, "Please enter the mobile number", Toast.LENGTH_LONG).show() }
            }

            btn_message.setOnClickListener {

                //Method 1
               // val uri=Uri.parse("smsto:9848836758")
              // val uri=Uri.parse("smsto:"+edtMobile.text.toString())
               // val intent=Intent(Intent.ACTION_SENDTO,uri)
               // intent.putExtra("sms_body",edt_message.text.toString())
                //startActivity(intent)

                //Method 2
                val smsManager=SmsManager.getDefault() as SmsManager
                smsManager.sendTextMessage(edtMobile.text!!.toString(),null,edt_message.text!!.toString(),null,null)


            }

        } catch (ex: Exception) {
            Log.e(TAG, ex.message)
        }

    }
}