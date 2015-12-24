package com.towenqi.qianlv.memory;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qianlv on 2015/12/19.
 */
public class PageData implements Parcelable {
    public String mTitleStrId;
    public int mImgResId;
    public String mDescriStrId;

    public PageData( int mImgResId, String mTitleStrId,String mDescriStrId) {
        this.mTitleStrId = mTitleStrId;
        this.mImgResId = mImgResId;
        this.mDescriStrId = mDescriStrId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitleStrId);
        dest.writeInt(this.mImgResId);
        dest.writeString(this.mDescriStrId);
    }

    protected PageData(Parcel in) {
        this.mTitleStrId = in.readString();
        this.mImgResId = in.readInt();
        this.mDescriStrId = in.readString();
    }

    public static final Creator<PageData> CREATOR = new Creator<PageData>() {
        public PageData createFromParcel(Parcel source) {
            return new PageData(source);
        }

        public PageData[] newArray(int size) {
            return new PageData[size];
        }
    };
}


