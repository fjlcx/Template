package cn.smlcx.template.ui.activity;

import android.os.Handler;
import android.widget.ImageView;

import butterknife.BindView;
import cn.smlcx.template.R;
import cn.smlcx.template.base.BaseActivity;
import cn.smlcx.template.utils.ImageLoader;

/**
 * Created by lcx on 2017/6/5.
 */

public class SplashActivity extends BaseActivity {

	@BindView(R.id.iv_splsh)
	ImageView mIvSplsh;

	@Override
	protected int attachLayoutRes() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {
		mIvSplsh.setImageBitmap(ImageLoader.load(mContext, R.mipmap.splash));
		getToolBar().hide();
	}

	@Override
	protected void initData() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(HomeActivity.class);
				finish();
			}
		}, 3000);
	}

	@Override
	protected void initInject() {
	}
}
