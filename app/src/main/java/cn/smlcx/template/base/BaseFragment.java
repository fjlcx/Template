package cn.smlcx.template.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smlcx.template.R;
import cn.smlcx.template.widget.EmptyLayout;

/**
 * Created by lcx on 2017/5/5.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
	@Nullable
	@BindView(R.id.empty_layout)
	EmptyLayout mEmptyLayout;
	protected P mPresenter;
	public Context mContext;
	public View view;

	private Toolbar mToolbar;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.base_layout, container, false);
		ButterKnife.bind(getActivity());
		init();
		initViews();
		mPresenter = initPresenter();
		initData();
		return view;
	}

	private void init() {
		mContext = getActivity();
	}

	@LayoutRes
	protected abstract int attachLayoutRes();

	/**
	 * 初始化视图控件
	 */
	protected abstract void initViews();

	/**
	 * 初始化presenter
	 */
	protected abstract P initPresenter();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	@Override
	public void showLoding() {
		if (mEmptyLayout != null) {
			mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
		}
	}

	@Override
	public void hideLoding() {
		if (mEmptyLayout != null) {
			mEmptyLayout.hide();
		}
	}

	@Override
	public void showErr(String err) {
		if (mEmptyLayout != null) {
			mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
		}
	}

	@Override
	public void showNonData(String msg) {
		if (mEmptyLayout != null) {
			mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_DATA);
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
}
