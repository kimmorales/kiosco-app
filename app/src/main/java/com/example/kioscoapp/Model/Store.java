package com.example.kioscoapp.Model;

import java.lang.reflect.Parameter;
import java.util.List;

public class Store {
    private String name;
    private long id;
    private String email;
    private StoreFormat format;
    private String storeId;
    private String storeFormatId;
    private String storeName;
    private String storePickup;
    private String countryId;
    private String stateName;
    private String cityName;
    private String cityId;
    private String districtName;
    private String storePickUp;
    private String storeHomeDelivery;
    private List<Parameter> parameters;
    private Country country;
    private double homeDeliveryCost;
    private DeliveryTypes deliveryTypes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StoreFormat getFormat() {
        return format;
    }

    public void setFormat(StoreFormat format) {
        this.format = format;
    }

    public DeliveryTypes getDeliveryTypes() {
        return deliveryTypes;
    }

    public void setDeliveryTypes(DeliveryTypes deliveryTypes) {
        this.deliveryTypes = deliveryTypes;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreFormatId() {
        return storeFormatId;
    }

    public void setStoreFormatId(String storeFormatId) {
        this.storeFormatId = storeFormatId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePickup() {
        return storePickup;
    }

    public void setStorePickup(String storePickup) {
        this.storePickup = storePickup;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getStorePickUp() {
        return storePickUp;
    }

    public void setStorePickUp(String storePickUp) {
        this.storePickUp = storePickUp;
    }

    public String getStoreHomeDelivery() {
        return storeHomeDelivery;
    }

    public void setStoreHomeDelivery(String storeHomeDelivery) {
        this.storeHomeDelivery = storeHomeDelivery;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public double getHomeDeliveryCost() {
        return homeDeliveryCost;
    }

    public void setHomeDeliveryCost(double homeDeliveryCost) {
        this.homeDeliveryCost = homeDeliveryCost;
    }

    public class StoreFormat{
        private long Id;
        private String name;

        public long getId() {
            return Id;
        }

        public void setId(long id) {
            Id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class DeliveryTypes{
        private long id;
        private String deliveryTypeName;
        private boolean addresRequired;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getDeliveryTypeName() {
            return deliveryTypeName;
        }

        public void setDeliveryTypeName(String deliveryTypeName) {
            this.deliveryTypeName = deliveryTypeName;
        }

        public boolean isAddresRequired() {
            return addresRequired;
        }

        public void setAddresRequired(boolean addresRequired) {
            this.addresRequired = addresRequired;
        }
    }
}
