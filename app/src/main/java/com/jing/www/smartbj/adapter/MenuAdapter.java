package com.jing.www.smartbj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jing.www.smartbj.R;
import com.jing.www.smartbj.activity.MainActivity;
import com.jing.www.smartbj.base.BaseFragment;
import com.jing.www.smartbj.bean.NewsCenterBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/7.
 */
public class MenuAdapter extends RecyclerView.Adapter {
    public Context context;
    public List<NewsCenterBean.NewsCenterMenuBean> NewsCenterMenuBeanList;
    private int selectedPosition ;
    //private NewsCenterBean.NewsCenterMenuBean newsCenterMenuBean;

    public MenuAdapter(Context context,List <NewsCenterBean.NewsCenterMenuBean>NewsCenterMenuBeanList) {
        this.context = context;
        this.NewsCenterMenuBeanList=NewsCenterMenuBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //添加子条目布局,上下文的传入和数据的传入都需要调用者传入构造中

        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        //将布局传给ViewHolder
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
    ViewHolder viewHolder = (ViewHolder) holder;
        final NewsCenterBean.NewsCenterMenuBean newsCenterMenuBean = NewsCenterMenuBeanList.get(position);
       viewHolder.tvMenuTitle.setText(newsCenterMenuBean.getTitle());
        if (selectedPosition == position) {

            viewHolder.ivArrow.setImageResource(R.drawable.menu_arr_select);
            viewHolder.tvMenuTitle.setTextColor(Color.RED);
        }else{

            viewHolder.ivArrow.setImageResource(R.drawable.menu_arr_normal);
            viewHolder.tvMenuTitle.setTextColor(Color.WHITE);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != position) {
                    selectedPosition = position;
                    notifyDataSetChanged();
                    //修改对应的标题
                   BaseFragment baseFragment =((MainActivity)context).getCurrentTabFragment();
                    baseFragment.setTitle(newsCenterMenuBean.getTitle());
                }
                ((MainActivity)context).slidingMenu.toggle();


            }
        });
    }

    @Override
    public int getItemCount() {
        return NewsCenterMenuBeanList !=null? NewsCenterMenuBeanList.size():0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_arrow)
        ImageView ivArrow;
        @BindView(R.id.tv_menu_title)
        TextView tvMenuTitle;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
