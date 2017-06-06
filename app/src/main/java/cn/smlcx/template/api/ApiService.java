package cn.smlcx.template.api;

import cn.smlcx.template.bean.News;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lcx on 2017/5/4.
 */

public interface ApiService {
	@GET("weixin/query")
	Observable<News> getNewsList(@Query("pno") int pno,
								   @Query("ps") int ps,
								   @Query("key") String key,
								   @Query("dtype") String dtype);

}
