package com.example.wdftm.cmrdb_project;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class Category extends AppCompatActivity {

    SQLite OpenHelper;
    int food,cloth,live,walk,play;
    PieChart mPiechart;
    private String[] Stars = new String[]{"食", "衣", "住", "行", "樂"};
    private int[] number;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        OpenHelper = new SQLite(this);

        int food = OpenHelper.GetcCategory_TotalPrice_Month("食");
        int cloth = OpenHelper.GetcCategory_TotalPrice_Month("衣");
        int live = OpenHelper.GetcCategory_TotalPrice_Month("住");
        int walk = OpenHelper.GetcCategory_TotalPrice_Month("行");
        int play = OpenHelper.GetcCategory_TotalPrice_Month("樂");
        number = new int[]{food,cloth,live,walk,play};
        mPiechart = (PieChart) findViewById(R.id.mPieChart);
        initView();


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
        mPiechart.setDescriptionPosition(950, 950);
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
}

