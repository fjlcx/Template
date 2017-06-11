package cn.smlcx.template.api;

import android.util.Log;

import cn.smlcx.template.bean.HttpResult;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lcx on 2017/6/7.
 */

public class RxHelper {
	/**
	 * 对结果进行预处理
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> Observable.Transformer<HttpResult<T>, T> handleResult() {
		return new Observable.Transformer<HttpResult<T>, T>() {
			@Override
			public Observable<T> call(Observable<HttpResult<T>> tObservable) {
				return tObservable.flatMap(new Func1<HttpResult<T>, Observable<T>>() {
					@Override
					public Observable<T> call(HttpResult<T> result) {
						Log.d("code", "call: "+result.getError_code());
						if (result.getError_code() == 0) {
							return createData(result.getResult());
						} else {
							return Observable.error(new ApiException(result.getError_code()));
						}
					}
				}).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());
			}
		};
	}

	/**
	 * 创建成功的数据
	 *
	 * @param data
	 * @param <T>
	 * @return
	 */
	private static <T> Observable<T> createData(final T data) {
		return Observable.create(new Observable.OnSubscribe<T>() {
			@Override
			public void call(Subscriber<? super T> subscriber) {
				try {
					subscriber.onNext(data);
					subscriber.onCompleted();
				} catch (Exception e) {
					subscriber.onError(e);
				}
			}
		});
	}
}
