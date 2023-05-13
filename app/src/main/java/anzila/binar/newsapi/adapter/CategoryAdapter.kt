package anzila.binar.newsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import anzila.binar.newsapi.databinding.ItemCategoryBinding
import anzila.binar.newsapi.model.CategoryData
import com.bumptech.glide.Glide

class CategoryAdapter(var listCategory : List<CategoryData> ): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var onClick : ((CategoryData) -> Unit)? = null

    class ViewHolder(var binding : ItemCategoryBinding ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        var view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.binding.categoryName.text = listCategory[position].name
        Glide.with(holder.itemView).load(listCategory[position].picture).into(holder.binding.categoryImage)
        holder.binding.itemCategory.setOnClickListener {
            onClick!!.invoke(listCategory[position])
        }
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }
}