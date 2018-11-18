package com.project.common.core.view.dialog;

import android.app.Activity;
import android.view.Gravity;

import com.project.common.R;
import com.project.common.core.view.dialog.logicsetter.AddressLogicSetter;
import com.project.common.core.view.dialog.logicsetter.BaseLogicSetter;
import com.project.common.core.view.dialog.logicsetter.BottomAddTextContentSetter;
import com.project.common.core.view.dialog.logicsetter.BottomSelectPortraitSetter;
import com.project.common.core.view.dialog.logicsetter.BottomSelectSexSetter;
import com.project.common.core.view.dialog.logicsetter.BottomSelectTagSetter;
import com.project.common.core.view.dialog.logicsetter.CenterTextContentSetter;
import com.project.common.core.view.dialog.logicsetter.NetDialogLogicSetter;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/7/26 10:34
 * @版本 0.1
 * @类说明: dialog生产者并显示
 */

public class DialogHelper {

    /**
     * 通用的dialog
     *
     * @param baseLogicSetter
     * @return
     */
    public static CommonFragmentDialog showCommonDialog(BaseLogicSetter baseLogicSetter) {
        return new DialogFactory(baseLogicSetter).createDialog();
    }
    /**
     * 通用的dialog
     *
     * @param baseLogicSetter
     * @return
     */
    public static CommonFragmentDialog netDialog(NetDialogLogicSetter baseLogicSetter, boolean isCancel) {
        return new DialogFactory(baseLogicSetter,R.layout.http_dialog,isCancel,Gravity.CENTER,false).createDialog();
    }

    /**
     * 选择成员项目
     * @return
     */
    public static void showSelectTagDialog(BottomSelectTagSetter setter, Activity activity) {
        new DialogFactory(setter, R.layout.dialog_bottom_select_tag,
                true, Gravity.BOTTOM, true, R.style.AnimBottom).createDialog().show(activity.getFragmentManager(), "select_tag");
    }

    /**
     * 选择性别
     *
     * @return
     */
    public static void showSelectSexDialog(BottomSelectSexSetter bottomSelectSexSetter, Activity activity) {
        new DialogFactory(bottomSelectSexSetter, R.layout.dialog_select_sex,
                true, Gravity.BOTTOM, true, R.style.AnimBottom).createDialog().show(activity.getFragmentManager(), "select_sex");
    }


    /**
     * 选择头像
     *
     * @return
     */
    public static void showSelectPortraitDialog(BottomSelectPortraitSetter bottomSelectSexSetter, Activity activity) {
        new DialogFactory(bottomSelectSexSetter, R.layout.dialog_select_image_picker,
                true, Gravity.BOTTOM, true, R.style.AnimBottom).createDialog().show(activity.getFragmentManager(), "select_portrait");
    }

    /**
     * 添加文本内容
     * @return
     */
    public static void showAddTextContentDialog(BottomAddTextContentSetter bottomSelectSexSetter, Activity activity) {
        new DialogFactory(bottomSelectSexSetter, R.layout.dialog_add_text_content,
                true, Gravity.BOTTOM, true, R.style.AnimBottom).createDialog().show(activity.getFragmentManager(), "add_text");
    }
    /**
     * 居中显示文本内容
     * @return
     */
    public static void showTextContentDialog(CenterTextContentSetter bottomSelectSexSetter, Activity activity) {
        new DialogFactory(bottomSelectSexSetter, R.layout.dialog_show_text_content,
                true, Gravity.CENTER, false, R.style.AnimBottom).createDialog().show(activity.getFragmentManager(), "show_text");
    }
    /**
     * 选择地址
     * @return
     */
    public static void showSelectAddress(AddressLogicSetter bottomSelectSexSetter, Activity activity) {
        new DialogFactory(bottomSelectSexSetter, R.layout.dialog_select_address,
                true, Gravity.BOTTOM, true, R.style.AnimBottom).createDialog().show(activity.getFragmentManager(), "select_address");
    }
}
