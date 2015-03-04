package ctprojects.firebase_prototype;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by christophe on 3/3/2015.
 */
public class FirebasePrototypeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
