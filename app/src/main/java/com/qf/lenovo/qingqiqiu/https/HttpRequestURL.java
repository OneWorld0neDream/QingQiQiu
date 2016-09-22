package com.qf.lenovo.qingqiqiu.https;

/**
 * Created by lenovo on 2016/9/20.
 */
public interface HttpRequestURL {
    String STRATEGY_ADVERSEMENT_URL = "http://q.chanyouji.com/api/v1/adverts.json";
    String STRATEGY_GLOBAL_DESTINATIONS_URL = "http://q.chanyouji.com/api/v2/destinations.json";
    String STRATEGY_NEARBY_LOCATIONS_URL = "http://q.chanyouji.com/api/v2/destinations/nearby.json";
    String STRATEGY_GLOBAL_LOCATIONS_URL = "http://q.chanyouji.com/api/v2/destinations/list.json";

    String STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LAT = "lat";
    String STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LNG = "lng";
    String STRATEGY_OTHER_LOCATIONS_REQUEST_PARAM_AREA = "area";
    String STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_RECOMMAND = "recommend";
    String STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_ID = "id";

}
