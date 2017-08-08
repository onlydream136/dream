package com.xiao.android5;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private TextInputLayout username;
    private TextInputLayout password;
    private TabLayout tab;
    private ViewPager vp;
    private Button fab;
    private myAdapter adapter;

    private String[] datas = {"嘻嘻","哈哈","嘿嘿","十分骄傲会计法师打发","呵呵","啊啊","哦哦"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        username = (TextInputLayout) findViewById(R.id.username);
        password = (TextInputLayout) findViewById(R.id.password);
        EditText editText = username.getEditText();
        editText.addTextChangedListener(this);
        String abc = "abc_123";
        tab = (TabLayout) findViewById(R.id.tab);
//        tab.addTab(tab.newTab().setText("哈哈"));
//        tab.addTab(tab.newTab().setText("嘿嘿"));
//        tab.addTab(tab.newTab().setText("嘻嘻"));
//        tab.addTab(tab.newTab().setText("哈哈"));
//        tab.addTab(tab.newTab().setText("嘿嘿"));
//        tab.addTab(tab.newTab().setText("嘻嘻"));
//        tab.addTab(tab.newTab().setText("哈哈"));
//        tab.addTab(tab.newTab().setText("嘿嘿"));
//        tab.addTab(tab.newTab().setText("嘻dgdfgdfgdfgsdgf嘻"));
//        tab.addTab(tab.newTab().setText("哈哈"));
//        tab.addTab(tab.newTab().setText("嘿嘿"));
//        tab.addTab(tab.newTab().setText("嘻嘻"));
//        tab.addTab(tab.newTab().setText("哈哈"));
//        tab.addTab(tab.newTab().setText("嘿嘿"));
//        tab.addTab(tab.newTab().setText("嘻嘻"));
        vp = (ViewPager) findViewById(R.id.vp);
        adapter = new myAdapter(this,datas);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);



    }

    @Override
    public void onClick(View v) {
        Log.d("print","----->"+"点击了");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 10) {
            username.setError("用户名过长");
            username.setErrorEnabled(true);
        } else {
            username.setErrorEnabled(false);
        }
    }


    public class myAdapter extends PagerAdapter{
        private Context context;
        private List<TextView> texts;
        public myAdapter(Context context,String[] datas) {
            this.context = context;
            texts = new ArrayList<>();
            for (int i = 0; i < datas.length; i++) {
                TextView textView = new TextView(context);
                textView.setTextSize(50);
                textView.setText(datas[i]);
                textView.setTextColor(Color.parseColor("#888888"));
                textView.setGravity(Gravity.CENTER);
                texts.add(textView);
            }
        }

        @Override
        public int getCount() {
            return texts.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(texts.get(position));
            return texts.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(texts.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return texts.get(position).getText().toString();
        }
    }
}
