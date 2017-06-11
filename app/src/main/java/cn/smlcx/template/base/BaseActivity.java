package cn.smlcx.template.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smlcx.template.R;
import cn.smlcx.template.widget.EmptyLayout;
import cn.smlcx.template.widget.ToolBarSet;


/**
 * Created by lcx on 2017/5/4.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
	@Nullable
	@BindView(R.id.empty_layout)
	EmptyLayout mEmptyLayout;

	private Toolbar mToolbar;

	@Inject
	protected P mPresenter;
	public Context mContext;
	private ToolBarSet mToolBarSet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(attachLayoutRes());
		ButterKnife.bind(this);
		init();
	}

	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		View view = getLayoutInflater().inflate(R.layout.base_layout, null);
		super.setContentView(view);
		initDefaultView(layoutResID);
	}

	private void initDefaultView(int layoutResId) {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		FrameLayout container = (FrameLayout) findViewById(R.id.container);
		View childView = LayoutInflater.from(this).inflate(layoutResId, null);
		container.addView(childView, 0);
	}

	private void init() {
		mContext = this;
		mToolBarSet = new ToolBarSet(mToolbar,this);
		initInject();
		initViews();
		initData();
		/*//当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			//透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			//透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}*/
	}

	/**
	 * 绑定布局文件
	 * @return 布局文件ID
	 */
	@LayoutRes
	protected abstract int attachLayoutRes();

	/**
	 * 初始化视图控件
	 */
	protected abstract void initViews();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 初始化dagger2
	 */
	protected abstract void initInject();

	/**
	 * 获取Toolbar对象
	 * @return
	 */
	public ToolBarSet getToolBar(){
		if(mToolBarSet==null){
			mToolBarSet = new ToolBarSet(mToolbar,this);
		}
		return mToolBarSet;
	}

	/**
	 * 加载中
	 */
	@Override
	public void showLoding() {
		if (mEmptyLayout != null) {
			mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
		}
	}
	/**
	 * 隐藏加载
	 */
	@Override
	public void hideLoding() {
		if (mEmptyLayout != null) {
			mEmptyLayout.hide();
		}
	}

	/**
	 * 显示错误信息
	 */
	@Override
	public void showErr(String err) {
		if (mEmptyLayout != null) {
			mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
		}
	}
	/**
	 * 无数据
	 */
	@Override
	public void showNonData(String msg) {
		if (mEmptyLayout != null) {
			mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_DATA);
		}
	}

	/*设置activity之间的切换动画*/
	/*@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_right_exit);
	}
	*/

	/**
	 * 页面跳转
	 * @param clz
	 */
	@Override
	public void startActivity(Class<?> clz) {
		startActivity(new Intent(BaseActivity.this,clz));
	}

	/**
	 * 携带数据的页面跳转
	 * @param clz
	 * @param bundle
	 */
	@Override
	public void startActivity(Class<?> clz, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, clz);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/**
	 * 简化Toast
	 * @param msg
	 */
	@Override
	public void showToast(String msg){
		Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
	}


	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPresenter!=null){
			mPresenter.unSubscribe();
		}
	}

}
