package cn.smlcx.template.global;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import cn.smlcx.template.di.component.AppComponent;
import cn.smlcx.template.di.component.DaggerAppComponent;
import cn.smlcx.template.di.module.AppModule;

/**
 * Created by lcx on 2017/6/5.
 */

public class TemplateApplication extends Application {
	private static TemplateApplication instance;
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		LeakCanary.install(this);
		getAppComponent();
	}

	public static TemplateApplication getInstance() {
		return instance;
	}

	// 初始化dagger2
	public AppComponent getAppComponent(){
		return DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.build();
	}
}
