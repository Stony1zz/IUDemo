package com.anxing.android.iu_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class MainActivity extends FragmentActivity implements OnClickListener {
    private FragmentAt fragmentAt;
    private FragmentAuth fragmentAuth;
    private FragmentSpace fragmentSpace;
    private FragmentMore fragmentMore;
    private FrameLayout atFl, authFl, spaceFl, moreFl;
    private ImageView atIv, authIv, spaceIv, moreIv;
    private ImageView toggleImageView, plusImageView;
    private PopupWindow popWindow;
    private DisplayMetrics dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        clickAtBtn();
    }


    private void initView() {
        atFl = (FrameLayout) findViewById(R.id.layout_at);
        authFl = (FrameLayout) findViewById(R.id.layout_auth);
        spaceFl = (FrameLayout) findViewById(R.id.layout_space);
        moreFl = (FrameLayout) findViewById(R.id.layout_more);

        atIv = (ImageView) findViewById(R.id.image_at);
        authIv = (ImageView) findViewById(R.id.image_space);
        spaceIv = (ImageView) findViewById(R.id.image_space);
        moreIv = (ImageView) findViewById(R.id.image_more);

        toggleImageView = (ImageView) findViewById(R.id.toggle_btn);
        plusImageView = (ImageView) findViewById(R.id.plus_btn);

    }


    private void initData() {
        atFl.setOnClickListener(this);
        authFl.setOnClickListener(this);
        spaceFl.setOnClickListener(this);
        moreFl.setOnClickListener(this);

        toggleImageView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.layout_at:
                clickAtBtn();
                break;

            case R.id.layout_auth:
                clickAuthBtn();
                break;

            case R.id.layout_space:
                clickSpaceBtn();
                break;
            case R.id.layout_more:
                clickMoreBtn();
                break;
            case R.id.toggle_btn:
                break;
        }
    }


    private void clickAtBtn() {
        fragmentAt = new FragmentAt();

        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frame_content, fragmentAt);

        fragmentTransaction.commit();

        atFl.setSelected(true);
        atIv.setSelected(true);

        authFl.setSelected(false);
        authIv.setSelected(false);

        spaceFl.setSelected(false);
        spaceIv.setSelected(false);

        moreFl.setSelected(false);
        moreIv.setSelected(false);
    }


    private void clickAuthBtn() {
        fragmentAuth = new FragmentAuth();
        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, fragmentAuth);
        fragmentTransaction.commit();

        atFl.setSelected(false);
        atIv.setSelected(false);

        authFl.setSelected(true);
        authIv.setSelected(true);

        spaceFl.setSelected(false);
        spaceIv.setSelected(false);

        moreFl.setSelected(false);
        moreIv.setSelected(false);
    }


    private void clickSpaceBtn() {

        fragmentSpace = new FragmentSpace();
        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, fragmentSpace);
        fragmentTransaction.commit();

        atFl.setSelected(false);
        atIv.setSelected(false);

        authFl.setSelected(false);
        authIv.setSelected(false);

        spaceFl.setSelected(true);
        spaceIv.setSelected(true);

        moreFl.setSelected(false);
        moreIv.setSelected(false);
    }


    private void clickMoreBtn() {

        fragmentMore = new FragmentMore();

        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frame_content, fragmentMore);
        fragmentTransaction.commit();

        atFl.setSelected(false);
        atIv.setSelected(false);

        authFl.setSelected(false);
        authIv.setSelected(false);

        spaceFl.setSelected(false);
        spaceIv.setSelected(false);

        moreFl.setSelected(true);
        moreIv.setSelected(true);
    }

    private void clickToggleBtn() {
        showPopupWindow(toggleImageView);

        plusImageView.setSelected(true);
    }


    private void changeButtonImage() {
        plusImageView.setSelected(false);
    }


    private void showPopupWindow(View parent) {
        if (popWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.popwindow_layout, null);
            dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            popWindow = new PopupWindow(view, dm.widthPixels, LinearLayout.LayoutParams.WRAP_CONTENT);
        }

        popWindow.setFocusable(true);

        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.showAsDropDown(parent, 0,0);

        popWindow.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {

                changeButtonImage();
            }
        });


        popWindow.setTouchInterceptor(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                changeButtonImage();
                popWindow.dismiss();
                return false;
            }
        });
    }
}
