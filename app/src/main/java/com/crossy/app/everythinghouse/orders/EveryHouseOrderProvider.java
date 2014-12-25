package com.crossy.app.everythinghouse.orders;

import android.content.Context;

import com.crossy.app.everythinghouse.utils.Result;

import java.util.List;

/**
 * Created by ljj on 2014/12/25.
 */
public class EveryHouseOrderProvider {

    private Context context;

    public EveryHouseOrderProvider(Context context){
        this.context = context;
    }

    public Result getOrderList(List<EveryHouseOrderObject> orderObjects){
        if(orderObjects==null){
            return new Result(false,"list传递了空指针");
        }

        return new Result(false,"meiyou");
    }
}
