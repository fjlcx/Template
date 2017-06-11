package cn.smlcx.template.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.smlcx.template.R;
import cn.smlcx.template.base.BaseActivity;
import cn.smlcx.template.bean.News;
import cn.smlcx.template.di.component.DaggerNewsComponent;
import cn.smlcx.template.di.module.NewsModule;
import cn.smlcx.template.mvp.presenter.NewsListPresenter;
import cn.smlcx.template.mvp.view.ViewContract;
import cn.smlcx.template.ui.adapter.NewsListAdapter;

/**
 * Created by lcx on 2017/6/5.
 */

public class HomeActivity extends BaseActivity<NewsListPresenter> implements ViewContract.NewsListView {
	protected final String TAG = this.getClass().getSimpleName();
	@BindView(R.id.rlv_news)
	RecyclerView mRlvNews;
	@BindView(R.id.swiperefresh)
	SwipeRefreshLayout mSwiperefresh;
	private NewsListAdapter mAdapter;
	private List<News> mDatas = new ArrayList<News>();
	@Override
	protected int attachLayoutRes() {
		return R.layout.activity_home;
	}

	@Override
	protected void initViews() {
		getToolBar().setTitle("微信精选")
				.setDisplayHomeAsUpEnabled(false)
				.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						String msg = "";
						switch (item.getItemId()) {
							case R.id.action_create:
								msg += "Click create";
								break;
							case R.id.action_add:
								msg += "Click add";
								break;
						}
						if(!msg.equals("")) {
							Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
						}
						return false;
					}
				});
		mAdapter = new NewsListAdapter(mDatas);
		mRlvNews.setAdapter(mAdapter);
		mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
				Intent intent = new Intent(HomeActivity.this,NewsDetailActivity.class);
				intent.putExtra("url",mDatas.get(position).getUrl());
				intent.putExtra("title",mDatas.get(position).getTitle());
				startActivity(intent);
			}
		});
	}

	@Override
	protected void initInject() {
		DaggerNewsComponent.builder()
				.newsModule(new NewsModule(this))
				.build()
				.inject(this);
	}

	@Override
	protected void initData() {
		mPresenter.getNewsList(1, 50, "d975b5fe029c0691fe5d683cb68b86ac", "json");
	}


	@Override
	public void success(List<?> list) {
		mDatas.addAll((List<News>)list);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void fail(String msg) {
		showErr(msg);
	}

	/** 创建菜单 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main,menu);
		return true;
	}
}
