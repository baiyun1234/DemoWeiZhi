package bai.bai.bai.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bai.bai.bai.demo.R;

/**
 * 自定义的ActionBar
 * Custom ActionBar
 */
public class BaseActionBar extends RelativeLayout implements View.OnClickListener {

    private ActionBarClickListener mListener;
    private View mContentView;
    private TextView mTvTitle, mTvRightMsg;

    public BaseActionBar(Context context) {
        this(context, null);
    }

    public BaseActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //loadData
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BaseActionBar);
        String title = array.getString(R.styleable.BaseActionBar_title);
        String rightMsg = array.getString(R.styleable.BaseActionBar_rightMessage);
        boolean isShowLeftBackIcon = array.getBoolean(R.styleable.BaseActionBar_isShowLeftBackIcon, true);
        boolean isShowRightIcon = array.getBoolean(R.styleable.BaseActionBar_isShowRightIcon, false);
        boolean isShowRightMsg = array.getBoolean(R.styleable.BaseActionBar_isShowRightMessage, false);
        int rightIconId = array.getResourceId(R.styleable.BaseActionBar_rightIcon, R.mipmap.ic_launcher);

        //initView
        mContentView = LayoutInflater.from(context).inflate(R.layout.view_action_bar, null);
        mTvTitle = mContentView.findViewById(R.id.tv_title);
        ImageView ivRightIcon = mContentView.findViewById(R.id.iv_right_icon);
        ImageView ivBack = mContentView.findViewById(R.id.iv_back);
        mTvRightMsg = mContentView.findViewById(R.id.tv_right_msg);

        //setViewData
        isShowView(isShowLeftBackIcon, ivBack);
        isShowView(isShowRightIcon, ivRightIcon);
        isShowView(isShowRightMsg, mTvRightMsg);
        if (title != null) mTvTitle.setText(title);
        mTvRightMsg.setText(rightMsg);
        ivRightIcon.setImageResource(rightIconId);

        addView(mContentView);
        array.recycle();
    }

    public void isShowView(boolean isShow, View view) {
        if (isShow) {
            view.setVisibility(VISIBLE);
            view.setOnClickListener(this);
        } else {
            view.setVisibility(INVISIBLE);
        }
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setRightText(String rightMassage) {
        mTvRightMsg.setText(rightMassage);
    }

    public void setActionBarClickListener(ActionBarClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {

        // Resource IDs cannot be used in a switch statement in Android library modules
        if (v.getId() == R.id.iv_back) {
            if (mListener == null) return;
            mListener.onActionBarLeft();
        } else if (v.getId() == R.id.tv_right_msg) {
            if (mListener == null) return;
            mListener.onActionBarRight();
        } else if (v.getId() == R.id.iv_right_icon) {
            if (mListener == null) return;
            mListener.onActionBarRight();
        }
    }

    public interface ActionBarClickListener {
        void onActionBarLeft();

        void onActionBarRight();
    }

}