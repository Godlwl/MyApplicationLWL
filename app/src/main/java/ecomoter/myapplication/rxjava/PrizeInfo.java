package ecomoter.myapplication.rxjava;

import java.util.List;

/**
 * Created by lwl on 2017/6/15.
 * Describe:
 */

public class PrizeInfo {
    /**
     * status : 1
     * data : {"prizeId":"374","prizeSmallImgUrl":"http://61.144.248.2:9090/upload/raffleproduct/20160822/1/10508492090436400.png","prizeName":"心肺运动跑步机","type":"2","prizeNo":"16041423170","state":"4","totalSum":2639,"currentSum":2327,"minSum":1,"unitPrice":10,"resultTime":null,"startDate":"2016-04-18 00:00:00","secondsDifference":0,"converUnit":10,"hasBuyTag":2,"prizeResultInfo":"","buyStock":0,"buyPrice":null,"expressPrice":null,"deliveryMode":null,"companyList":[],"prizeBigImgUrl":"http://61.144.248.2:9090/upload/raffleproduct/20160414/2/1379658153248900.png","collectionSum":21,"prizeDesc":null,"prizeInfo":"http://61.144.248.2:9090/upload/kindeditor/html/raffleproduct/20160414/1471853890421.html","strategyUrl":"","oldPrice":2689,"secondsCountdown":0,"fullDate":null,"shanghaiIndex":"","shenzhenIndex":"","indexDate":null,"coinRegionShow":1,"winnerlist":[]}
     */

    private int status;
    /**
     * prizeId : 374
     * prizeSmallImgUrl : http://61.144.248.2:9090/upload/raffleproduct/20160822/1/10508492090436400.png
     * prizeName : 心肺运动跑步机
     * type : 2
     * prizeNo : 16041423170
     * state : 4
     * totalSum : 2639
     * currentSum : 2327
     * minSum : 1
     * unitPrice : 10
     * resultTime : null
     * startDate : 2016-04-18 00:00:00
     * secondsDifference : 0
     * converUnit : 10
     * hasBuyTag : 2
     * prizeResultInfo :
     * buyStock : 0
     * buyPrice : null
     * expressPrice : null
     * deliveryMode : null
     * companyList : []
     * prizeBigImgUrl : http://61.144.248.2:9090/upload/raffleproduct/20160414/2/1379658153248900.png
     * collectionSum : 21
     * prizeDesc : null
     * prizeInfo : http://61.144.248.2:9090/upload/kindeditor/html/raffleproduct/20160414/1471853890421.html
     * strategyUrl :
     * oldPrice : 2689
     * secondsCountdown : 0
     * fullDate : null
     * shanghaiIndex :
     * shenzhenIndex :
     * indexDate : null
     * coinRegionShow : 1
     * winnerlist : []
     */

    private DataEntity data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        private String prizeId;
        private String prizeSmallImgUrl;
        private String prizeName;
        private String type;
        private String prizeNo;
        private String state;
        private int totalSum;
        private int currentSum;
        private int minSum;
        private int unitPrice;
        private Object resultTime;
        private String startDate;
        private int secondsDifference;
        private int converUnit;
        private int hasBuyTag;
        private String prizeResultInfo;
        private int buyStock;
        private Object buyPrice;
        private Object expressPrice;
        private Object deliveryMode;
        private String prizeBigImgUrl;
        private int collectionSum;
        private Object prizeDesc;
        private String prizeInfo;
        private String strategyUrl;
        private int oldPrice;
        private int secondsCountdown;
        private Object fullDate;
        private String shanghaiIndex;
        private String shenzhenIndex;
        private Object indexDate;
        private int coinRegionShow;
        private List<?> companyList;
        private List<?> winnerlist;

        public String getPrizeId() {
            return prizeId;
        }

        public void setPrizeId(String prizeId) {
            this.prizeId = prizeId;
        }

        public String getPrizeSmallImgUrl() {
            return prizeSmallImgUrl;
        }

        public void setPrizeSmallImgUrl(String prizeSmallImgUrl) {
            this.prizeSmallImgUrl = prizeSmallImgUrl;
        }

        public String getPrizeName() {
            return prizeName;
        }

        public void setPrizeName(String prizeName) {
            this.prizeName = prizeName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPrizeNo() {
            return prizeNo;
        }

        public void setPrizeNo(String prizeNo) {
            this.prizeNo = prizeNo;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getTotalSum() {
            return totalSum;
        }

        public void setTotalSum(int totalSum) {
            this.totalSum = totalSum;
        }

        public int getCurrentSum() {
            return currentSum;
        }

        public void setCurrentSum(int currentSum) {
            this.currentSum = currentSum;
        }

        public int getMinSum() {
            return minSum;
        }

        public void setMinSum(int minSum) {
            this.minSum = minSum;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public Object getResultTime() {
            return resultTime;
        }

        public void setResultTime(Object resultTime) {
            this.resultTime = resultTime;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public int getSecondsDifference() {
            return secondsDifference;
        }

        public void setSecondsDifference(int secondsDifference) {
            this.secondsDifference = secondsDifference;
        }

        public int getConverUnit() {
            return converUnit;
        }

        public void setConverUnit(int converUnit) {
            this.converUnit = converUnit;
        }

        public int getHasBuyTag() {
            return hasBuyTag;
        }

        public void setHasBuyTag(int hasBuyTag) {
            this.hasBuyTag = hasBuyTag;
        }

        public String getPrizeResultInfo() {
            return prizeResultInfo;
        }

        public void setPrizeResultInfo(String prizeResultInfo) {
            this.prizeResultInfo = prizeResultInfo;
        }

        public int getBuyStock() {
            return buyStock;
        }

        public void setBuyStock(int buyStock) {
            this.buyStock = buyStock;
        }

        public Object getBuyPrice() {
            return buyPrice;
        }

        public void setBuyPrice(Object buyPrice) {
            this.buyPrice = buyPrice;
        }

        public Object getExpressPrice() {
            return expressPrice;
        }

        public void setExpressPrice(Object expressPrice) {
            this.expressPrice = expressPrice;
        }

        public Object getDeliveryMode() {
            return deliveryMode;
        }

        public void setDeliveryMode(Object deliveryMode) {
            this.deliveryMode = deliveryMode;
        }

        public String getPrizeBigImgUrl() {
            return prizeBigImgUrl;
        }

        public void setPrizeBigImgUrl(String prizeBigImgUrl) {
            this.prizeBigImgUrl = prizeBigImgUrl;
        }

        public int getCollectionSum() {
            return collectionSum;
        }

        public void setCollectionSum(int collectionSum) {
            this.collectionSum = collectionSum;
        }

        public Object getPrizeDesc() {
            return prizeDesc;
        }

        public void setPrizeDesc(Object prizeDesc) {
            this.prizeDesc = prizeDesc;
        }

        public String getPrizeInfo() {
            return prizeInfo;
        }

        public void setPrizeInfo(String prizeInfo) {
            this.prizeInfo = prizeInfo;
        }

        public String getStrategyUrl() {
            return strategyUrl;
        }

        public void setStrategyUrl(String strategyUrl) {
            this.strategyUrl = strategyUrl;
        }

        public int getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(int oldPrice) {
            this.oldPrice = oldPrice;
        }

        public int getSecondsCountdown() {
            return secondsCountdown;
        }

        public void setSecondsCountdown(int secondsCountdown) {
            this.secondsCountdown = secondsCountdown;
        }

        public Object getFullDate() {
            return fullDate;
        }

        public void setFullDate(Object fullDate) {
            this.fullDate = fullDate;
        }

        public String getShanghaiIndex() {
            return shanghaiIndex;
        }

        public void setShanghaiIndex(String shanghaiIndex) {
            this.shanghaiIndex = shanghaiIndex;
        }

        public String getShenzhenIndex() {
            return shenzhenIndex;
        }

        public void setShenzhenIndex(String shenzhenIndex) {
            this.shenzhenIndex = shenzhenIndex;
        }

        public Object getIndexDate() {
            return indexDate;
        }

        public void setIndexDate(Object indexDate) {
            this.indexDate = indexDate;
        }

        public int getCoinRegionShow() {
            return coinRegionShow;
        }

        public void setCoinRegionShow(int coinRegionShow) {
            this.coinRegionShow = coinRegionShow;
        }

        public List<?> getCompanyList() {
            return companyList;
        }

        public void setCompanyList(List<?> companyList) {
            this.companyList = companyList;
        }

        public List<?> getWinnerlist() {
            return winnerlist;
        }

        public void setWinnerlist(List<?> winnerlist) {
            this.winnerlist = winnerlist;
        }
    }
}
