package com.capsule.sample.func.empty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.capsule.recy.click.EmptyClickListener;
import com.capsule.recy.decor.EmptyDecor;
import com.capsule.sample.R;
import com.capsule.sample.base.BaseActivity;

/**
 * Created by wusheng on 2017/9/2.
 */

public class EmptyViewActivity extends BaseActivity {

  private RecyclerView recyclerView;
  private EmptyAdapter adapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("加载为空");
    recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addItemDecoration(new EmptyDecor(R.layout.layout_empty));
    recyclerView.addOnItemTouchListener(new EmptyClickListener() {
      @Override public void onEmptyClick() {
        Toast.makeText(EmptyViewActivity.this, "点击任意地方刷新...", Toast.LENGTH_SHORT).show();
      }
    });

    adapter = new EmptyAdapter();
    recyclerView.setAdapter(adapter);

  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_empty;
  }
}