package anzila.binar.newsapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import anzila.binar.newsapi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datadet = intent.extras!!.getString("url")
        Toast.makeText(this, "News URL : $datadet", Toast.LENGTH_SHORT).show()

        binding.detailNews.apply {
            webViewClient = WebViewClient()
            loadUrl(datadet.toString())
        }
    }
}