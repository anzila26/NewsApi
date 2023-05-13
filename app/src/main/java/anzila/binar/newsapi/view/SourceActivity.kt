package anzila.binar.newsapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import anzila.binar.newsapi.adapter.SourceAdapter
import anzila.binar.newsapi.databinding.ActivitySourceBinding
import anzila.binar.newsapi.viewmodel.SourceViewModel

class SourceActivity : AppCompatActivity() {
    lateinit var binding : ActivitySourceBinding
    lateinit var sourceAdapter : SourceAdapter
    lateinit var sourceVm : SourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sourceVm = ViewModelProvider(this).get(SourceViewModel::class.java)
        sourceAdapter = SourceAdapter(emptyList())
        val layoutMan = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvSource.layoutManager = layoutMan
        binding.rvSource.adapter = sourceAdapter

        sourceVm.getDataSource().observe(this, Observer {
            if (it != null) {
                sourceAdapter.listSource = it
                sourceAdapter.notifyDataSetChanged()
            }
        })

        val datacat = intent.extras!!.getString("name")
        sourceVm.callApiSource(datacat.toString())

        binding.rvSource.apply{
            layoutManager = LinearLayoutManager(this@SourceActivity, LinearLayoutManager.VERTICAL, false)
            adapter = sourceAdapter
            sourceAdapter.onClick={
                var sour = it.name
                val inten = Intent(context, ListActivity::class.java)
                inten.putExtra("name",sour)
                startActivity(inten)
            }
        }
    }
}