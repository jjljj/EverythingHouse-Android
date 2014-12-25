package com.crossy.app.everythinghouse.orders.send;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.utils.Result;
import com.crossy.app.everythinghouse.utils.TimeConvertUtil;
import com.crossy.app.everythinghouse.utils.ViewUtil;

import java.util.Calendar;

public class EveryHouseOrderSendActivity extends Activity {

    private Button buttonPickTime;
    private BootstrapEditText editTextLocation;
    private BootstrapEditText editTextMoney;
    private BootstrapEditText editTextContent;
    private CheckBox checkBoxAnonymous;

    private String time="";
    private String location;
    private String money;
    private String content;
    private boolean isAnonymous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_house_order_send);

        initView();

        registerListener();

        initActionBar();
    }

    private void initView(){
        buttonPickTime = (Button)findViewById(R.id.buttonPickTime);
        editTextLocation = (BootstrapEditText)findViewById(R.id.editTextLocation);
        editTextMoney = (BootstrapEditText)findViewById(R.id.editTextMoney);
        editTextContent = (BootstrapEditText)findViewById(R.id.editTextContent);
        checkBoxAnonymous = (CheckBox)findViewById(R.id.checkboxAnonymous);

        buttonPickTime.setText("0000-00-00 00:00:00(点击修改)");
    }

    private void registerListener(){
        buttonPickTime.setOnClickListener(new View.OnClickListener() {
            private int[] times = new int[5];
            final Calendar c = Calendar.getInstance();
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EveryHouseOrderSendActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                                times[0] = i;
                                times[1] = i2;
                                times[2] = i3;

                                TimePickerDialog timePickerDialog = new TimePickerDialog(EveryHouseOrderSendActivity.this,
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker timePicker, int i, int i2) {
                                                times[3] = i;
                                                times[4] = i2;

                                                if(TimeConvertUtil.getTimeDistance(TimeConvertUtil.getTimeInString(times))<0){
                                                    new AlertDialog.Builder(EveryHouseOrderSendActivity.this)
                                                            .setMessage("时间已经过去了哦")
                                                            .show();
                                                }else{
                                                    time = TimeConvertUtil.getTimeInString(times);
                                                    buttonPickTime.setText(time+"(点击修改)");
                                                }


                                            }
                                        },c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),true);
                                timePickerDialog.setTitle("设置时间");
                                timePickerDialog.setButton(TimePickerDialog.BUTTON_POSITIVE,"完成",timePickerDialog);
                                timePickerDialog.show();
                            }
                        },c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle("设置日期");
                datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE,"下一步",datePickerDialog);
                datePickerDialog.show();
            }
        });
    }

    private void initActionBar(){
        ActionBar actionBar = getActionBar();
        ViewUtil.customizeActionBar(actionBar, R.layout.actionbar_every_house_order_send);
        View view = actionBar.getCustomView();
        view.findViewById(R.id.layout_actionbar_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        view.findViewById(R.id.layout_actionbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time.equals("")){
                    Toast.makeText(EveryHouseOrderSendActivity.this,"请选择时间",Toast.LENGTH_SHORT).show();
                    return;
                }
                location = editTextLocation.getText().toString();
                money = editTextMoney.getText().toString();
                content = editTextContent.getText().toString();
                isAnonymous = checkBoxAnonymous.isChecked();

                submitOrder();
            }
        });
    }

    private void submitOrder(){
        new AsyncTask<Void,Void,Result>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Result doInBackground(Void... voids) {
                return new Result(true,"test ok");
            }

            @Override
            protected void onPostExecute(Result result) {
                super.onPostExecute(result);
                if(result.isSuccess()){
                    finish();
                }else{
                    Toast.makeText(EveryHouseOrderSendActivity.this,result.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

}
