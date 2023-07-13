package com.example.budget_tracker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.budget_tracker.databinding.ActivityAddTransactionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddTransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTransactionBinding
    private val fStore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val firebaseAuth: FirebaseAuth  = FirebaseAuth.getInstance()
    private val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private lateinit var type: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.expenseCheckbox.setOnClickListener {
            type = "expense"
        }
        binding.incomeCheckbox.setOnClickListener {
            type = "income"
        }
        binding.btnAddTransaction.setOnClickListener {
        val amount:String = binding.userAmountAdd.text.toString().trim()
            val note:String = binding.userNoteAdd.text.toString()
            if (amount.isEmpty()){
                return@setOnClickListener
            }

            if (type.isEmpty()) {
                Toast.makeText(applicationContext, "select transaction type", Toast.LENGTH_SHORT).show()
            }

            val id = UUID.randomUUID().toString()

            val map = mutableMapOf<String, Any>()
            map.putIfAbsent("id", id)
            map["amount"] = amount
            map["note"] = note
            map["type"] = type

            val documentReference = fStore.collection("Expenses").document(firebaseAuth.uid.toString()).collection("notes").document(id)
            documentReference.set(map)


        }
    }
}