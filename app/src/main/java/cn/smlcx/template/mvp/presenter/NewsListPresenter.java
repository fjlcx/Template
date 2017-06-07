package cn.smlcx.template.mvp.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import cn.smlcx.template.base.BasePresenter;
import cn.smlcx.template.bean.News;
import cn.smlcx.template.di.scope.ActivityScope;
import cn.smlcx.template.mvp.model.NewsListModel;
import cn.smlcx.template.mvp.view.ViewContract;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by lcx on 2017/6/6.
 */
@ActivityScope
public class NewsListPresenter extends BasePresenter<NewsListModel,ViewContract.NewsListView>{

	@Inject
	public NewsListPresenter(NewsListModel model,ViewContract.NewsListView view) {
		this.mModel = model;
		this.mView = view;
	}

	public void getNewsList(int pno, int ps, String key, String dtype) {
		Subscription subscribe = mModel.getNewsListModel(pno, ps, key, dtype)
				.subscribe(new Subscriber<List<News>>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onNext(List<News> result) {
						Log.d(TAG, "onNext: "+result.size());
					}
				});
		addSubscribe(subscribe);
	}
}
