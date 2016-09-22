package com.qf.lenovo.qingqiqiu.models;

import java.util.List;

/**
 * Created by 31098 on 9/21/2016.
 */

public class StragegyDestinationsListModel {

    private String message;
    private int status;
    private List<DestinationLocationsList> data;

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

    public List<DestinationLocationsList> getData() {
        return data;
    }

    public void setData(List<DestinationLocationsList> data) {
        this.data = data;
    }

    public static class DestinationLocationsList {
        private String name;
        private String button_text;
        private String region;
        private List<DestinationsLocationItem> destinations;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getButton_text() {
            return button_text;
        }

        public void setButton_text(String button_text) {
            this.button_text = button_text;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public List<DestinationsLocationItem> getDestinations() {
            return destinations;
        }

        public void setDestinations(List<DestinationsLocationItem> destinations) {
            this.destinations = destinations;
        }

        public static class DestinationsLocationItem {
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
            private Object alias_name;
            private Object travel_tip;
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

            public Object getAlias_name() {
                return alias_name;
            }

            public void setAlias_name(Object alias_name) {
                this.alias_name = alias_name;
            }

            public Object getTravel_tip() {
                return travel_tip;
            }

            public void setTravel_tip(Object travel_tip) {
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
    }
}
