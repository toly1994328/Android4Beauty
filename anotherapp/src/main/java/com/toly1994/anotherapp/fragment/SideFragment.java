package com.toly1994.anotherapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.toly1994.anotherapp.R;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/27/027:7:43<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class SideFragment extends Fragment {
    private static final String TAG = "PagerFragment";

    public static SideFragment newInstance(String color) {
        SideFragment fragment = new SideFragment();
        Bundle bundle = new Bundle();
        bundle.putString("color", color);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onCreateView: ");

        return inflater.inflate(R.layout.fg_side, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onViewCreated: ");

        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onCreate: " );
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onAttach: " );
        super.onAttach(context);
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onActivityCreated: " );
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onViewStateRestored: " );
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onStart: " );
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onResume: " );
        super.onResume();
    }


    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onInflate: " );
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onAttachFragment: " );
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onSaveInstanceState: " );
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onPause() {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onPause: " );
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onStop: " );
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onDestroyView: " );
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onDestroy: " );

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e(TAG, "Fragment -☯☯☯☯☯☯☯☯- onDetach: " );
        super.onDetach();
    }
}
