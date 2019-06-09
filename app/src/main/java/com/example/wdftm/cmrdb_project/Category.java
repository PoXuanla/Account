package com.example.wdftm.cmrdb_project;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Category extends AppCompatActivity {

    SQLite OpenHelper;
    int food,cloth,live,walk,play,year,month;
    PieChart mPiechart;
    Button nextright,nextleft;
    private String[] Stars = new String[]{"食", "衣", "住", "行", "樂"};
    private int[] number;
    String currentDay,SQLday;
    TextView day,percent;
    float allprice;
    DecimalFormat df=new DecimalFormat("#.##");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        mPiechart = (PieChart) findViewById(R.id.mPieChart);
        nextright = (Button)findViewById(R.id.nextright);
        nextleft = (Button)findViewById(R.id.nextleft);
        day = (TextView)findViewById(R.id.day);
        percent = (TextView)findViewById(R.id.percent);
        OpenHelper = new SQLite(this);

        Date date = new Date();
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyM");
        currentDay = bartDateFormat.format(date);


         year = Integer.parseInt(currentDay.substring(0,4));
         month = Integer.parseInt(currentDay.substring(4,5));
        SQLday = year+"/"+month+"%";
         day.setText(year+" 年 "+month +" 月");

      //  str = str + "%";
       // Toast.makeText(getApplicationContext(),month,Toast.LENGTH_LONG).show();
         food = OpenHelper.GetcCategory_TotalPrice_Month(SQLday,"食");
         cloth = OpenHelper.GetcCategory_TotalPrice_Month(SQLday,"衣");
         live = OpenHelper.GetcCategory_TotalPrice_Month(SQLday,"住");
         walk = OpenHelper.GetcCategory_TotalPrice_Month(SQLday,"行");
         play = OpenHelper.GetcCategory_TotalPrice_Month(SQLday,"樂");
        number = new int[]{food,cloth,live,walk,play};
        initView();
         allprice= food+cloth+live+walk+play;

        percent.setText("食 :　" + df.format(((float)food/allprice)*100)+"%\n"+
                "衣 :　" + df.format(((float)cloth/allprice)*100)+"%\n"+
                "住 :　" + df.format(((float)live/allprice)*100)+"%\n"+
                "行 :　" + df.format(((float)walk/allprice)*100)+"%\n"+
                "樂 :　" + df.format(((float)play/allprice)*100)+"%\n");
        nextright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(month + 1 ==13){
                    year += 1;
                    month = 1;
                }
                else{
                    month += 1;
                }
               changeChart();
            }
        });
        nextleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(month - 1 ==0){
                    year -= 1;
                    month = 12;
                }
                else{
                    month -= 1;
                }
                changeChart();
            }
        });



    }
    private void initView() {
        setData();
        //设置X轴的动画
        mPiechart.animateX(1400);
        //使用百分比
        //   mPiechart.setUsePercentValues(true);
        //设置图列可见
        mPiechart.getLegend().setEnabled(true);
        //设置图列标识的大小
        mPiechart.getLegend().setFormSize(14);
        //设置图列标识文字的大小
        mPiechart.getLegend().setTextSize(14);
        //设置图列的位置
        mPiechart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        //设置图列标识的形状
        mPiechart.getLegend().setForm(Legend.LegendForm.CIRCLE);
        //设置图表描述
        //mPiechart.setDescription("这是一个饼图");
        //设置图表描述的字体
        mPiechart.setDescriptionTextSize(14);
        //设置图表描述的位置
        mPiechart.setDescriptionPosition(0, 0);
        //设置是否可转动
        mPiechart.setRotationEnabled(true);
    }

    /**
     * 图表设置数据
     */
    private void setData() {
        //设置标题
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < Stars.length; i++) {
            titles.add(Stars[i]);
        }
        ArrayList<Entry> entrys = new ArrayList<>();
        for (int i = 0; i < number.length; i++) {
            entrys.add(new Entry(number[i], i));
        }
        //饼图数据集
        PieDataSet dataset = new PieDataSet(entrys,"");
        //设置饼状图Item之间的间隙
        // dataset.setSlicespace(3f);
        //饼图Item被选中时变化的距离
        dataset.setSelectionShift(10f);
        //颜色填充
        dataset.setColors(new int[]{Color.rgb(205, 189, 255), Color.rgb(154, 87, 255), Color.rgb(120, 0, 189), Color.rgb(153, 0, 153), Color.rgb(117, 0, 117)});
        //数据填充
        PieData piedata = new PieData(titles, dataset);
        //设置饼图上显示数据的字体大小
        piedata.setValueTextSize(15);
        mPiechart.setData(piedata);
    }
    private void changeChart(){
        SQLday = year+"/"+month+"%";
        Toast.makeText(getApplicationContext(),SQLday,Toast.LENGTH_LONG).show();
        day.setText(year+" 年 "+month +" 月");
        food = OpenHelper.GetcCategory_TotalPrice_Month(SQLday,"食");
        cloth = OpenHelper.GetcCategory_TotalPrice_Month(SQLday,"衣");
        live = OpenHelper.GetcCategory_TotalPrice_Month(SQLday,"住");
        walk = OpenHelper.GetcCategory_TotalPrice_Month(SQLday,"行");
        play = OpenHelper.GetcCategory_TotalPrice_Month(SQLday,"樂");
        //Toast.makeText(getApplicationContext(),food,Toast.LENGTH_LONG).show();
        number = new int[]{food,cloth,live,walk,play};
        initView();
        allprice= food+cloth+live+walk+play;
        percent.setText("食 :　" + df.format(((float)food/allprice)*100)+"%\n"+
                "衣 :　" + df.format(((float)cloth/allprice)*100)+"%\n"+
                "住 :　" + df.format(((float)live/allprice)*100)+"%\n"+
                "行 :　" + df.format(((float)walk/allprice)*100)+"%\n"+
                "樂 :　" + df.format(((float)play/allprice)*100)+"%\n");
    }
}

