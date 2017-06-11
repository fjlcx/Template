package cn.smlcx.template.mvp.presenter;

import javax.inject.Inject;

import cn.smlcx.template.base.BasePresenter;
import cn.smlcx.template.bean.News;
import cn.smlcx.template.bean.PageBean;
import cn.smlcx.template.di.scope.ActivityScope;
import cn.smlcx.template.mvp.model.NewsListModel;
import cn.smlcx.template.mvp.view.ViewContract;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

/**
 * Created by lcx on 2017/6/6.
 */
@ActivityScope
public class NewsListPresenter extends BasePresenter<NewsListModel,ViewContract.NewsListView>{
	private Subscription subscribe;
	@Inject
	public NewsListPresenter(NewsListModel model,ViewContract.NewsListView view) {
		this.mModel = model;
		this.mView = view;
	}

	public void getNewsList(int pno, int ps, String key, String dtype) {
		subscribe = mModel.getNewsListModel(pno, ps, key, dtype)
				.doOnSubscribe(new Action0() {
					@Override
					public void call() {
						mView.showLoding();
					}
				})
				.subscribe(new Subscriber<PageBean<News>>() {
					@Override
					public void onCompleted() {
						mView.hideLoding();
					}

					@Override
					public void onError(Throwable e) {
						mView.fail(e.getMessage());
					}

					@Override
					public void onNext(PageBean<News> result) {
						mView.success(result.getList());
					}
				});
		addSubscribe(subscribe);
	}

}
