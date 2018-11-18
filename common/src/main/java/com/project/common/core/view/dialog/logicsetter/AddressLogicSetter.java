package com.project.common.core.view.dialog.logicsetter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.project.common.R;
import com.project.common.core.base.BaseActivity;
import com.project.common.core.http.BaseObserver;
import com.project.common.core.http.HttpCallBack;
import com.project.common.core.view.dialog.CommonFragmentDialog;
import com.project.common.core.view.dialog.api.AddressApi;
import com.project.common.core.view.dialog.api.JDAddressBean;
import com.project.common.core.view.dialog.data.DialogBaseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chihane.jdaddressselector.AddressProvider;
import chihane.jdaddressselector.AddressSelector;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/7/26 11:38
 * @版本 0.1
 * @类说明: 基础的setter
 */

public class AddressLogicSetter implements CommonFragmentDialog.ILogicSetter {

    private ILogicSetterClickLisenter onConfirmClickListener;
    private ILogicSetterClickLisenter onCancelClickListener;

    private String mTitle;
    private BaseActivity mActivity;
    //
    private boolean isNotShowCancel;
    private View mContent;

    public AddressLogicSetter(String title, BaseActivity activity) {

        mTitle = title;
        this.mActivity = activity;
    }

    Province province;
    City city;
    County county;
    Street street;
    int provinceID = -1;
    int cityID = -1;
    int countyID = -1;
    private boolean haveFour;

    @Override
    public AddressLogicSetter setLogic(final CommonFragmentDialog fragmentDialog, final View rootView) {
        TextView comfrim = rootView.findViewById(R.id.tv_comfirm);
        TextView cancle = rootView.findViewById(R.id.tv_ccancel);
        TextView common_dialog_title = rootView.findViewById(R.id.tv_title);
        FrameLayout contain = rootView.findViewById(R.id.fl_contain);

        common_dialog_title.setText(mTitle);

        AddressSelector selector = new AddressSelector(rootView.getContext());
        selector.setOnAddressSelectedListener(new OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(Province province, City city, County county, Street street) {
                AddressLogicSetter.this.province = province;
                AddressLogicSetter.this.city = city;
                AddressLogicSetter.this.county = county;
                AddressLogicSetter.this.street = street;
                provinceID = -1;
                cityID = -1;
                countyID = -1;
            }
        });
        selector.setAddressProvider(new AddressProvider() {
            @Override
            public void provideProvinces(final AddressReceiver<Province> addressReceiver) {

                Map map = new HashMap();
                map.put("level", 0);
                map.put("parentId", 0);
                new AddressApi().getAddress(map).subscribe(new BaseObserver<JDAddressBean>(new HttpCallBack<JDAddressBean>() {
                    @Override
                    public void onNext(JDAddressBean jdAddressBean) {
                        List<Province> provice = new ArrayList<>();
                        for (JDAddressBean.DataBean dataBean : jdAddressBean.getData()) {
                            Province p = new Province();
                            p.name = dataBean.getName();
                            p.id = dataBean.getId();
                            provice.add(p);
                        }
                        addressReceiver.send(provice);

                    }
                }, mActivity, false));
            }

            @Override
            public void provideCitiesWith(int i, final AddressReceiver<City> addressReceiver) {
                if (provinceID == i) {
                    return;
                }
                Map map = new HashMap();
                map.put("level", 1);
                map.put("parentId", i);
                new AddressApi().getAddress(map).subscribe(new BaseObserver<JDAddressBean>(new HttpCallBack<JDAddressBean>() {
                    @Override
                    public void onNext(JDAddressBean jdAddressBean) {
                        List<City> provice = new ArrayList<>();
                        for (JDAddressBean.DataBean dataBean : jdAddressBean.getData()) {
                            City p = new City();
                            p.name = dataBean.getName();
                            p.id = dataBean.getId();
                            provice.add(p);
                        }
                        addressReceiver.send(provice);

                    }
                }, mActivity, false));
            }

            @Override
            public void provideCountiesWith(int i, final AddressReceiver<County> addressReceiver) {
                if (cityID == i) {
                    return;
                }
                haveFour = true;
                Map map = new HashMap();
                map.put("level", 2);
                map.put("parentId", i);
                new AddressApi().getAddress(map).subscribe(new BaseObserver<JDAddressBean>(new HttpCallBack<JDAddressBean>() {
                    @Override
                    public void onNext(JDAddressBean jdAddressBean) {
                        List<County> provice = new ArrayList<>();
                        for (JDAddressBean.DataBean dataBean : jdAddressBean.getData()) {
                            County p = new County();
                            p.name = dataBean.getName();
                            p.id = dataBean.getId();
                            provice.add(p);
                        }
                        addressReceiver.send(provice);
                    }
                }, mActivity, false));
            }

            @Override
            public void provideStreetsWith(int i, final AddressReceiver<Street> addressReceiver) {
                if (countyID == i) {
                    return;
                }
                Map map = new HashMap();
                map.put("level", 3);
                map.put("parentId", i);
                new AddressApi().getAddress(map).subscribe(new BaseObserver<JDAddressBean>(new HttpCallBack<JDAddressBean>() {
                    @Override
                    public void onNext(JDAddressBean jdAddressBean) {
                        if (jdAddressBean.getData() == null || jdAddressBean.getData().size() == 0) {
                            addressReceiver.send(null);
                            haveFour = false;
                            return;
                        }
                        List<Street> provice = new ArrayList<>();
                        for (JDAddressBean.DataBean dataBean : jdAddressBean.getData()) {
                            Street p = new Street();
                            p.name = dataBean.getName();
                            p.id = dataBean.getId();
                            provice.add(p);
                        }
                        addressReceiver.send(provice);
                        haveFour = true;

                    }
                }, mActivity, false));
            }
        });
        contain.addView(selector.getView());
        comfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onConfirmClickListener != null) {
                    onConfirmClickListener.click(province, city, county, street);
                }
                fragmentDialog.dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelClickListener != null) {
                    onCancelClickListener.click(null, null, null, null);
                }
                fragmentDialog.dismiss();
            }
        });
        return this;
    }


    public AddressLogicSetter setLogicSetterComfirmLisenter(ILogicSetterClickLisenter onClickListener) {
        if (onClickListener != null) {
            this.onConfirmClickListener = onClickListener;
        }
        return this;
    }

    public AddressLogicSetter setLogicSetterCancelLisenter(ILogicSetterClickLisenter onClickListener) {
        if (onClickListener != null) {
            this.onCancelClickListener = onClickListener;
        }
        return this;
    }

    public AddressLogicSetter setCancelIsShow(boolean isShowCancel) {
        this.isNotShowCancel = isShowCancel;
        return this;
    }

    public interface ILogicSetterClickLisenter {
        void click(Province province, City city, County county, Street street);
    }
}
