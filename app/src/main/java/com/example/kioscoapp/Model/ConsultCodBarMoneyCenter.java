package com.example.kioscoapp.Model;

import java.util.List;

public class ConsultCodBarMoneyCenter {
    private String responseCode;
    private String expirantionDateCodBar;
    private List<CodBars> CodBars;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getExpirantionDateCodBar() {
        return expirantionDateCodBar;
    }

    public void setExpirantionDateCodBar(String expirantionDateCodBar) {
        this.expirantionDateCodBar = expirantionDateCodBar;
    }

    public List<ConsultCodBarMoneyCenter.CodBars> getCodBars() {
        return CodBars;
    }

    public void setCodBars(List<ConsultCodBarMoneyCenter.CodBars> codBars) {
        CodBars = codBars;
    }

    public class CodBars {
        private double serviceType;
        private String barcodeimg;
        private double addresRequired;
        private String idBarcode;

        public double getServiceType() {
            return serviceType;
        }

        public void setServiceType(double serviceType) {
            this.serviceType = serviceType;
        }

        public String getBarcodeimg() {
            return barcodeimg;
        }

        public void setBarcodeimg(String barcodeimg) {
            this.barcodeimg = barcodeimg;
        }

        public double getAddresRequired() {
            return addresRequired;
        }

        public void setAddresRequired(double addresRequired) {
            this.addresRequired = addresRequired;
        }

        public String getIdBarcode() {
            return idBarcode;
        }

        public void setIdBarcode(String idBarcode) {
            this.idBarcode = idBarcode;
        }
    }

}

