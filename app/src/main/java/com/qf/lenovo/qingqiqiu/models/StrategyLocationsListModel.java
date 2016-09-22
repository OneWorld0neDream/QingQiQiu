package com.qf.lenovo.qingqiqiu.models;

import java.util.List;

/**
 * Created by 31098 on 9/20/2016.
 */
public class StrategyLocationsListModel {
    private String message;
    private int status;
    private List<StragegyDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> data;

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

    public List<StragegyDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> getData() {
        return data;
    }

    public void setData(List<StragegyDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> data) {
        this.data = data;
    }
}
