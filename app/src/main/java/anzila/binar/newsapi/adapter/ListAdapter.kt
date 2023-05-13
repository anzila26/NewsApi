package anzila.binar.newsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import anzila.binar.newsapi.databinding.ItemListBinding
import anzila.binar.newsapi.model.list.Article
import com.bumptech.glide.Glide

class ListAdapter (var listList : List<Article>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var onClick : ((Article) -> Unit)? = null

    class ViewHolder(var binding : ItemListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        var view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        var source = listList[position]
        holder.binding.listName.text = listList[position].title
        Glide.with(holder.itemView).load(listList[position].urlToImage).into(holder.binding.listImage)
        holder.binding.itemList.setOnClickListener {
            onClick?.invoke(source)
        }
    }

    override fun getItemCount(): Int {
        return  listList.size
    }
}