package com.example.gn.hlshop.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gn.hlshop.R;

/**
 * Created by GN on 2017/2/12.
 */
public class PhoenixToolbar extends Toolbar{
    private LayoutInflater mInflater;
    private View mview;
    private EditText mSearchview;
    private TextView mTextTitle;
    private ImageButton mRightButton;
    public PhoenixToolbar(Context context) {
        super(context,null);
    }
    public PhoenixToolbar(Context context,AttributeSet attrs){
        super(context, attrs, 0);
    }
    public PhoenixToolbar(Context context,AttributeSet attrs,int defStyleAttr){
        super(context, attrs, defStyleAttr);
        initview();
        setContentInsetsRelative(10, 10);

        if(attrs != null){
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(),attrs,
                    R.styleable.PhoenixToolbar,defStyleAttr,0);

            final Drawable rightIcon = a.getDrawable(R.styleable.PhoenixToolbar_rightButtonIcon);

            if(rightIcon != null){
                setRightButtonIcon(rightIcon);
            }
            boolean isShowSearchView = a.getBoolean(R.styleable.PhoenixToolbar_isShowSearchView,false);
            if(isShowSearchView){
                showSearchView();
                hideTitleView();
            }
            a.recycle();
        }
    }

    private void setRightButtonIcon(Drawable rightIcon){
        if(mRightButton !=null){
            mRightButton.setImageDrawable(rightIcon);
            mRightButton.setVisibility(VISIBLE);
        }
    }
    private void initview(){
        if(mview == null){
            mInflater = LayoutInflater.from(getContext());
            mview = mInflater.inflate(R.layout.toobar, null);
            mTextTitle = (TextView)mview.findViewById(R.id.toobat_title);
            mSearchview = (EditText) mview.findViewById(R.id.toolbar_searchview);
            mRightButton = (ImageButton) mview.findViewById(R.id.toobar_rightButton);
            //直接使用ToolBar 的LayoutParams
            LayoutParams lp = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER_HORIZONTAL);
            addView(mview,lp);
        }
    }



    //重写父类两个指定标题的方法
    @Override
    public void setTitle(int resId) {
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {
        //调用重写方法的时候 已经被初始化过啦
        initview();
        if(mTextTitle!=null) {
            mTextTitle.setText(title);
            showTitleView();
        }
    }
    public void showSearchView(){
        if(mSearchview != null) {
            mSearchview.setVisibility(VISIBLE);
        }
    }
    public void hideSearchView(){
        if(mSearchview != null) {
            mSearchview.setVisibility(GONE);
        }
    }
    public void showTitleView(){
        if(mTextTitle != null) {
            mTextTitle.setVisibility(VISIBLE);
        }
    }
    public void hideTitleView(){
        if(mTextTitle != null) {
            mTextTitle.setVisibility(GONE);
        }
    }
    public void setmRightButtonClickListener(OnClickListener listener){
        mRightButton.setOnClickListener(listener);

    }
}
