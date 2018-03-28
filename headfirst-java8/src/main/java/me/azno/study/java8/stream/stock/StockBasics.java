package me.azno.study.java8.stream.stock;

public class StockBasics {
    private String code;//代码
    private String name;//名称
    private String industry;//所属行业
    private String area;//地区
    private double pe;//市盈率
    private double outstanding;//流通股本(亿)
    private double totals;//总股本(亿)
    private double totalAssets;//总资产(万)
    private double liquidAssets;//流动资产
    private double fixedAssets;//固定资产
    private double reserved;//公积金
    private double reservedPerShare;//每股公积金
    private double esp;//每股收益
    private double bvps;//每股净资
    private double pb;//市净率
    private int timeToMarket;//上市日期
    private double undp;//未分利润
    private double perundp;//每股未分配
    private double rev;//收入同比(%)
    private double profit;//利润同比(%)
    private double gpr;//毛利率(%)
    private double npr;//净利润率(%)
    private int holders;//股东人数

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(double outstanding) {
        this.outstanding = outstanding;
    }

    public double getTotals() {
        return totals;
    }

    public void setTotals(double totals) {
        this.totals = totals;
    }

    public double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public double getLiquidAssets() {
        return liquidAssets;
    }

    public void setLiquidAssets(double liquidAssets) {
        this.liquidAssets = liquidAssets;
    }

    public double getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(double fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public double getReserved() {
        return reserved;
    }

    public void setReserved(double reserved) {
        this.reserved = reserved;
    }

    public double getReservedPerShare() {
        return reservedPerShare;
    }

    public void setReservedPerShare(double reservedPerShare) {
        this.reservedPerShare = reservedPerShare;
    }

    public double getEsp() {
        return esp;
    }

    public void setEsp(double esp) {
        this.esp = esp;
    }

    public double getBvps() {
        return bvps;
    }

    public void setBvps(double bvps) {
        this.bvps = bvps;
    }

    public double getPb() {
        return pb;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }

    public int getTimeToMarket() {
        return timeToMarket;
    }

    public void setTimeToMarket(int timeToMarket) {
        this.timeToMarket = timeToMarket;
    }

    public double getUndp() {
        return undp;
    }

    public void setUndp(double undp) {
        this.undp = undp;
    }

    public double getPerundp() {
        return perundp;
    }

    public void setPerundp(double perundp) {
        this.perundp = perundp;
    }

    public double getRev() {
        return rev;
    }

    public void setRev(double rev) {
        this.rev = rev;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getGpr() {
        return gpr;
    }

    public void setGpr(double gpr) {
        this.gpr = gpr;
    }

    public double getNpr() {
        return npr;
    }

    public void setNpr(double npr) {
        this.npr = npr;
    }

    public int getHolders() {
        return holders;
    }

    public void setHolders(int holders) {
        this.holders = holders;
    }

    public static StockBasics get(String[] array){
        return new StockBasics(array);
    }

    private StockBasics(String[] array) {
        this.setCode(array[0]);
        this.setName(array[1]);
        this.setIndustry(array[2]);
        this.setArea(array[3]);
        this.setPe(Double.parseDouble(array[4]));
        this.setOutstanding(Double.parseDouble(array[5]));
        this.setTotals(Double.parseDouble(array[6]));
        this.setTotalAssets(Double.parseDouble(array[7]));
        this.setLiquidAssets(Double.parseDouble(array[8]));
        this.setFixedAssets(Double.parseDouble(array[9]));
        this.setReserved(Double.parseDouble(array[10]));
        this.setReservedPerShare(Double.parseDouble(array[11]));
        this.setEsp(Double.parseDouble(array[12]));
        this.setBvps(Double.parseDouble(array[13]));
        this.setPb(Double.parseDouble(array[14]));
        this.setTimeToMarket(Integer.parseInt(array[15]));
        this.setUndp(Double.parseDouble(array[16]));
        this.setPerundp(Double.parseDouble(array[17]));
        this.setRev(Double.parseDouble(array[18]));
        this.setProfit(Double.parseDouble(array[19]));
        this.setGpr(Double.parseDouble(array[20]));
        this.setNpr(Double.parseDouble(array[21]));
        this.setHolders(Integer.parseInt(array[22].split("\\.")[0]));
    }

    @Override
    public String toString() {
        return "StockBasics{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", industry='" + industry + '\'' +
                ", area='" + area + '\'' +
                ", pe=" + pe +
                ", outstanding=" + outstanding +
                ", totals=" + totals +
                ", totalAssets=" + totalAssets +
                ", liquidAssets=" + liquidAssets +
                ", fixedAssets=" + fixedAssets +
                ", reserved=" + reserved +
                ", reservedPerShare=" + reservedPerShare +
                ", esp=" + esp +
                ", bvps=" + bvps +
                ", pb=" + pb +
                ", timeToMarket=" + timeToMarket +
                ", undp=" + undp +
                ", perundp=" + perundp +
                ", rev=" + rev +
                ", profit=" + profit +
                ", gpr=" + gpr +
                ", npr=" + npr +
                ", holders=" + holders +
                '}';
    }
}
