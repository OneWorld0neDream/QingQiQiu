package com.qf.lenovo.qingqiqiu.models;

import java.util.List;

/**
 * Created by 31098 on 9/22/2016.
 */

public class TravelNoteListModel {

    private String message;
    private int status;
    private List<TravelNoteListItemModel> data;

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

    public List<TravelNoteListItemModel> getData() {
        return data;
    }

    public void setData(List<TravelNoteListItemModel> data) {
        this.data = data;
    }

    public static class TravelNoteListItemModel {
        private boolean wished;
        private int id;
        private int wishes_count;
        private String visit_tip;
        private String address;
        private String topic;
        private String introduce;
        private int user_activities_count;
        private int icon_type;
        private int price;
        private PhotoItem photo;
        private DistrictBean district;
        private DestinationBean destination;
        private Object activity_category;
        private Object wiki_page;
        private List<?> wiki_pages;
        private List<ActivityCollectionsBean> activity_collections;
        private List<PoisBean> pois;

        public boolean isWished() {
            return wished;
        }

        public void setWished(boolean wished) {
            this.wished = wished;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWishes_count() {
            return wishes_count;
        }

        public void setWishes_count(int wishes_count) {
            this.wishes_count = wishes_count;
        }

        public String getVisit_tip() {
            return visit_tip;
        }

        public void setVisit_tip(String visit_tip) {
            this.visit_tip = visit_tip;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getUser_activities_count() {
            return user_activities_count;
        }

        public void setUser_activities_count(int user_activities_count) {
            this.user_activities_count = user_activities_count;
        }

        public int getIcon_type() {
            return icon_type;
        }

        public void setIcon_type(int icon_type) {
            this.icon_type = icon_type;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public PhotoItem getPhoto() {
            return photo;
        }

        public void setPhoto(PhotoItem photo) {
            this.photo = photo;
        }

        public DistrictBean getDistrict() {
            return district;
        }

        public void setDistrict(DistrictBean district) {
            this.district = district;
        }

        public DestinationBean getDestination() {
            return destination;
        }

        public void setDestination(DestinationBean destination) {
            this.destination = destination;
        }

        public Object getActivity_category() {
            return activity_category;
        }

        public void setActivity_category(Object activity_category) {
            this.activity_category = activity_category;
        }

        public Object getWiki_page() {
            return wiki_page;
        }

        public void setWiki_page(Object wiki_page) {
            this.wiki_page = wiki_page;
        }

        public List<?> getWiki_pages() {
            return wiki_pages;
        }

        public void setWiki_pages(List<?> wiki_pages) {
            this.wiki_pages = wiki_pages;
        }

        public List<ActivityCollectionsBean> getActivity_collections() {
            return activity_collections;
        }

        public void setActivity_collections(List<ActivityCollectionsBean> activity_collections) {
            this.activity_collections = activity_collections;
        }

        public List<PoisBean> getPois() {
            return pois;
        }

        public void setPois(List<PoisBean> pois) {
            this.pois = pois;
        }

        public static class PhotoItem {
            private int width;
            private int height;
            private String photo_url;

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

            public String getPhoto_url() {
                return photo_url;
            }

            public void setPhoto_url(String photo_url) {
                this.photo_url = photo_url;
            }
        }

        public static class DistrictBean {
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

        public static class DestinationBean {
            private int id;
            private double lat;
            private double lng;
            private int district_id;
            private int parent_id;
            private String name;
            private String name_en;
            private String name_pinyin;
            private int score;
            private int level;
            private String path;
            private boolean published;
            private boolean is_in_china;
            private int inspiration_activities_count;
            private int activity_collections_count;
            private int wishes_count;
            private Object wiki_destination_id;
            private String photo_url;
            private String title;
            private String description;
            private String tip;
            private String time_cost;
            private Object wiki_page_id;
            private boolean has_airport;
            private String visit_tip;
            private boolean is_top_destination;
            private boolean is_in_grouping;
            private String alias_name;
            private String travel_tip;
            private PhotoBean photo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getDistrict_id() {
                return district_id;
            }

            public void setDistrict_id(int district_id) {
                this.district_id = district_id;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
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

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
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

            public int getInspiration_activities_count() {
                return inspiration_activities_count;
            }

            public void setInspiration_activities_count(int inspiration_activities_count) {
                this.inspiration_activities_count = inspiration_activities_count;
            }

            public int getActivity_collections_count() {
                return activity_collections_count;
            }

            public void setActivity_collections_count(int activity_collections_count) {
                this.activity_collections_count = activity_collections_count;
            }

            public int getWishes_count() {
                return wishes_count;
            }

            public void setWishes_count(int wishes_count) {
                this.wishes_count = wishes_count;
            }

            public Object getWiki_destination_id() {
                return wiki_destination_id;
            }

            public void setWiki_destination_id(Object wiki_destination_id) {
                this.wiki_destination_id = wiki_destination_id;
            }

            public String getPhoto_url() {
                return photo_url;
            }

            public void setPhoto_url(String photo_url) {
                this.photo_url = photo_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }

            public String getTime_cost() {
                return time_cost;
            }

            public void setTime_cost(String time_cost) {
                this.time_cost = time_cost;
            }

            public Object getWiki_page_id() {
                return wiki_page_id;
            }

            public void setWiki_page_id(Object wiki_page_id) {
                this.wiki_page_id = wiki_page_id;
            }

            public boolean isHas_airport() {
                return has_airport;
            }

            public void setHas_airport(boolean has_airport) {
                this.has_airport = has_airport;
            }

            public String getVisit_tip() {
                return visit_tip;
            }

            public void setVisit_tip(String visit_tip) {
                this.visit_tip = visit_tip;
            }

            public boolean isIs_top_destination() {
                return is_top_destination;
            }

            public void setIs_top_destination(boolean is_top_destination) {
                this.is_top_destination = is_top_destination;
            }

            public boolean isIs_in_grouping() {
                return is_in_grouping;
            }

            public void setIs_in_grouping(boolean is_in_grouping) {
                this.is_in_grouping = is_in_grouping;
            }

            public String getAlias_name() {
                return alias_name;
            }

            public void setAlias_name(String alias_name) {
                this.alias_name = alias_name;
            }

            public String getTravel_tip() {
                return travel_tip;
            }

            public void setTravel_tip(String travel_tip) {
                this.travel_tip = travel_tip;
            }

            public PhotoBean getPhoto() {
                return photo;
            }

            public void setPhoto(PhotoBean photo) {
                this.photo = photo;
            }

            public static class PhotoBean {
                private int id;
                private int width;
                private int height;
                private String url;
                private int file_size;
                private String photo_url;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
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

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getFile_size() {
                    return file_size;
                }

                public void setFile_size(int file_size) {
                    this.file_size = file_size;
                }

                public String getPhoto_url() {
                    return photo_url;
                }

                public void setPhoto_url(String photo_url) {
                    this.photo_url = photo_url;
                }
            }
        }

        public static class ActivityCollectionsBean {
            private int id;
            private String topic;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTopic() {
                return topic;
            }

            public void setTopic(String topic) {
                this.topic = topic;
            }
        }

        public static class PoisBean {
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
            private String location_name;
            private double blat;
            private double blng;
            private double youji_lat;
            private double youji_lng;
            private int youji_poi_id;
            private String youji_poi_name;
            private boolean is_in_china;
            private String local_name;
            private String local_address_name;

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

            public String getLocation_name() {
                return location_name;
            }

            public void setLocation_name(String location_name) {
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

            public String getLocal_name() {
                return local_name;
            }

            public void setLocal_name(String local_name) {
                this.local_name = local_name;
            }

            public String getLocal_address_name() {
                return local_address_name;
            }

            public void setLocal_address_name(String local_address_name) {
                this.local_address_name = local_address_name;
            }
        }
    }
}
