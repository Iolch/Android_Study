package com.example.ciclodevida

import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.TextView

class MyHandler(val button:Button, val textView: TextView) : Handler(){

    val MSG = 100;

    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
        if(msg?.what == MSG)
        {
            button.isEnabled = true
            textView.text = "Processamento Finalizado!"
        }
    }

}