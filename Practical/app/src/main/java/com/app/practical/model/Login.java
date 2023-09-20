package com.app.practical.model;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login implements Serializable, Parcelable {

    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;
    public final static Creator<Login> CREATOR = new Creator<Login>() {


        public Login createFromParcel(android.os.Parcel in) {
            return new Login(in);
        }

        public Login[] newArray(int size) {
            return (new Login[size]);
        }

    };
    private final static long serialVersionUID = 5222393777668122681L;

    protected Login(android.os.Parcel in) {
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public Login() {
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(message);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }

    public static class Data implements Serializable, Parcelable {

        @SerializedName("user_id")
        @Expose
        public Integer userId;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("company_name")
        @Expose
        public String companyName;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("user_device_id")
        @Expose
        public String userDeviceId;
        @SerializedName("access_token")
        @Expose
        public String accessToken;
        @SerializedName("fcm_token")
        @Expose
        public String fcmToken;
        @SerializedName("event_joined")
        @Expose
        public Boolean eventJoined;
        @SerializedName("stripe_customer_id")
        @Expose
        public String stripeCustomerId;

        public final Creator<Data> CREATOR = new Creator<Data>() {


            public Data createFromParcel(android.os.Parcel in) {
                return new Data(in);
            }

            public Data[] newArray(int size) {
                return (new Data[size]);
            }

        };
        private final static long serialVersionUID = -7641989758965360415L;

        @SuppressWarnings({"unchecked"})
        protected Data(android.os.Parcel in) {
            this.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.name = ((String) in.readValue((String.class.getClassLoader())));
            this.companyName = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            this.image = ((String) in.readValue((String.class.getClassLoader())));
            this.userDeviceId = ((String) in.readValue((String.class.getClassLoader())));
            this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
            this.fcmToken = ((String) in.readValue((String.class.getClassLoader())));
            this.eventJoined = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.stripeCustomerId = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Data() {
        }

        public void writeToParcel(android.os.Parcel dest, int flags) {
            dest.writeValue(userId);
            dest.writeValue(name);
            dest.writeValue(companyName);
            dest.writeValue(email);
            dest.writeValue(image);
            dest.writeValue(userDeviceId);
            dest.writeValue(accessToken);
            dest.writeValue(fcmToken);
            dest.writeValue(eventJoined);
            dest.writeValue(stripeCustomerId);
        }

        public int describeContents() {
            return 0;
        }

    }
}