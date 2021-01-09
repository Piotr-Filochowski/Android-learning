package com.filochowski.smb_cw1.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.filochowski.smb_cw1.R
import com.filochowski.smb_cw1.dto.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            auth.signInWithEmailAndPassword(
                eTEmail.text.toString(),
                eTPassword.text.toString()
            )
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        Toast.makeText(this, "Successfull login", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("user", auth.currentUser)
                        val currentUser = auth.currentUser
                        startActivity(intent)

                    } else {
                        var message: String? = it.exception?.message
                        if(message != null) {
                            Toast.makeText(this, "Login error $message", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Login unknown error", Toast.LENGTH_SHORT).show()
                        }
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