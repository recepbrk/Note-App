package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import es.dmoral.toasty.Toasty

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            val email =binding.emailET.text.toString()
            val pass =binding.passEt.text.toString()
            val confirmPass =binding.confirmpassET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){

                if (pass==confirmPass){

                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            startActivity(Intent(this,MainActivity::class.java))
                        }else{
                            Toasty.error(this, it.exception.toString(), Toast.LENGTH_SHORT, true).show();
                        }
                    }

                }else
                {
                    Toasty.error(this, "Passwords Did Not Match !", Toast.LENGTH_SHORT, true).show();
                }

            }else
            {
                Toasty.info(this, "Fill All Fields !", Toast.LENGTH_SHORT, true).show();
            }
        }

    }
}