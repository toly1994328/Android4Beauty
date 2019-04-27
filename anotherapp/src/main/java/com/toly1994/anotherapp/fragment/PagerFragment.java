package com.toly1994.anotherapp.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.toly1994.anotherapp.R;

import java.util.List;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/27/027:7:43<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class PagerFragment extends Fragment {
    private static final String TAG = "PagerFragment";

    private List<Fragment> fragments;
    private FragmentTransaction transaction;
    private BoxFragment title;

    public static PagerFragment newInstance(String color) {
        PagerFragment fragment = new PagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("color", color);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onCreateView: ");

        return inflater.inflate(R.layout.fg_pager, container, false);
    }

    private boolean sideShowing = true;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);

        view.setOnClickListener(v -> {
            if (sideShowing) {
                hideAt(0);
            } else {
                showAt(0);
            }
            sideShowing = !sideShowing;

            if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
                    checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID) &&
                    checkSelfPermission(REQUESTED_PERMISSIONS[2], PERMISSION_REQ_ID)) {
                //执行到此处说明已有权限成功
                Toast.makeText(getActivity(), "已有权限成功", Toast.LENGTH_SHORT).show();

            }
        });

        view.setOnLongClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, 0);// 设定结果返回
            return false;


        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 0:
                    //打开相册并选择照片，这个方式选择单张
                    // 获取返回的数据，这里是android自定义的Uri地址
                    Uri selectedImage = data.getData();
                    Log.e("startActivityForResult", "startActivityForResult: " + selectedImage);
                    break;
            }
        }

    }

    private static final int PERMISSION_REQ_ID = 22;
    private static final String[] REQUESTED_PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,//录音权限
            Manifest.permission.CAMERA,//相机权限
            Manifest.permission.WRITE_EXTERNAL_STORAGE//SD卡写权限
    };

    /**
     * 检查权限的方法
     *
     * @param permission  权限
     * @param requestCode 请求码
     * @return 是否拥有权限
     */
    public boolean checkSelfPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(getActivity(), permission)
                != PackageManager.PERMISSION_GRANTED) {
            //发送权限请求
            PagerFragment.this.requestPermissions(REQUESTED_PERMISSIONS, requestCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQ_ID: {//请求码
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED ||
                        grantResults[1] != PackageManager.PERMISSION_GRANTED ||
                        grantResults[2] != PackageManager.PERMISSION_GRANTED) {
                    //三个权限有任意的未被允许,弹吐司，退出
                    Toast.makeText(getActivity(), "用户没有允许权限", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                    break;
                }
                Log.e(TAG, "onRequestPerm: OK");
                break;
            }
        }
    }

    public void hideAt(int i) {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        getChildFragmentManager().beginTransaction().hide(fragments.get(i)).commit();
    }

    public void showAt(int i) {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        getChildFragmentManager().beginTransaction().show(fragments.get(i)).commit();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onStart: ");
        super.onStart();
//        transaction = getChildFragmentManager().beginTransaction();
        SideFragment side = new SideFragment();
        title = BoxFragment.newInstance("#eeeeee");
        BoxFragment footer = BoxFragment.newInstance("#eeeeee");
//        transaction.add(R.id.fl_side, side).show(sideFragment).commit();

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.fl_side, side)
                .add(R.id.fl_title, title)
                .add(R.id.fl_bottom, footer)
                .show(title)
                .show(side)
                .show(footer)
                .commit();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- isVisibleToUser: ");
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onViewStateRestored: ");
        super.onViewStateRestored(savedInstanceState);
    }


    @Override
    public void onResume() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onResume: ");
        super.onResume();
    }


    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onInflate: ");
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onAttachFragment: ");
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onPause() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onPause: ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onStop: ");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onDestroy: ");

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e(TAG, "Fragment -卍卍卍卍卍卍卍- onDetach: ");
        super.onDetach();
    }
}
