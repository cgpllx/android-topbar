package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义组合控件的使用步骤：
 * 0. 在res/attr.xml文件中定义属性
 * 1. 初始化自定义控件
 * 2. 获取自定义控件的属性
 * 3. 设置自定义控件
 * 4. 将自定义控件添加到ViewGroup中
 * 5. 添加按钮事件监听
 */
public class TopBar extends RelativeLayout {
	
	private TextView mTitleTextView;
	private Button mLeftButton, mRightButton;
	
	private String mTitleText;
	private float mTitleTextSize;
	private int mTitleTextColor;
	
	private String mLeftText;
	private int mLeftColor;
	private Drawable mLeftBackground;
	
	private String mRightText;
	private int mRightColor;
	private Drawable mRightBackground;
	
	private LayoutParams leftParams, rightParams, titleParams;
	private TopBarListener mTopBarListener;
	private boolean mLeftVisible;
	private boolean mRightVisible;

	@SuppressWarnings("deprecation")
	public TopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initViews(context);
		
		getAttributes(context, attrs);
		
		setAttributes();
		
		addViewsToViewGroup();
		
		setButtonListener();
		
		setButtonVisible();
	}

	/**
	 * 初始化控件
	 * @param context
	 */
	private void initViews(Context context) {
		mTitleTextView = new TextView(context);
		mLeftButton = new Button(context);
		mRightButton = new Button(context);
	}
	
	/**
	 * 获取自定义的属性
	 * @param context
	 * @param attrs
	 */
	private void getAttributes(Context context, AttributeSet attrs) {
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
		
		mTitleText = ta.getString(R.styleable.TopBar_titleText);
		mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
		mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 0);
		
		mLeftText = ta.getString(R.styleable.TopBar_leftText);
		mLeftColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
		mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
		mLeftVisible = ta.getBoolean(R.styleable.TopBar_leftVisible, true);
		
		mRightText = ta.getString(R.styleable.TopBar_rightText);
		mRightColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
		mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
		mRightVisible = ta.getBoolean(R.styleable.TopBar_rightVisible, true);
		
		ta.recycle();
	}
	
	/**
	 * 设置属性
	 */
	private void setAttributes() {
		mTitleTextView.setText(mTitleText);
		mTitleTextView.setTextColor(mTitleTextColor);
		mTitleTextView.setTextSize(mTitleTextSize);
		
		mLeftButton.setText(mLeftText);
		mLeftButton.setTextColor(mLeftColor);
		mLeftButton.setBackgroundDrawable(mLeftBackground);
		
		mRightButton.setText(mRightText);
		mRightButton.setTextColor(mRightColor);
		mRightButton.setBackgroundDrawable(mRightBackground);
	}

	/**
	 * 将自定义控件添加到ViewGroup
	 */
	private void addViewsToViewGroup() {
		leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
		addView(mLeftButton, leftParams);
		
		rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
		addView(mRightButton, rightParams);
		
		titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
		addView(mTitleTextView, titleParams);
	}
	
	/**
	 * 设置按钮事件监听
	 */
	private void setButtonListener() {
		mLeftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mTopBarListener.setLeftButtonListener();
			}
		});
		
		mRightButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mTopBarListener.setRightButtonListener();
			}
		});
	}
	
	/**
	 * 以布局的形式设置左按钮是否可见
	 */
	private void setButtonVisible() {
		if (mLeftVisible) {
			mLeftButton.setVisibility(View.VISIBLE);
		} else {
			mLeftButton.setVisibility(View.INVISIBLE);
		}
		
		if (mRightVisible) {
			mRightButton.setVisibility(View.VISIBLE);
		} else {
			mRightButton.setVisibility(View.INVISIBLE);
		}
	}

	
	/**
	 * 代码的方式设置左控件是否可见
	 * @param isVisibled
	 */
	public void setLeftButtonVisible(boolean isVisibled) {
		if (isVisibled) {
			mLeftButton.setVisibility(View.VISIBLE);
		} else {
			mLeftButton.setVisibility(View.INVISIBLE);
		}
	}
	
	/**
	 * 代码的方式设置右控件是否可见
	 * @param isVisibled
	 */
	public void setRightButtonVisible(boolean isVisibled) {
		if (isVisibled) {
			mRightButton.setVisibility(View.VISIBLE);
		} else {
			mRightButton.setVisibility(View.INVISIBLE);
		}
	}
	
	public void setTopBarListener(TopBarListener topBarListener) {
		mTopBarListener = topBarListener;
	}
	
	/**
	 * 监听回调接口
	 */
	public interface TopBarListener {
		public void setLeftButtonListener();
		public void setRightButtonListener();
	}

}
