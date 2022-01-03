package uz.shokirov.flashlightapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.shokirov.flashlightapp.databinding.ActivityAboutBinding


class AboutActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.githubLink.setOnClickListener {
            setLink("https://github.com/shokirovollohberdi")
        }
        binding.instaLink.setOnClickListener {
            setLink("https://www.instagram.com/shokirov_it_blog/")
        }
        binding.telegramLink.setOnClickListener {
            setLink("https://t.me/shokirov_ollohberdi")
        }

    }

    private fun setLink(downloadLink: String) {
        val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(downloadLink))
        startActivity(myIntent)
    }

}