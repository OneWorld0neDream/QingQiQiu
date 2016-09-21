package com.qf.lenovo.qingqiqiu.models;

import java.util.List;

/**
 * Created by 31098 on 9/20/2016.
 */
public class StrategyNearbyLocationsListModel {
    private String message;
    private int status;
    private List<StragegyOtherDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> data;

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

    public List<StragegyOtherDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> getData() {
        return data;
    }

    public void setData(List<StragegyOtherDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> data) {
        this.data = data;
    }
}
