package com.example.oncoassist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {

    private lateinit var suser: EditText
    private lateinit var semail: EditText
    private lateinit var spassword: EditText
    private lateinit var sbutton: Button
    private lateinit var firebase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        sbutton = findViewById(R.id.sbutton)
        suser = findViewById(R.id.suser)
        semail = findViewById(R.id.semail)
        spassword = findViewById(R.id.spassword)

        firebase = FirebaseDatabase.getInstance().getReference("user")

        sbutton.setOnClickListener {
            saveUserData()
        }
    }

    private fun saveUserData() {
        val username = suser.text.toString()
        val useremail = semail.text.toString()
        val userpass = spassword.text.toString()

        if (username.isEmpty()) {
            suser.error = "Please enter name"
            return
        }
        if (useremail.isEmpty()) {
            semail.error = "Please enter email"
            return
        }
        if (userpass.isEmpty()) {
            spassword.error = "Please enter password"
            return
        }

        val userId = firebase.push().key ?: ""
        val user = SignIn(username, useremail, userpass)

        firebase.child(userId).setValue(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                suser.text.clear()
                semail.text.clear()
                spassword.text.clear()
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}


// Nishit 1