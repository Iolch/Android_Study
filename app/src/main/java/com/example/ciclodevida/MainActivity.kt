package com.example.ciclodevida

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private var edtUsername : EditText? = null; //temos que definir como nula, já que começa com valor nulo
    private var edtEmail : EditText? = null;

//    private val handler:Handler = Handler()     // Método 2

    private var myHandler:MyHandler ?= null     // Método 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtUsername = findViewById(R.id.edtUsername) //faz referencia ao edtUsername dentro do xml
        edtEmail = findViewById(R.id.edtEmail)

        val txt = findViewById<TextView>(R.id.processText);     // Método 3
        val btn = findViewById<Button>(R.id.nextButton);        // Método 3
        myHandler = MyHandler(btn, txt)                         // Método 3

    }

    fun next(view : View)
    {
        var username : String = edtUsername?.text.toString()    //a interrogação representa que edtUsername pode ser nulo
        var email : String = edtEmail?.text.toString()    //a interrogação representa que edtEmail pode ser nulo

        Toast.makeText(this, "Username = $username.  Email =  $email", Toast.LENGTH_LONG).show();

        execInProcess(true)
        Thread {
            SystemClock.sleep(5000)
//            runOnUiThread{                        // Método 1
//                execInProcess(false)
//            }

//            handler.post{                         // Método 2
//                execInProcess(false)
//            }

            var msg:Message = Message.obtain()      // Método 3
            msg.what = 100
            myHandler?.sendMessage(msg)

        }.start()


        var intent = Intent(this, Main2Activity::class.java);
//        startActivity(intent);
    }

    fun execInProcess(exec : Boolean)
    {
        val editText = findViewById<TextView>(R.id.processText);
        val editButton = findViewById<Button>(R.id.nextButton);
        if(exec) editText.text = "Processando..."
        else editText.text = "Finalizado!"

        editButton.isEnabled = !exec;

    }
}
