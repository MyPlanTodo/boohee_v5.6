package com.boohee.user.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boohee.myview.DatePickerWheelView;
import com.boohee.one.R;
import com.boohee.one.mine.McInitActivity;
import com.boohee.record.PickerScrollListener;
import com.boohee.utils.DateHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserMcComeView extends FrameLayout {
    Context             ctx;
    DatePickerWheelView wheelView;

    public UserMcComeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public UserMcComeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserMcComeView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.ctx = getContext();
        addView(LayoutInflater.from(this.ctx).inflate(R.layout.nz, null));
        setUserProperty();
        setBottomPicker();
    }

    private void setUserProperty() {
        ((TextView) findViewById(R.id.user_property_text)).setText(R.string.mc_last_come);
    }

    public void setBottomPicker() {
        this.wheelView = new DatePickerWheelView(this.ctx, DateHelper.parseString(new
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System
                .currentTimeMillis()))));
        McInitActivity.defaultMcCome = this.wheelView.getYear();
        this.wheelView.setPickNumListener(new PickerScrollListener() {
            public void onScroll() {
                McInitActivity.defaultMcCome = UserMcComeView.this.wheelView.getYear();
            }
        });
        ((LinearLayout) findViewById(R.id.picker_layout)).addView(this.wheelView);
    }

    public String getLastCome() {
        return this.wheelView.getDateString();
    }
}
