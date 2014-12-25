package com.crossy.app.everythinghouse.orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.crossy.app.everythinghouse.R;
import com.daimajia.swipe.SwipeLayout;

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

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


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
        return convertView;
    }

    private class ViewHolder{
        SwipeLayout swipeLayout;
    }
}
