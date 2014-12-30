package com.crossy.app.everythinghouse.orders;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.IconTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.utils.DataUtil;
import com.crossy.app.everythinghouse.utils.HttpUtil;
import com.crossy.app.everythinghouse.utils.Result;
import com.crossy.app.everythinghouse.utils.api.API_EVERYTHING_HOUSE;
import com.daimajia.swipe.SwipeLayout;

import org.w3c.dom.Text;

import java.util.List;


/**
 * Created by ljj on 2014/12/25.
 */
public class EveryHouseOrderListAdapter extends ArrayAdapter<EveryHouseOrderObject> {

    private final String dealUrl = DataUtil.getString(API_EVERYTHING_HOUSE.SPF_NAME, API_EVERYTHING_HOUSE.SPF_KEY_WEB_HOST, "")+"/deal/";

    private Context context;
    private int resourceId;
    private List<EveryHouseOrderObject> orderObjects;

    public EveryHouseOrderListAdapter(Context context, int resource, List<EveryHouseOrderObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resourceId = resource;
        this.orderObjects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resourceId, null);
            holder.swipeLayout = (SwipeLayout)convertView.findViewById(R.id.swipeLayoutOrder);
            holder.textViewTrade = (TextView)convertView.findViewById(R.id.textViewTrade);
            holder.textViewContact = (TextView)convertView.findViewById(R.id.textViewContact);

            holder.textViewCreator = (TextView)convertView.findViewById(R.id.textViewCreator);
            holder.textViewState = (TextView)convertView.findViewById(R.id.textViewState);
            holder.textViewTime = (IconTextView)convertView.findViewById(R.id.textViewTime);
            holder.textViewLocation = (IconTextView)convertView.findViewById(R.id.textViewLocation);
            holder.textViewMoney = (IconTextView)convertView.findViewById(R.id.textViewMoney);
            holder.textViewContent = (IconTextView)convertView.findViewById(R.id.textViewContent);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final EveryHouseOrderObject temp = orderObjects.get(position);
        holder.textViewCreator.setText("投条人："+temp.getUsername());
        if(temp.getState().equals("0")){
            holder.textViewState.setText("状态："+"未接单");
        }else if(temp.getState().equals("1")){
            holder.textViewState.setText("状态："+"处理中");
        }else if(temp.getState().equals("2")){
            holder.textViewState.setText("状态："+"已完成");
        }
        holder.textViewTime.setText("{fa-calendar}时间："+temp.getTime());
        holder.textViewLocation.setText("{fa-map-marker} 地点："+temp.getPosition());
        holder.textViewMoney.setText("{fa-angellist}赏金："+temp.getNicecard());
        holder.textViewContent.setText("{fa-paperclip}内容："+temp.getContent());

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);//set show mode.
        holder.swipeLayout.setDragEdge(SwipeLayout.DragEdge.Right);//set drag edge.
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout swipeLayout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout swipeLayout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });
        holder.textViewTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void,Void,Result>(){
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                    }

                    @Override
                    protected Result doInBackground(Void... voids) {
                        HttpUtil httpUtil = new HttpUtil();
                        httpUtil.get(dealUrl+temp.getId(),true);
                        if(httpUtil.getHttpResponse().getStatusLine().getStatusCode() == 200){
                            return new Result(true,"接单成功");
                        }else{
                            return new Result(false,"接单失败");
                        }
                    }

                    @Override
                    protected void onPostExecute(Result result) {
                        super.onPostExecute(result);
                        Toast.makeText(context,result.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }.execute();
            }
        });
        holder.textViewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return convertView;
    }

    private class ViewHolder{
        SwipeLayout swipeLayout;
        TextView textViewTrade;
        TextView textViewContact;

        TextView textViewCreator;
        TextView textViewState;
        IconTextView textViewTime;
        IconTextView textViewLocation;
        IconTextView textViewMoney;
        IconTextView textViewContent;
    }
}
