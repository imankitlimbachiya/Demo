package com.app.practical.model;

import java.io.Serializable;
import java.util.List;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event implements Serializable, Parcelable {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data;
    @SerializedName("expired_message")
    @Expose
    private String expiredMessage;

    public final static Creator<Event> CREATOR = new Creator<Event>() {


        public Event createFromParcel(android.os.Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return (new Event[size]);
        }

    };
    private final static long serialVersionUID = -6864215161707784140L;

    protected Event(android.os.Parcel in) {
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (Datum.class.getClassLoader()));
        this.expiredMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Event() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getExpiredMessage() {
        return expiredMessage;
    }

    public void setExpiredMessage(String expiredMessage) {
        this.expiredMessage = expiredMessage;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(message);
        dest.writeList(data);
        dest.writeValue(expiredMessage);
    }

    public int describeContents() {
        return 0;
    }

    public static class Datum implements Serializable, Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("event_title")
        @Expose
        private String eventTitle;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("event_start_date")
        @Expose
        private String eventStartDate;
        @SerializedName("event_end_date")
        @Expose
        private String eventEndDate;
        @SerializedName("expired_flag")
        @Expose
        private String expiredFlag;
        @SerializedName("is_group")
        @Expose
        private String isGroup;
        @SerializedName("group_details")
        @Expose
        private GroupDetails groupDetails;
        @SerializedName("user_role")
        @Expose
        private UserRole userRole;
        @SerializedName("pitch")
        @Expose
        private Boolean pitch;
        @SerializedName("offring")
        @Expose
        private Boolean offring;
        @SerializedName("seeking")
        @Expose
        private Boolean seeking;

        public final static Creator<Datum> CREATOR = new Creator<Datum>() {


            public Datum createFromParcel(android.os.Parcel in) {
                return new Datum(in);
            }

            public Datum[] newArray(int size) {
                return (new Datum[size]);
            }

        };
        private final static long serialVersionUID = -1295127600581333268L;

        protected Datum(android.os.Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.eventTitle = ((String) in.readValue((String.class.getClassLoader())));
            this.image = ((String) in.readValue((String.class.getClassLoader())));
            this.eventStartDate = ((String) in.readValue((String.class.getClassLoader())));
            this.eventEndDate = ((String) in.readValue((String.class.getClassLoader())));
            this.expiredFlag = ((String) in.readValue((String.class.getClassLoader())));
            this.isGroup = ((String) in.readValue((String.class.getClassLoader())));
            this.groupDetails = ((GroupDetails) in.readValue((GroupDetails.class.getClassLoader())));
            this.userRole = ((UserRole) in.readValue((UserRole.class.getClassLoader())));
            this.pitch = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.offring = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.seeking = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        }

        public Datum() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEventTitle() {
            return eventTitle;
        }

        public void setEventTitle(String eventTitle) {
            this.eventTitle = eventTitle;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getEventStartDate() {
            return eventStartDate;
        }

        public void setEventStartDate(String eventStartDate) {
            this.eventStartDate = eventStartDate;
        }

        public String getEventEndDate() {
            return eventEndDate;
        }

        public void setEventEndDate(String eventEndDate) {
            this.eventEndDate = eventEndDate;
        }

        public String getExpiredFlag() {
            return expiredFlag;
        }

        public void setExpiredFlag(String expiredFlag) {
            this.expiredFlag = expiredFlag;
        }

        public String getIsGroup() {
            return isGroup;
        }

        public void setIsGroup(String isGroup) {
            this.isGroup = isGroup;
        }

        public GroupDetails getGroupDetails() {
            return groupDetails;
        }

        public void setGroupDetails(GroupDetails groupDetails) {
            this.groupDetails = groupDetails;
        }

        public UserRole getUserRole() {
            return userRole;
        }

        public void setUserRole(UserRole userRole) {
            this.userRole = userRole;
        }

        public Boolean getPitch() {
            return pitch;
        }

        public void setPitch(Boolean pitch) {
            this.pitch = pitch;
        }

        public Boolean getOffring() {
            return offring;
        }

        public void setOffring(Boolean offring) {
            this.offring = offring;
        }

        public Boolean getSeeking() {
            return seeking;
        }

        public void setSeeking(Boolean seeking) {
            this.seeking = seeking;
        }

        public void writeToParcel(android.os.Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(eventTitle);
            dest.writeValue(image);
            dest.writeValue(eventStartDate);
            dest.writeValue(eventEndDate);
            dest.writeValue(expiredFlag);
            dest.writeValue(isGroup);
            dest.writeValue(groupDetails);
            dest.writeValue(userRole);
            dest.writeValue(pitch);
            dest.writeValue(offring);
            dest.writeValue(seeking);
        }

        public int describeContents() {
            return 0;
        }

        public static class GroupDetails implements Serializable, Parcelable {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("group_name")
            @Expose
            private String groupName;
            @SerializedName("group_image")
            @Expose
            private String groupImage;
            @SerializedName("count")
            @Expose
            private Object count;
            @SerializedName("details")
            @Expose
            private List<Detail> details;
            public final static Creator<GroupDetails> CREATOR = new Creator<GroupDetails>() {


                public GroupDetails createFromParcel(android.os.Parcel in) {
                    return new GroupDetails(in);
                }

                public GroupDetails[] newArray(int size) {
                    return (new GroupDetails[size]);
                }

            };
            private final static long serialVersionUID = 7879694111443293233L;

            @SuppressWarnings({"unchecked"})
            protected GroupDetails(android.os.Parcel in) {
                this.id = ((String) in.readValue((String.class.getClassLoader())));
                this.groupName = ((String) in.readValue((String.class.getClassLoader())));
                this.groupImage = ((String) in.readValue((String.class.getClassLoader())));
                this.count = ((Object) in.readValue((Object.class.getClassLoader())));
                in.readList(this.details, (Detail.class.getClassLoader()));
            }

            public GroupDetails() {
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getGroupImage() {
                return groupImage;
            }

            public void setGroupImage(String groupImage) {
                this.groupImage = groupImage;
            }

            public Object getCount() {
                return count;
            }

            public void setCount(Object count) {
                this.count = count;
            }

            public List<Detail> getDetails() {
                return details;
            }

            public void setDetails(List<Detail> details) {
                this.details = details;
            }

            public void writeToParcel(android.os.Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(groupName);
                dest.writeValue(groupImage);
                dest.writeValue(count);
                dest.writeList(details);
            }

            public int describeContents() {
                return 0;
            }

            public class Detail implements Serializable, Parcelable {

                @SerializedName("user_id")
                @Expose
                private String userId;
                @SerializedName("name")
                @Expose
                private String name;
                @SerializedName("image")
                @Expose
                private String image;
                public final Creator<Detail> CREATOR = new Creator<Detail>() {


                    public Detail createFromParcel(android.os.Parcel in) {
                        return new Detail(in);
                    }

                    public Detail[] newArray(int size) {
                        return (new Detail[size]);
                    }

                };
                private final static long serialVersionUID = 7310957959789374870L;

                @SuppressWarnings({"unchecked"})
                protected Detail(android.os.Parcel in) {
                    this.userId = ((String) in.readValue((String.class.getClassLoader())));
                    this.name = ((String) in.readValue((String.class.getClassLoader())));
                    this.image = ((String) in.readValue((String.class.getClassLoader())));
                }

                public Detail() {
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public void writeToParcel(android.os.Parcel dest, int flags) {
                    dest.writeValue(userId);
                    dest.writeValue(name);
                    dest.writeValue(image);
                }

                public int describeContents() {
                    return 0;
                }

            }

        }

        public class UserRole implements Serializable, Parcelable {

            @SerializedName("role_id")
            @Expose
            private String roleId;
            @SerializedName("force_meeting")
            @Expose
            private Integer forceMeeting;
            @SerializedName("max_meeting_privilage")
            @Expose
            private String maxMeetingPrivilage;
            @SerializedName("max_meeting_non_privilage")
            @Expose
            private String maxMeetingNonPrivilage;
            @SerializedName("max_workshop")
            @Expose
            private String maxWorkshop;
            @SerializedName("force_meeting_flag")
            @Expose
            private String forceMeetingFlag;
            public final Creator<UserRole> CREATOR = new Creator<UserRole>() {


                public UserRole createFromParcel(android.os.Parcel in) {
                    return new UserRole(in);
                }

                public UserRole[] newArray(int size) {
                    return (new UserRole[size]);
                }

            };
            private final static long serialVersionUID = -1223377992242772802L;

            @SuppressWarnings({"unchecked"})
            protected UserRole(android.os.Parcel in) {
                this.roleId = ((String) in.readValue((String.class.getClassLoader())));
                this.forceMeeting = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.maxMeetingPrivilage = ((String) in.readValue((String.class.getClassLoader())));
                this.maxMeetingNonPrivilage = ((String) in.readValue((String.class.getClassLoader())));
                this.maxWorkshop = ((String) in.readValue((String.class.getClassLoader())));
                this.forceMeetingFlag = ((String) in.readValue((String.class.getClassLoader())));
            }

            public UserRole() {
            }

            public String getRoleId() {
                return roleId;
            }

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }

            public Integer getForceMeeting() {
                return forceMeeting;
            }

            public void setForceMeeting(Integer forceMeeting) {
                this.forceMeeting = forceMeeting;
            }

            public String getMaxMeetingPrivilage() {
                return maxMeetingPrivilage;
            }

            public void setMaxMeetingPrivilage(String maxMeetingPrivilage) {
                this.maxMeetingPrivilage = maxMeetingPrivilage;
            }

            public String getMaxMeetingNonPrivilage() {
                return maxMeetingNonPrivilage;
            }

            public void setMaxMeetingNonPrivilage(String maxMeetingNonPrivilage) {
                this.maxMeetingNonPrivilage = maxMeetingNonPrivilage;
            }

            public String getMaxWorkshop() {
                return maxWorkshop;
            }

            public void setMaxWorkshop(String maxWorkshop) {
                this.maxWorkshop = maxWorkshop;
            }

            public String getForceMeetingFlag() {
                return forceMeetingFlag;
            }

            public void setForceMeetingFlag(String forceMeetingFlag) {
                this.forceMeetingFlag = forceMeetingFlag;
            }

            public void writeToParcel(android.os.Parcel dest, int flags) {
                dest.writeValue(roleId);
                dest.writeValue(forceMeeting);
                dest.writeValue(maxMeetingPrivilage);
                dest.writeValue(maxMeetingNonPrivilage);
                dest.writeValue(maxWorkshop);
                dest.writeValue(forceMeetingFlag);
            }

            public int describeContents() {
                return 0;
            }
        }
    }
}