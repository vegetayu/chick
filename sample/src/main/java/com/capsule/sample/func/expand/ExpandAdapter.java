package com.capsule.sample.func.expand;

import android.view.View;
import com.capsule.recy.Adapter;
import com.capsule.recy.ViewHolder;
import com.capsule.sample.R;

/**
 * Created by wusheng on 2017/9/9.
 */

public class ExpandAdapter extends Adapter<ArtBean,ViewHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_expand);
  }

  @Override protected void convert(ViewHolder holder, ArtBean item) {
    holder.setImageResource(R.id.icon, item.getIconRes())
        .setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent())
        .setVisibility(R.id.invi, item.isExpand() ? View.VISIBLE : View.GONE);


  }
}