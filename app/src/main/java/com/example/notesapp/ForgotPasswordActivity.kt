package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.notesapp.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email= findViewById<EditText>(R.id.forgotemail)
        val sendbutton = findViewById<Button>(R.id.button1)
        auth= FirebaseAuth.getInstance()

        binding.button1.setOnClickListener {
            val mail=email.text.toString()
            auth.sendPasswordResetEmail(mail)
                .addOnCompleteListener{ task->
                    Toast.makeText(this,"Send E-Mail!!", Toast.LENGTH_LONG)
                        .show()
                    val intent= Intent(applicationContext,LogiNActivity::class.java)
                    startActivity(intent)
                    finish()
                }


                .addOnFailureListener{
                    Toast.makeText(this,it.toString(), Toast.LENGTH_LONG)
                        .show()
                }
        }





    }
}