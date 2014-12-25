package com.crossy.app.everythinghouse.orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.IconTextView;
import android.widget.TextView;

import com.crossy.app.everythinghouse.R;
import com.daimajia.swipe.SwipeLayout;

import org.w3c.dom.Text;

import java.util.List;


/**
 * Created by ljj on 2014/12/25.
 */
public class EveryHouseOrderListAdapter extends ArrayAdapter<EveryHouseOrderObject> {

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

        EveryHouseOrderObject temp = orderObjects.get(position);
        holder.textViewCreator.setText("投条人："+"XXX");
        holder.textViewState.setText("状态："+"XXXX");
        holder.textViewTime.setText("{fa-calendar}时间："+"0000-00-00 00:00:00");
        holder.textViewLocation.setText("{fa-map-marker} 地点："+"XXX");
        holder.textViewMoney.setText("{fa-angellist}赏金："+"XX");
        holder.textViewContent.setText("{fa-paperclip}内容："+"这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容");

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
