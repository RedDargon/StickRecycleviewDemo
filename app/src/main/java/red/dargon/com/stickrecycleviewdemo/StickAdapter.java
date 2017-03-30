package red.dargon.com.stickrecycleviewdemo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * recycleview 适配器
 * Created by RedDargon on 2017/3/27.
 */

class StickAdapter extends RecyclerView.Adapter<StickAdapter.ItemViewHolder> {
    private final RecyclerView recycleview;
    private LayoutInflater inflater;
    private List<ItemBean> list;
    private final LinearLayoutManager manager;
    private boolean move;
    private int index;

    StickAdapter(Context context, List<ItemBean> list, RecyclerView recyclerView) {
        inflater = LayoutInflater.from(context);
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
        //可以在这里做分割线处理
        manager = new LinearLayoutManager(context);
        this.recycleview = recyclerView;
        recyclerView.setLayoutManager(manager);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //在这里进行第二次滚动
                if (move ){
                    move = false;
                    //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                    int n = index - manager.findFirstVisibleItemPosition();
                    if ( 0 <= n && n < recyclerView.getChildCount()){
                        //获取要置顶的项顶部离RecyclerView顶部的距离
                        int top = recyclerView.getChildAt(n).getTop();
                        //最后的移动
                        recyclerView.smoothScrollBy(0, top);
                    }
                }
            }
        });
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.ui_item, null));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ItemBean itemBean = list.get(position);
        holder.tvName.setText(itemBean.getName());
        holder.ivHead.setImageResource(itemBean.getDrawableId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void scollToPosition(int n) {
        this.index = n ;
        //拿到当前屏幕可见的第一个position跟最后一个postion
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        //区分情况
        if (n <= firstItem ){
            //当要置顶的项在当前显示的第一个项的前面时
            recycleview.smoothScrollToPosition(n);
        }else if ( n <= lastItem ){
            //当要置顶的项已经在屏幕上显示时
            int top = recycleview.getChildAt(n - firstItem).getTop();
            recycleview.smoothScrollBy(0,top);
        }else{
            //当要置顶的项在当前显示的最后一项的后面时
            recycleview.smoothScrollToPosition(n);
            //这里这个变量是用在RecyclerView滚动监听里面的
            move = true;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivHead;

        ItemViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            ivHead = (ImageView) itemView.findViewById(R.id.ivHead);
        }
    }
}
