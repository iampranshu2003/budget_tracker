package com.example.budget_tracker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.budget_tracker.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.dontHaveAccount.setOnClickListener {
            val intent = Intent(this , SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.loginBtn.setOnClickListener {
            binding.loginBtn.setOnClickListener{
                val email = binding.loginEmail.text.toString()
                val pass = binding.loginPassword.text.toString()

                if (email.isNotEmpty() && pass.isNotEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent( this , DashboardActivity::class.java)
                            startActivity(intent)
                        }
                    }

                }
                else{
                    Toast.makeText(this , "Empty fields are not allowed" , Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}