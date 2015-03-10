package ctprojects.firebase_prototype;

/**
 * Created by christophe on 3/9/2015.
 */
public interface PaymentForm {
    public String getCardNumber();
    public String getCvc();
    public Integer getExpMonth();
    public Integer getExpYear();
}
