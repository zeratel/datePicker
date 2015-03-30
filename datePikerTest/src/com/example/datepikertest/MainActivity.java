package com.example.datepikertest;

import java.util.Calendar;

import net.simonvt.numberpicker.NumberPicker;
import net.simonvt.numberpicker.NumberPicker.OnValueChangeListener;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private Calendar calendar;
	private int year;
	private int monthInt;
	private int dateIntNow;//输入一个正确的当前日期
	private int dateInt;//留作使用的日期
	private NumberPicker np;
	private NumberPicker month;
	private NumberPicker date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		
		np = (NumberPicker) findViewById(R.id.year);
		
		calendar = Calendar.getInstance();
		year = calendar.getTime().getYear()+1900;
		monthInt = calendar.getTime().getMonth()+1;
		dateIntNow = calendar.getTime().getDate();
		
        np.setMaxValue(year+199);
        np.setMinValue(year-199);
        np.setValue(year);
        np.setFocusable(true);
        np.setFocusableInTouchMode(true);
       
        month = (NumberPicker) findViewById(R.id.month);
        month.setMaxValue(12);
        month.setMinValue(1);
        month.setValue(monthInt);
        month.setFocusable(true);
        month.setFocusableInTouchMode(true);
       
        date = (NumberPicker) findViewById(R.id.date);
        date.setMaxValue(31);
        date.setMinValue(1);
        date.setValue(dateIntNow);
        date.setFocusable(true);
        date.setFocusableInTouchMode(true);
        
        np.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				//先用最弱智的办法，其实可以改用设计模式
				year = newVal;
				setDate();
			}
		});
        month.setOnValueChangedListener(new OnValueChangeListener() {
        	
        	@Override
        	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        		monthInt = newVal;
				setDate();
        	}

        });
        date.setOnValueChangedListener(new OnValueChangeListener() {
        	
        	@Override
        	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        		dateInt = newVal;
        	}
        });
        
	}

	private void setDate() {
		calendar.set(year, monthInt-1, dateIntNow);
		int tempDayOfMonth =calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (date.getValue()>tempDayOfMonth) {
			date.setValue(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		date.setMaxValue(tempDayOfMonth);
	}

}
