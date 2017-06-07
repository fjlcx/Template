package cn.smlcx.template.mvp.model;

import java.util.List;

import javax.inject.Inject;

import cn.smlcx.template.api.ApiEngine;
import cn.smlcx.template.api.RxHelper;
import cn.smlcx.template.base.BaseModel;
import cn.smlcx.template.bean.News;
import cn.smlcx.template.di.scope.ActivityScope;
import rx.Observable;

/**
 * Created by lcx on 2017/6/5.
 */
@ActivityScope
public class NewsListModel implements BaseModel{
	@Inject
	public NewsListModel() {
	}

	public Observable<List<News>> getNewsListModel(int pno, int ps, String key, String dtype){
		return ApiEngine.getInstance().getApiService().getNewsList(pno,ps,key,dtype)
				.compose(RxHelper.<News>handleResult());
	}
}
