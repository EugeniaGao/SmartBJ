package com.jing.www.smartbj.fragment;


import android.view.View;

import com.jing.www.smartbj.base.BaseFragment;
import com.jing.www.smartbj.base.BaseLoadNetDataOperator;
import com.jing.www.smartbj.utils.Constant;

/**
 * Created by Administrator on 2017/2/5.
 */

public class NewsCenterTabFragment extends BaseFragment implements BaseLoadNetDataOperator{
    @Override
    public void initTitle() {
        setMenu(true);
        setTitle("新闻中心");
        setType(false);

    }

    @Override
    public View createContent() {
        return null;
    }

    @Override
    public void loadNetData() {
        final  String url = Constant.NEWSCENTER_URL;
        //OkHttpUtils.get() TODO OkhttpUtils 搭建获取数据
    }
}
