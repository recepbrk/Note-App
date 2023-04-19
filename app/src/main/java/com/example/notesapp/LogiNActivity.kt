package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.notesapp.databinding.ActivityLogiNactivityBinding
import com.google.firebase.auth.FirebaseAuth
import es.dmoral.toasty.Toasty

class LogiNActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogiNactivityBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogiNactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.kayTTxt.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }


        binding.btnKaydet.setOnClickListener {

            performSignUp()
        }

        binding.textView11.setOnClickListener {
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
        }


    }

    private fun performSignUp() {
        firebaseAuth=FirebaseAuth.getInstance()
        val email = findViewById<EditText>(R.id.KayitKullaniciadi)
        val pass= findViewById<EditText>(R.id.KayitParola)

        if (email.text.isEmpty() || pass.text.isEmpty()) {
            Toasty.info(this, "Please Fill All Fields !", Toast.LENGTH_SHORT, true).show();
            return
        }
        val inputmail = email.text.toString()
        val inputpassword = pass.text.toString()

        firebaseAuth.signInWithEmailAndPassword(inputmail, inputpassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toasty.success(this, "Registration Successful .", Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(this, "Registration Failed .", Toast.LENGTH_SHORT, true).show();
                }
            }

            .addOnFailureListener {

                Toasty.error(this, "error${it.localizedMessage}.", Toast.LENGTH_SHORT, true).show();
            }
    }
}