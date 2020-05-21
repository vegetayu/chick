package com.yuluyao.frog.func.divider

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuluyao.frog.R
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.fragment_divider_grid.*
import kotlinx.android.synthetic.main.item_data_grid.view.*
import yuluyao.frog.CleanAdapter
import yuluyao.frog.Divider
import yuluyao.frog.drag.DragCallback

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:52
 */
class DividerGridFragment : Fragment() {
  val adapter = object : CleanAdapter<Data>(R.layout.item_data_grid) {
    override fun onBindViewHolder(holder: Holder, position: Int) {
      holder.itemView.icon.setImageResource(data[position].iconRes)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_divider_grid, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val gridLayoutManager = GridLayoutManager(context, 4, LinearLayoutManager.VERTICAL, false)
    gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
      override fun getSpanSize(position: Int): Int {
        return when (position) {
          4, 8, 12 -> 2
          else -> 1
        }
      }
    }

    recycler.layoutManager = gridLayoutManager
    recycler.addItemDecoration(Divider(8f, R.color.item_decoration))
    setDrag(recycler)

    recycler.adapter = adapter
    Repo.refresh(40).subscribe { it ->
      adapter.data = it
      adapter.notifyDataSetChanged()
    }

  }

  private fun setDrag(recyclerView: RecyclerView) {
    val helper = ItemTouchHelper(DragCallback())
    helper.attachToRecyclerView(recyclerView)
  }
}
