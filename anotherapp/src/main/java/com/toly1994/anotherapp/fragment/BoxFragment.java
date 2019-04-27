package com.toly1994.anotherapp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.toly1994.anotherapp.R;
import com.toly1994.anotherapp.itf.IBoxSender;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/26/026:11:36<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class BoxFragment extends Fragment {
    private static final String TAG = "LifeCycleActivity";
    private String info;

    private boolean initialization; // 界面是否已创建完成
    private boolean isVisibleToUser; // 是否对用户可见

    public static BoxFragment newInstance(String color) {
        BoxFragment fragment = new BoxFragment();
        Bundle bundle = new Bundle();
        bundle.putString("color", color);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static BoxFragment newInstance(String color, String info) {
        BoxFragment fragment = new BoxFragment();
        Bundle bundle = new Bundle();
        bundle.putString("color", color);
        bundle.putString("info", info);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            info = bundle.getString("info");
        }

        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- isVisibleToUser: " + info + "---" + isVisibleToUser+"--"+initialization);
        super.setUserVisibleHint(isVisibleToUser);

        this.isVisibleToUser = isVisibleToUser;
        lazyLoad();

    }

    private void lazyLoad() {
        if (initialization && isVisibleToUser) {
            loadData();
        }
    }

    /**
     * 核心加载方法(可抽象)
     */
    private void loadData() {
        Log.e("loadData", "initData: "+info);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onCreate: " + info);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onAttach: " + info);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onCreateView: " + info);
        View view = inflater.inflate(R.layout.fg_title, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onViewCreated: " + info);
        TextView txt = view.findViewById(R.id.id_tv_txt);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String result = bundle.getString("color");
            String info = bundle.getString("info");
            view.setBackgroundColor(Color.parseColor(result));
            txt.setText(info);
        }

        txt.setOnClickListener(v -> {
            IBoxSender sender = (IBoxSender) getActivity();
//            sender.setData(txt.getText().toString().toUpperCase());
            sender.update(txt.getText().toString());


//            if (mOnDataSend != null) {
//                mOnDataSend.send(txt.getText().toString().toUpperCase());
//            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onActivityCreated: " + info);
        initialization = true;
        lazyLoad();
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onViewStateRestored: " + info);
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onStart: " + info);
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onResume: " + info);
        super.onResume();
    }


    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onInflate: " + info);
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onAttachFragment: " + info);
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onSaveInstanceState: " + info);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onPause() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onPause: " + info);
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onStop: " + info);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onDestroyView: " + info);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onDestroy: " + info);

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onDetach: " + info);
        super.onDetach();
    }

    public interface OnDataSend {
        void send(String data);
    }

    private OnDataSend mOnDataSend;

    public void setmOnDataSend(OnDataSend mOnDataSend) {
        this.mOnDataSend = mOnDataSend;
    }
}
