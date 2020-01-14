package com.codigoj.reclyerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codigoj.reclyerview.models.BlogPost
import kotlinx.android.synthetic.main.layout_blog_list_item.view.*
import java.util.ArrayList

class BlogRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    ItemTouchHelperAdapter {

    private var items: MutableList<BlogPost> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_blog_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        when(holder){
            is BlogViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: MutableList<BlogPost>){
        items = blogList
    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder) {
        items.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
    }


    class BlogViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView){
        val blogImage = itemView.blog_image
        val blogTitle= itemView.blog_title
        val blogAuthor= itemView.blog_author

        fun bind(blogPost: BlogPost){
            blogTitle.setText(blogPost.title)
            blogAuthor.setText(blogPost.username)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(blogPost.image)
                .into(blogImage)
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        var blogPost:BlogPost = items?.get(fromPosition)
        items?.remove(blogPost)
        items?.add(toPosition,blogPost)
        notifyItemMoved(fromPosition,toPosition)
    }

    override fun onItemSwiped(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }


}