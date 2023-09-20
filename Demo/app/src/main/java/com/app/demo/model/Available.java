package com.app.demo.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Available implements Parcelable {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private Data data;

    public final static Creator<Available> CREATOR = new Creator<Available>() {

        public Available createFromParcel(android.os.Parcel in) {
            return new Available(in);
        }

        public Available[] newArray(int size) {
            return (new Available[size]);
        }
    };

    protected Available(android.os.Parcel in) {
        this.result = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.msg = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public Available() {
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(result);
        dest.writeValue(msg);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }

    public static class Data implements Parcelable {

        @SerializedName("totalItems")
        @Expose
        private Integer totalItems;
        @SerializedName("startIndex")
        @Expose
        private Integer startIndex;
        @SerializedName("itemsPerPage")
        @Expose
        private Integer itemsPerPage;
        @SerializedName("list")
        @Expose
        private ArrayList<List> list = null;

        public final Creator<Data> CREATOR = new Creator<Data>() {

            public Data createFromParcel(android.os.Parcel in) {
                return new Data(in);
            }

            public Data[] newArray(int size) {
                return (new Data[size]);
            }
        };

        protected Data(android.os.Parcel in) {
            this.totalItems = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.startIndex = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.itemsPerPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(this.list, (List.class.getClassLoader()));
        }

        public Data() {
        }

        public Integer getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(Integer totalItems) {
            this.totalItems = totalItems;
        }

        public Integer getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(Integer startIndex) {
            this.startIndex = startIndex;
        }

        public Integer getItemsPerPage() {
            return itemsPerPage;
        }

        public void setItemsPerPage(Integer itemsPerPage) {
            this.itemsPerPage = itemsPerPage;
        }

        public ArrayList getList() {
            return list;
        }

        public void setList(ArrayList list) {
            this.list = list;
        }

        public void writeToParcel(android.os.Parcel dest, int flags) {
            dest.writeValue(totalItems);
            dest.writeValue(startIndex);
            dest.writeValue(itemsPerPage);
            dest.writeList(list);
        }

        public int describeContents() {
            return 0;
        }

        public static class List implements Parcelable {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("price")
            @Expose
            private Integer price;
            @SerializedName("age")
            @Expose
            private Integer age;
            @SerializedName("isGraded")
            @Expose
            private Boolean isGraded;
            @SerializedName("isSold")
            @Expose
            private Boolean isSold;
            @SerializedName("isCoin")
            @Expose
            private Boolean isCoin;
            @SerializedName("tags")
            @Expose
            public ArrayList<String> tags = new ArrayList<>();
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("history")
            @Expose
            private String history;
            @SerializedName("year")
            @Expose
            private Integer year;
            @SerializedName("pictures")
            @Expose
            private Pictures pictures;

            public final Creator<List> CREATOR = new Creator<List>() {

                public List createFromParcel(android.os.Parcel in) {
                    return new List(in);
                }

                public List[] newArray(int size) {
                    return (new List[size]);
                }
            };

            protected List(android.os.Parcel in) {
                this.id = ((String) in.readValue((String.class.getClassLoader())));
                this.price = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.age = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.isGraded = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                this.isSold = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                this.isCoin = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                in.readList(this.tags, (java.lang.String.class.getClassLoader()));
                this.name = ((String) in.readValue((String.class.getClassLoader())));
                this.history = ((String) in.readValue((String.class.getClassLoader())));
                this.year = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.pictures = ((Pictures) in.readValue((Pictures.class.getClassLoader())));
            }

            public List() {
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Integer getPrice() {
                return price;
            }

            public void setPrice(Integer price) {
                this.price = price;
            }

            public Integer getAge() {
                return age;
            }

            public void setAge(Integer age) {
                this.age = age;
            }

            public Boolean getIsGraded() {
                return isGraded;
            }

            public void setIsGraded(Boolean isGraded) {
                this.isGraded = isGraded;
            }

            public Boolean getIsSold() {
                return isSold;
            }

            public void setIsSold(Boolean isSold) {
                this.isSold = isSold;
            }

            public Boolean getIsCoin() {
                return isCoin;
            }

            public void setIsCoin(Boolean isCoin) {
                this.isCoin = isCoin;
            }

            public ArrayList<String> getTags() {
                return tags;
            }

            public void setTags(ArrayList<String> tags) {
                this.tags = tags;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHistory() {
                return history;
            }

            public void setHistory(String history) {
                this.history = history;
            }

            public Integer getYear() {
                return year;
            }

            public void setYear(Integer year) {
                this.year = year;
            }

            public Pictures getPictures() {
                return pictures;
            }

            public void setPictures(Pictures pictures) {
                this.pictures = pictures;
            }

            public void writeToParcel(android.os.Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(price);
                dest.writeValue(age);
                dest.writeValue(isGraded);
                dest.writeValue(isSold);
                dest.writeValue(isCoin);
                dest.writeList(tags);
                dest.writeValue(name);
                dest.writeValue(history);
                dest.writeValue(year);
                dest.writeValue(pictures);
            }

            public int describeContents() {
                return 0;
            }

            public class Pictures implements Parcelable {

                @SerializedName("front")
                @Expose
                private Front front;
                @SerializedName("back")
                @Expose
                private Back back;

                public final Creator<Pictures> CREATOR = new Creator<Pictures>() {

                    public Pictures createFromParcel(android.os.Parcel in) {
                        return new Pictures(in);
                    }

                    public Pictures[] newArray(int size) {
                        return (new Pictures[size]);
                    }
                };

                protected Pictures(android.os.Parcel in) {
                    this.front = ((Front) in.readValue((Front.class.getClassLoader())));
                    this.back = ((Back) in.readValue((Back.class.getClassLoader())));
                }

                public Pictures() {
                }

                public Front getFront() {
                    return front;
                }

                public void setFront(Front front) {
                    this.front = front;
                }

                public Back getBack() {
                    return back;
                }

                public void setBack(Back back) {
                    this.back = back;
                }

                public void writeToParcel(android.os.Parcel dest, int flags) {
                    dest.writeValue(front);
                    dest.writeValue(back);
                }

                public int describeContents() {
                    return 0;
                }

                public class Front implements Parcelable {

                    @SerializedName("key")
                    @Expose
                    private String key;
                    @SerializedName("url")
                    @Expose
                    private String url;
                    @SerializedName("sizeInMegaByte")
                    @Expose
                    private Float sizeInMegaByte;

                    public final Creator<Front> CREATOR = new Creator<Front>() {


                        @SuppressWarnings({
                                "unchecked"
                        })
                        public Front createFromParcel(android.os.Parcel in) {
                            return new Front(in);
                        }

                        public Front[] newArray(int size) {
                            return (new Front[size]);
                        }

                    };

                    protected Front(android.os.Parcel in) {
                        this.key = ((String) in.readValue((String.class.getClassLoader())));
                        this.url = ((String) in.readValue((String.class.getClassLoader())));
                        this.sizeInMegaByte = ((Float) in.readValue((Float.class.getClassLoader())));
                    }

                    public Front() {
                    }

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public Float getSizeInMegaByte() {
                        return sizeInMegaByte;
                    }

                    public void setSizeInMegaByte(Float sizeInMegaByte) {
                        this.sizeInMegaByte = sizeInMegaByte;
                    }

                    public void writeToParcel(android.os.Parcel dest, int flags) {
                        dest.writeValue(key);
                        dest.writeValue(url);
                        dest.writeValue(sizeInMegaByte);
                    }

                    public int describeContents() {
                        return 0;
                    }
                }

                public class Back implements Parcelable {

                    @SerializedName("key")
                    @Expose
                    private String key;
                    @SerializedName("url")
                    @Expose
                    private String url;
                    @SerializedName("sizeInMegaByte")
                    @Expose
                    private Float sizeInMegaByte;

                    public final Creator<Back> CREATOR = new Parcelable.Creator<Back>() {

                        public Back createFromParcel(android.os.Parcel in) {
                            return new Back(in);
                        }

                        public Back[] newArray(int size) {
                            return (new Back[size]);
                        }
                    };

                    protected Back(android.os.Parcel in) {
                        this.key = ((String) in.readValue((String.class.getClassLoader())));
                        this.url = ((String) in.readValue((String.class.getClassLoader())));
                        this.sizeInMegaByte = ((Float) in.readValue((Float.class.getClassLoader())));
                    }

                    public Back() {
                    }

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public Float getSizeInMegaByte() {
                        return sizeInMegaByte;
                    }

                    public void setSizeInMegaByte(Float sizeInMegaByte) {
                        this.sizeInMegaByte = sizeInMegaByte;
                    }

                    public void writeToParcel(android.os.Parcel dest, int flags) {
                        dest.writeValue(key);
                        dest.writeValue(url);
                        dest.writeValue(sizeInMegaByte);
                    }

                    public int describeContents() {
                        return 0;
                    }
                }
            }
        }
    }
}