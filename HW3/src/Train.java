public class Train extends PublicTransport{
    @Override
    public void calculatePayment() {
        double fare=getBaseFare()+
        setTotalFare();
    }
}
