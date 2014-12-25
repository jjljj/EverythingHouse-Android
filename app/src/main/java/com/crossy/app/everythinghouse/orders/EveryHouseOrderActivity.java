package com.crossy.app.everythinghouse.orders;

import android.app.ActionBar;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.utils.Result;
import com.crossy.app.everythinghouse.utils.ViewUtil;
import com.crossy.app.everythinghouse.utils.launch.TabHostActivity;
import com.crossy.app.everythinghouse.utils.pulltorefresh.library.PullToRefreshBase;
import com.crossy.app.everythinghouse.utils.pulltorefresh.library.PullToRefreshListView;
import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

public class EveryHouseOrderActivity extends Activity {

    private PullToRefreshListView listView;
    private EveryHouseOrderListAdapter listAdapter;
    private List<EveryHouseOrderObject> orderObjects = new ArrayList<EveryHouseOrderObject>();
    private EveryHouseOrderProvider orderProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_house_order);

        initView();

        orderProvider = new EveryHouseOrderProvider(this);

        refreshList();

        registerListener();

        initActionBar();
    }

    @Override
    protected void onResume(){
        super.onResume();
        initActionBar();
    }

    private void initView(){
        listView = (PullToRefreshListView)findViewById(R.id.listViewOrder);

    }

    private void refreshList(){
        new AsyncTask<Void,Void,Result>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Result doInBackground(Void... voids) {
                return orderProvider.getOrderList(orderObjects);
            }

            @Override
            protected void onPostExecute(Result result) {
                super.onPostExecute(result);
                if(result.isSuccess()){
                    listAdapter = new EveryHouseOrderListAdapter(EveryHouseOrderActivity.this,
                            R.layout.item_every_house_order_list,orderObjects);
                    listView.setAdapter(listAdapter);
                }else{
                    Toast.makeText(EveryHouseOrderActivity.this,result.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private void loadMoreList(){
        new AsyncTask<Void,Void,Result>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Result doInBackground(Void... voids) {
                return orderProvider.getOrderList(orderObjects);
            }

            @Override
            protected void onPostExecute(Result result) {
                super.onPostExecute(result);
                if(result.isSuccess()){
                    listAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(EveryHouseOrderActivity.this,result.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private void registerListener(){
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                loadMoreList();
            }
        });
    }

    private void initActionBar(){
        ActionBar actionBar = TabHostActivity.actionBar;
        ViewUtil.customizeActionBar(actionBar, R.layout.actionbar_every_house_order);
        View view = actionBar.getCustomView();
    }
}
