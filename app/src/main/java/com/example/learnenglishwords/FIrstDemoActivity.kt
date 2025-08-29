package com.example.learnenglishwords

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.learnenglishwords.databinding.ActivityFirstDemoBinding
import com.example.learnenglishwords.databinding.ActivityLearnWordBinding
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import javax.xml.transform.Source

class FIrstDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenMain.setOnClickListener {
            val intent = Intent(this@FIrstDemoActivity, ActivityLearnWordBinding::class.java)
            startActivity(intent)
        }

        val word = ExtraWord(
            "galaxy",
            "галактика"
        )

        binding.btnOpenSecond.setOnClickListener {
            val intent = Intent(this@FIrstDemoActivity, SecondDemoActivity::class.java).apply {
                putExtra("EXTRA", "meow")
                putExtra("EXTRA_INT", "1")
                putExtra("EXTRA_KEY_WORD", word)
            }

            val bundle = Bundle()

            intent.putExtras(
                bundleOf(
                    "EXTRA" to "meow",
                    "EXTRA_INT" to  "1",
                    "EXTRA_KEY_WORD" to  word,
                )
            )

            startActivity(intent)
        }
    }

    data class ExtraWord(
        val original: String,
        val translate: String,
        var learned: Boolean = false
    ) : Serializable

}