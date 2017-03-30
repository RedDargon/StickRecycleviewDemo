package red.dargon.com.stickrecycleviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StickRecycleViewActivity extends AppCompatActivity {
    private Handler handler = new Handler(Looper.myLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stick_recycle_view);
        initView();
    }

    private void initView() {
        final RecyclerView recycleview = (RecyclerView) findViewById(R.id.recycleview);
        final StickAdapter stickAdapter = new StickAdapter(this, initData(), recycleview);
        Button btn = (Button) findViewById(R.id.btn);
        recycleview.setAdapter(stickAdapter);
//        stickAdapter.notifyDataSetChanged();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stickAdapter.scollToPosition(15);
            }
        });
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },5000);
    }

    private List<ItemBean> initData() {
        List<ItemBean> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i == 4) {
                list.add(new ItemBean("失了智的狗管理", R.drawable.ic_music));
            }
            list.add(new ItemBean(String.format(Locale.getDefault(), "狗子%d号", i), R.drawable.ic_recommand));
        }
        return list;
    }


}
