package xyz.arifz.materialedittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import xyz.arifz.materialedittext.databinding.ActivityDemoBinding

class DemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        binding.met.text = null
//
//        binding.btn.setOnClickListener {
//            val txt = binding.met.text
//            Toast.makeText(this, txt?: "empty", Toast.LENGTH_SHORT).show() }
    }
}