package com.filochowski.smb_cw1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.filochowski.smb_cw1.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            auth.signInWithEmailAndPassword(
                eTEmail.text.toString(),
                eTPassword.text.toString()
            )
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        Toast.makeText(this, "Successfull login", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java).also {
                            it.putExtra("user", auth.currentUser?.email)
                        })

                    } else {
                        Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show()
                    }
                }

        }

        loginButton.setOnLongClickListener {
            auth.createUserWithEmailAndPassword(
                eTEmail.text.toString(),
                eTPassword.text.toString()
            )
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        Toast.makeText(this, "Successfull user registration", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }

            true
        }
    }
}