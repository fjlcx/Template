package cn.smlcx.template.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import cn.smlcx.template.R;
import cn.smlcx.template.base.BaseActivity;
import cn.smlcx.template.widget.LoadingWebView;

/**
 * Created by lcx on 2017/6/9.
 */

public class NewsDetailActivity extends BaseActivity {
	@BindView(R.id.webView)
	LoadingWebView mWebView;
	private String url;

	@Override
	protected int attachLayoutRes() {
		return R.layout.activity_newsdetail;
	}

	@Override
	protected void initViews() {
		url = getIntent().getExtras().getString("url");
		getToolBar().setTitle(getIntent().getExtras().getString("title"))
				.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						String msg = "";
						switch (item.getItemId()) {
							case R.id.action_add:
								msg += "Click add";
								break;
						}
						if(!msg.equals("")) {
							Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
						}
						return false;
					}
				});;
	}

	@Override
	protected void initData() {
		mWebView.loadMessageUrl(url);
		//mWebView.addProgressBar();
	}

	@Override
	protected void initInject() {

	}

	/** 创建菜单 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_sec,menu);
		return true;
	}
}
