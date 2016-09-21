package com.qf.lenovo.qingqiqiu.models;

import java.util.List;

/**
 * Created by lenovo on 2016/9/20.
 */
public class TravelNoteList {

    private String message;
    private int status;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String event_type;

        private UserBean user;

        private ActivityBean activity;

        public String getEvent_type() {
            return event_type;
        }

        public void setEvent_type(String event_type) {
            this.event_type = event_type;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public ActivityBean getActivity() {
            return activity;
        }

        public void setActivity(ActivityBean activity) {
            this.activity = activity;
        }

        public static class UserBean {
            private int id;
            private String name;
            private int gender;
            private int level;
            private String photo_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getPhoto_url() {
                return photo_url;
            }

            public void setPhoto_url(String photo_url) {
                this.photo_url = photo_url;
            }
        }

        public static class ActivityBean {
            private int id;
            private String made_at;
            private int likes_count;
            private int comments_count;
            private String topic;
            private int contents_count;
            private int district_id;
            private String created_at;
            private int favorites_count;
            private int parent_district_id;
            private int parent_district_count;
            private int level;
            private String description;
            /**
             * id : 2617
             * name : 刘芊兰
             * gender : 0
             * level : 3
             * photo_url : http://inspiration.chanyouji.cn/User/2617/135ddcbc21ea5186015e5909f50c112c.jpg
             */

            private UserBean user;
            /**
             * id : 79242
             * h5_url : http://m.ctrip.com/webapp/you/sight/458/16020.html?popup=close&autoawaken=close&allianceid=309340&sid=788076&ouid=
             * name : 呼伦贝尔大草原
             * name_en :
             * name_pinyin : hu lun bei er da cao yuan
             * business_id : 16020
             * poi_type : SIGHT
             * district_id : 458
             * lat : 49.2114982605
             * lng : 119.7657165527
             * address : 呼伦贝尔市（大兴安岭以西、阿尔山以北、西至蒙古国边境、北至俄罗斯边境的大面积区域）
             * location_name : null
             * blat : 49.2178459167
             * blng : 119.7720859586
             * youji_lat : 49.766493
             * youji_lng : 119.88799
             * youji_poi_id : 2129
             * youji_poi_name : 呼伦贝尔大草原
             * is_in_china : true
             * local_name : null
             * local_address_name : null
             */

            private PoiBean poi;
            private int inspiration_activity_id;
            private Object inspiration_activity;
            /**
             * id : 369626
             * caption : null
             * photo_url : http://inspiration.chanyouji.cn/UserActivityContent/369626/439a8251a908656ee9fff03d6b94945b.jpg
             * position : 0
             * width : 1080
             * height : 1920
             */

            private List<ContentsBean> contents;
            /**
             * id : 100062
             * name : 内蒙古
             * name_en : Neimenggu
             * name_pinyin : nei meng gu|nmg
             * score : null
             * level : 3
             * path : .120001.110000.100062.
             * published : false
             * is_in_china : true
             * user_activities_count : 50
             * lat : 40.8235
             * lng : 111.674
             * is_valid_destination : false
             * destination_id : 349
             */

            private List<DistrictsBean> districts;
            private List<?> categories;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMade_at() {
                return made_at;
            }

            public void setMade_at(String made_at) {
                this.made_at = made_at;
            }

            public int getLikes_count() {
                return likes_count;
            }

            public void setLikes_count(int likes_count) {
                this.likes_count = likes_count;
            }

            public int getComments_count() {
                return comments_count;
            }

            public void setComments_count(int comments_count) {
                this.comments_count = comments_count;
            }

            public String getTopic() {
                return topic;
            }

            public void setTopic(String topic) {
                this.topic = topic;
            }

            public int getContents_count() {
                return contents_count;
            }

            public void setContents_count(int contents_count) {
                this.contents_count = contents_count;
            }

            public int getDistrict_id() {
                return district_id;
            }

            public void setDistrict_id(int district_id) {
                this.district_id = district_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public int getFavorites_count() {
                return favorites_count;
            }

            public void setFavorites_count(int favorites_count) {
                this.favorites_count = favorites_count;
            }

            public int getParent_district_id() {
                return parent_district_id;
            }

            public void setParent_district_id(int parent_district_id) {
                this.parent_district_id = parent_district_id;
            }

            public int getParent_district_count() {
                return parent_district_count;
            }

            public void setParent_district_count(int parent_district_count) {
                this.parent_district_count = parent_district_count;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public PoiBean getPoi() {
                return poi;
            }

            public void setPoi(PoiBean poi) {
                this.poi = poi;
            }

            public int getInspiration_activity_id() {
                return inspiration_activity_id;
            }

            public void setInspiration_activity_id(int inspiration_activity_id) {
                this.inspiration_activity_id = inspiration_activity_id;
            }

            public Object getInspiration_activity() {
                return inspiration_activity;
            }

            public void setInspiration_activity(Object inspiration_activity) {
                this.inspiration_activity = inspiration_activity;
            }

            public List<ContentsBean> getContents() {
                return contents;
            }

            public void setContents(List<ContentsBean> contents) {
                this.contents = contents;
            }

            public List<DistrictsBean> getDistricts() {
                return districts;
            }

            public void setDistricts(List<DistrictsBean> districts) {
                this.districts = districts;
            }

            public List<?> getCategories() {
                return categories;
            }

            public void setCategories(List<?> categories) {
                this.categories = categories;
            }

            public static class UserBean {
                private int id;
                private String name;
                private int gender;
                private int level;
                private String photo_url;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getGender() {
                    return gender;
                }

                public void setGender(int gender) {
                    this.gender = gender;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getPhoto_url() {
                    return photo_url;
                }

                public void setPhoto_url(String photo_url) {
                    this.photo_url = photo_url;
                }
            }

            public static class PoiBean {
                private int id;
                private String h5_url;
                private String name;
                private String name_en;
                private String name_pinyin;
                private int business_id;
                private String poi_type;
                private int district_id;
                private double lat;
                private double lng;
                private String address;
                private Object location_name;
                private double blat;
                private double blng;
                private double youji_lat;
                private double youji_lng;
                private int youji_poi_id;
                private String youji_poi_name;
                private boolean is_in_china;
                private Object local_name;
                private Object local_address_name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getH5_url() {
                    return h5_url;
                }

                public void setH5_url(String h5_url) {
                    this.h5_url = h5_url;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getName_en() {
                    return name_en;
                }

                public void setName_en(String name_en) {
                    this.name_en = name_en;
                }

                public String getName_pinyin() {
                    return name_pinyin;
                }

                public void setName_pinyin(String name_pinyin) {
                    this.name_pinyin = name_pinyin;
                }

                public int getBusiness_id() {
                    return business_id;
                }

                public void setBusiness_id(int business_id) {
                    this.business_id = business_id;
                }

                public String getPoi_type() {
                    return poi_type;
                }

                public void setPoi_type(String poi_type) {
                    this.poi_type = poi_type;
                }

                public int getDistrict_id() {
                    return district_id;
                }

                public void setDistrict_id(int district_id) {
                    this.district_id = district_id;
                }

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public Object getLocation_name() {
                    return location_name;
                }

                public void setLocation_name(Object location_name) {
                    this.location_name = location_name;
                }

                public double getBlat() {
                    return blat;
                }

                public void setBlat(double blat) {
                    this.blat = blat;
                }

                public double getBlng() {
                    return blng;
                }

                public void setBlng(double blng) {
                    this.blng = blng;
                }

                public double getYouji_lat() {
                    return youji_lat;
                }

                public void setYouji_lat(double youji_lat) {
                    this.youji_lat = youji_lat;
                }

                public double getYouji_lng() {
                    return youji_lng;
                }

                public void setYouji_lng(double youji_lng) {
                    this.youji_lng = youji_lng;
                }

                public int getYouji_poi_id() {
                    return youji_poi_id;
                }

                public void setYouji_poi_id(int youji_poi_id) {
                    this.youji_poi_id = youji_poi_id;
                }

                public String getYouji_poi_name() {
                    return youji_poi_name;
                }

                public void setYouji_poi_name(String youji_poi_name) {
                    this.youji_poi_name = youji_poi_name;
                }

                public boolean isIs_in_china() {
                    return is_in_china;
                }

                public void setIs_in_china(boolean is_in_china) {
                    this.is_in_china = is_in_china;
                }

                public Object getLocal_name() {
                    return local_name;
                }

                public void setLocal_name(Object local_name) {
                    this.local_name = local_name;
                }

                public Object getLocal_address_name() {
                    return local_address_name;
                }

                public void setLocal_address_name(Object local_address_name) {
                    this.local_address_name = local_address_name;
                }
            }

            public static class ContentsBean {
                private int id;
                private Object caption;
                private String photo_url;
                private int position;
                private int width;
                private int height;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public Object getCaption() {
                    return caption;
                }

                public void setCaption(Object caption) {
                    this.caption = caption;
                }

                public String getPhoto_url() {
                    return photo_url;
                }

                public void setPhoto_url(String photo_url) {
                    this.photo_url = photo_url;
                }

                public int getPosition() {
                    return position;
                }

                public void setPosition(int position) {
                    this.position = position;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }

            public static class DistrictsBean {
                private int id;
                private String name;
                private String name_en;
                private String name_pinyin;
                private Object score;
                private int level;
                private String path;
                private boolean published;
                private boolean is_in_china;
                private int user_activities_count;
                private double lat;
                private double lng;
                private boolean is_valid_destination;
                private int destination_id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getName_en() {
                    return name_en;
                }

                public void setName_en(String name_en) {
                    this.name_en = name_en;
                }

                public String getName_pinyin() {
                    return name_pinyin;
                }

                public void setName_pinyin(String name_pinyin) {
                    this.name_pinyin = name_pinyin;
                }

                public Object getScore() {
                    return score;
                }

                public void setScore(Object score) {
                    this.score = score;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public boolean isPublished() {
                    return published;
                }

                public void setPublished(boolean published) {
                    this.published = published;
                }

                public boolean isIs_in_china() {
                    return is_in_china;
                }

                public void setIs_in_china(boolean is_in_china) {
                    this.is_in_china = is_in_china;
                }

                public int getUser_activities_count() {
                    return user_activities_count;
                }

                public void setUser_activities_count(int user_activities_count) {
                    this.user_activities_count = user_activities_count;
                }

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }

                public boolean isIs_valid_destination() {
                    return is_valid_destination;
                }

                public void setIs_valid_destination(boolean is_valid_destination) {
                    this.is_valid_destination = is_valid_destination;
                }

                public int getDestination_id() {
                    return destination_id;
                }

                public void setDestination_id(int destination_id) {
                    this.destination_id = destination_id;
                }
            }
        }
    }
}
