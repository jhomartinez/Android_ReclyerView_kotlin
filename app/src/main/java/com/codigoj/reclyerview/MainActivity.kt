package com.codigoj.reclyerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codigoj.reclyerview.models.DataSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var blogAdapter: BlogRecyclerAdapter
    private lateinit var mAdapter: ItemTouchHelperAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        addDataSet()

        var itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                mAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return false
            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context,R.color.cardview_light_background))
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if(actionState == ItemTouchHelper.ACTION_STATE_DRAG){
                    viewHolder?.itemView?.setBackgroundColor(Color.LTGRAY)
                }
            }

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags: Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                var swipeFlags: Int = ItemTouchHelper.START or ItemTouchHelper.END

                return ItemTouchHelper.Callback.makeMovementFlags(dragFlags,swipeFlags)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                //blogAdapter.removeItem(viewHolder)
                mAdapter.onItemSwiped(viewHolder.adapterPosition)
            }
        }
        var itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recycler_view)
    }

    public fun addItemTouchHelper(mAdapter: ItemTouchHelperAdapter){
        this.mAdapter = mAdapter
    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        blogAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingItemDecoration = TopSpacingItemDecoration(30, 15)
            addItemDecoration(topSpacingItemDecoration)
            addItemDecoration(DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL))
            blogAdapter = BlogRecyclerAdapter()
            recycler_view.adapter = blogAdapter
            addItemTouchHelper(blogAdapter)
        }
    }
}
