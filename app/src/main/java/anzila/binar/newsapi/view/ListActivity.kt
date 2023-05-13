package anzila.binar.newsapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import anzila.binar.newsapi.adapter.ListAdapter
import anzila.binar.newsapi.databinding.ActivityListBinding
import anzila.binar.newsapi.viewmodel.ListViewModel

class ListActivity : AppCompatActivity() {
    lateinit var binding : ActivityListBinding
    lateinit var listAdapter : ListAdapter
    lateinit var listVm : ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listVm = ViewModelProvider(this).get(ListViewModel::class.java)
        listAdapter = ListAdapter(emptyList())
        val layoutMan = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.layoutManager = layoutMan
        binding.rvList.adapter = listAdapter

        listVm.getDataList().observe(this, Observer{
            if (it != null) {
                listAdapter.listList = it
                listAdapter.notifyDataSetChanged()
            }
        })

        val datasour = intent.extras!!.getString("name")
        listVm.callApiList(datasour.toString())

        binding.rvList.apply{
            layoutManager = LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
            listAdapter.onClick={
                var list = it.url
                val inten = Intent(context, DetailActivity::class.java)
                inten.putExtra("url",list)
                startActivity(inten)
            }
        }
    }
}