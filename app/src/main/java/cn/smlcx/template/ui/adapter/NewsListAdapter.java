package cn.smlcx.template.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.smlcx.template.R;
import cn.smlcx.template.bean.News;

/**
 * Created by lcx on 2017/6/8.
 */

public class NewsListAdapter extends BaseQuickAdapter<News,BaseViewHolder> {
	public NewsListAdapter(@Nullable List data) {
		super(R.layout.item_news, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, News item) {
		helper.setText(R.id.c_title,item.getTitle())
			.setText(R.id.c_content,"来源:"+item.getSource());
		Glide.with(mContext).load(item.getFirstImg()).crossFade().into((ImageView) helper.getView(R.id.c_img));
	}
}
