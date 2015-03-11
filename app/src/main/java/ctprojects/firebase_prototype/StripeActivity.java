package ctprojects.firebase_prototype;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.stripe.android.model.Card;


public class StripeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stripe);

        Card card = new Card("4242-4242-4242-4242", 12, 2016, "123");

    }

//    public void saveCreditCard(PaymentForm form) {
//
//        Card card = new Card(
//                form.getCardNumber(),
//                form.getExpMonth(),
//                form.getExpYear(),
//                form.getCvc());
//
//        boolean validation = card.validateCard();
//        if (validation) {
//            startProgress();
//            new Stripe().createToken(
//                    card,
//                    PUBLISHABLE_KEY,
//                    new TokenCallback() {
//                        public void onSuccess(Token token) {
//                            getTokenList().addToList(token);
//                            finishProgress();
//                        }
//                        public void onError(Exception error) {
//                            handleError(error.getLocalizedMessage());
//                            finishProgress();
//                        }
//                    });
//        } else if (!card.validateNumber()) {
//            handleError("The card number that you entered is invalid");
//        } else if (!card.validateExpiryDate()) {
//            handleError("The expiration date that you entered is invalid");
//        } else if (!card.validateCVC()) {
//            handleError("The CVC code that you entered is invalid");
//        } else {
//            handleError("The card details that you entered are invalid");
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stripe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
