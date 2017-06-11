package cn.smlcx.template.mvp.model;

import cn.smlcx.template.api.ApiEngine;
import cn.smlcx.template.api.RetryWithDelay;
import cn.smlcx.template.api.RxHelper;
import cn.smlcx.template.base.BaseModel;
import cn.smlcx.template.bean.News;
import cn.smlcx.template.bean.PageBean;
import cn.smlcx.template.di.scope.ActivityScope;
import rx.Observable;

/**
 * Created by lcx on 2017/6/5.
 */
@ActivityScope
public class NewsListModel implements BaseModel{

	public Observable<PageBean<News>> getNewsListModel(int pno, int ps, String key, String dtype){
		return ApiEngine.getInstance().getApiService().getNewsList(pno,ps,key,dtype)
				.compose(RxHelper.<PageBean<News>>handleResult())
				.retryWhen(new RetryWithDelay(3,1000));
	}
}
