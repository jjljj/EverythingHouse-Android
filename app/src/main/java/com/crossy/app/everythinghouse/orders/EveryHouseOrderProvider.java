package com.crossy.app.everythinghouse.orders;

import android.content.Context;
import android.util.Log;

import com.crossy.app.everythinghouse.utils.DataUtil;
import com.crossy.app.everythinghouse.utils.HttpUtil;
import com.crossy.app.everythinghouse.utils.Result;
import com.crossy.app.everythinghouse.utils.api.API_EVERYTHING_HOUSE;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by ljj on 2014/12/25.
 */
public class EveryHouseOrderProvider {

    private final String orderListUrl = DataUtil.getString(API_EVERYTHING_HOUSE.SPF_NAME, API_EVERYTHING_HOUSE.SPF_KEY_WEB_HOST, "")+"/androidget";

    private Context context;

    public EveryHouseOrderProvider(Context context){
        this.context = context;
    }

    public Result getOrderList(List<EveryHouseOrderObject> orderObjects){
        if(orderObjects==null){
            return new Result(false,"list传递了空指针");
        }
        Gson gson = new Gson();
        HttpUtil httpUtil = new HttpUtil();
        String json = httpUtil.get(orderListUrl,true);
        Log.i("order",json);
        if(json.equals("{}")){
            return new Result(true,"获取接单失败");
        }else{
            orderObjects.addAll((List<EveryHouseOrderObject>)
                    gson.fromJson(json, new TypeToken<List<EveryHouseOrderObject>>() {
                    }.getType()));
            return new Result(true,"success");
        }

    }
}
