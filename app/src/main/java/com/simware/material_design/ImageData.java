package com.simware.material_design;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/6/7.
 */

public class ImageData implements Parcelable{
    private int imageId;
    private String name;
    public ImageData(){

    }
    public ImageData(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(imageId);
    }
    public static final Parcelable.Creator<ImageData> CREATOR=new Parcelable.Creator<ImageData>(){

        @Override
        public ImageData createFromParcel(Parcel parcel) {
            ImageData data=new ImageData();
            data.name=parcel.readString();
            data.imageId=parcel.readInt();
            return data;
        }

        @Override
        public ImageData[] newArray(int i) {
            return new ImageData[i];
        }
    };
}
