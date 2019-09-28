package com.yuluyao.frog.func.divider

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yuluyao.frog.decor.FrogDivider
import yuluyao.frog.drag.DragCallback
import com.yuluyao.frog.R
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.fragment_divider_staggered_vertical.*
import yuluyao.frog.FrogAdapter

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 20:06
 */
class DividerStaggeredVerticalFragment : Fragment() {
  val adapter = FrogAdapter<Data>(R.layout.item_data_stagger_vertical)


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_divider_staggered_vertical, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler.layoutManager = StaggeredGridLayoutManager(4,
      StaggeredGridLayoutManager.VERTICAL)
    recycler.addItemDecoration(FrogDivider(4f, R.color.item_decoration))
    setDrag(recycler)

    recycler.adapter = adapter
    Repo.refresh(50).subscribe { it ->
      adapter.data = it
      adapter.notifyDataSetChanged()
    }
  }


  private fun setDrag(recyclerView: RecyclerView) {
    val helper = ItemTouchHelper(DragCallback())
    helper.attachToRecyclerView(recyclerView)
  }

}