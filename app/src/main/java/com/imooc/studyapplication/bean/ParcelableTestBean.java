package com.imooc.studyapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ParcelableTestBean Parcelable测试
 * <p>
 * author:张冠之<br>
 * time: 2016/12/25 10:15 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class ParcelableTestBean implements Parcelable {
    private String TestName;
    private String TestId;
    private String TestType;
    private String TestNum;
    private String TestOrderNum;

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public String getTestId() {
        return TestId;
    }

    public void setTestId(String testId) {
        TestId = testId;
    }

    public String getTestType() {
        return TestType;
    }

    public void setTestType(String testType) {
        TestType = testType;
    }

    public String getTestNum() {
        return TestNum;
    }

    public void setTestNum(String testNum) {
        TestNum = testNum;
    }

    public String getTestOrderNum() {
        return TestOrderNum;
    }

    public void setTestOrderNum(String testorderNum) {
        TestOrderNum = testorderNum;
    }

    public static final Creator<ParcelableTestBean> CREATOR = new Creator<ParcelableTestBean>() {
        @Override
        public ParcelableTestBean createFromParcel(Parcel in) {
            ParcelableTestBean parcelableTestBean = new ParcelableTestBean();
            parcelableTestBean.TestType = in.readString();
            parcelableTestBean.TestOrderNum = in.readString();
            parcelableTestBean.TestNum = in.readString();
            parcelableTestBean.TestName = in.readString();
            parcelableTestBean.TestId = in.readString();
            return parcelableTestBean;
        }

        @Override
        public ParcelableTestBean[] newArray(int size) {
            return new ParcelableTestBean[size];
        }
    };

    /**
     * 一般return 0
     *
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 数据序列化
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TestName);
        dest.writeString(TestId);
        dest.writeString(TestNum);
        dest.writeString(TestOrderNum);
        dest.writeString(TestName);
    }
}
