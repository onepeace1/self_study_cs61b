public abstract class PublicTransport implements Payable {
    private String model;
    private double baseFare;
    private double totalFare;

    public void setModel(String model) {
        this.model=model;
    }

    public String getModel() {
        return model;
    }

    public void setBaseFare(double bf) {
        this.baseFare=bf;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public double getTotalFare() {
        return totalFare;
    }



}
