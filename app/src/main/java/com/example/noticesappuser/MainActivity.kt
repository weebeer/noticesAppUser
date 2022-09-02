package com.example.noticesappuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noticesappuser.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        recoveryNotice()
    }

    private fun recoveryNotice(){
        db.collection("notices").document("notice").get()
            .addOnCompleteListener { document ->
                if (document.isSuccessful){

                    val title = document.result.get("title").toString()
                    val notice = document.result.get("notice").toString()
                    val date = document.result.get("date").toString()
                    val author = document.result.get("author").toString()

                    binding.txtNoticeAuthor.text = author
                    binding.txtNoticeDate.text = date
                    binding.txtNoticeText.text = notice
                    binding.txtNoticeTitle.text = title
                }
            }
    }
}