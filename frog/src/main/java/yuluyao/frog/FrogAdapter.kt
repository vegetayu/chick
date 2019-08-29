package yuluyao.frog

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

open class FrogAdapter<T>(private val layoutId: Int = 0) :
    RecyclerView.Adapter<FrogHolder>() {
  var data: ArrayList<T> = arrayListOf()

  override fun getItemCount(): Int = data.size
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrogHolder {
    val inflater = LayoutInflater.from(parent.context)
    val layout = types?.get(viewType) ?: layoutId
    val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layout, parent, false)
    return if (binding != null) {
      FrogHolder(binding.root)
    } else {
      FrogHolder(inflater.inflate(layout, parent, false))
    }
  }

  override fun onBindViewHolder(holder: FrogHolder, position: Int) {
    val item = data[position]
    holder.bind(item)
  }

  private var types: HashMap<Int, Int>? = null
  fun initItemViewType(typeArray: IntArray, layoutArray: IntArray) {
    types = hashMapOf()
    for (i in 0 until typeArray.size) {
      types!![typeArray[i]] = layoutArray[i]
    }
  }

}

class FrogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val binding: ViewDataBinding? = DataBindingUtil.getBinding(itemView)
  fun bind(any: Any?) {
    binding?.setVariable(BR.item, any)
    binding?.executePendingBindings()
  }
}

@BindingAdapter("src")
fun imageResource(v: ImageView, res: Int) {
  v.setImageResource(res)
}

