package com.example.learnenglishwords

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglishwords.FIrstDemoActivity.ExtraWord
import com.example.learnenglishwords.databinding.ActivitySecondDemoBinding

class SecondDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondDemoBinding

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Log.i("!!!", "Разрешение на локацию получено")
            } else{
                Log.i("!!!", "Разрешение на локацию отклонено")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onCreate()")

        val btnLocation = binding.btnRequestPermission
        btnLocation.setOnClickListener {
            locationPermissionLauncher.launch(ACCESS_FINE_LOCATION)
            Log.i("!!!", "${this.componentName.shortClassName} клик")

        }

        with(binding){
            btnOpenFirst.setOnClickListener {
                val intent = Intent(this@SecondDemoActivity, FIrstDemoActivity::class.java)
                startActivity(intent)
            }

//            val text = intent.getStringExtra("EXTRA")
//            val number = intent.getIntExtra("EXTRA_INT", 0)
//
////            val word: ExtraWord = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
////                intent.getSerializableExtra("EXTRA_KEY_WORD", ExtraWord::class.java) as ExtraWord
////            } else {
////                intent.getSerializableExtra("EXTRA_KEY_WORD") as ExtraWord
////            }
//
//            val word = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                intent.getParcelableExtra("EXTRA_KEY_WORD", ExtraWord::class.java)
//            } else {
//                intent.getParcelableExtra("EXTRA_KEY_WORD")
//            }

            val bundle = intent.extras
            val text = bundle?.getString("EXTRA")
            val number = bundle?.getInt("EXTRA_INT")
            val word = bundle?.getParcelable("EXTRA_KEY_WORD", ExtraWord::class.java)

            tvText.text = text
            tvNumber.text = number.toString()
            tvWord.text = word?.original
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onResume() ")
    }

    override fun onPause() {
        super.onPause()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onPause() ")
    }

    override fun onStop() {
        super.onStop()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onDestroy()")
    }
}