package ctprojects.firebase_prototype;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ctprojects.firebase_prototype.log.LogWrapper;


public class MapActivity extends Activity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    protected GoogleApiClient mGoogleApiClient;
    protected MapFragment mMapFragment;
    protected LocationRequest mLocationRequest;
    protected GoogleMap map;
    protected LatLng myPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        buildGoogleApiClient();

        mMapFragment= (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
        map = mMapFragment.getMap();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("Our house");
        markerOptions.position(new LatLng(39.159830, -84.409532));
        map.addMarker(markerOptions);

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, false);
        Location location = service.getLastKnownLocation(provider);
        if(location!=null) {
            myPosition = new LatLng(location.getLatitude(), location.getLongitude());
            CircleOptions circleOptions = new CircleOptions();
            circleOptions.center(myPosition);
            circleOptions.radius(Constants.CERCLE_RADIUS);
            map.addCircle(circleOptions);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition,
                    Constants.INITIAL_MAP_ZOOM));
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true);

    }

    protected void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000); // Update location every second

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

        Location location = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        myPosition= new LatLng(location.getLatitude(), location.getLongitude());
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(myPosition);
        circleOptions.radius(1000);
        map.addCircle(circleOptions);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition,
                Constants.INITIAL_MAP_ZOOM));

    }

    @Override
    public void onConnectionSuspended(int i) {
        LogWrapper.info(this.getClass(), "GoogleApiClient connection has been suspend");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        LogWrapper.info(this.getClass(), "GoogleApiClient connection has failed");
    }


    @Override
    public void onLocationChanged(Location location) {
        myPosition= new LatLng(location.getLatitude(), location.getLongitude());
        if(myPosition!=null) {

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition,
                    Constants.INITIAL_MAP_ZOOM));
        }
    }

//    private void logout() {
//        if (this.mAuthData != null) {
//            /* logout of Firebase */
//            mFirebaseRef.unauth();
//            /* Logout of any of the Frameworks. This step is optional, but ensures the user is not logged into
//             * Facebook/Google+ after logging out of Firebase. */
//            if (this.mAuthData.getProvider().equals("facebook")) {
//                /* Logout from Facebook */
//                Session session = Session.getActiveSession();
//                if (session != null) {
//                    if (!session.isClosed()) {
//                        session.closeAndClearTokenInformation();
//                    }
//                } else {
//                    session = new Session(getApplicationContext());
//                    Session.setActiveSession(session);
//                    session.closeAndClearTokenInformation();
//                }
//            } else if (this.mAuthData.getProvider().equals("google")) {
//                /* Logout from Google+ */
//                if (mGoogleApiClient.isConnected()) {
//                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
//                    mGoogleApiClient.disconnect();
//                }
//            }
//            /* Update authenticated user and show login buttons */
//            setAuthenticatedUser(null);
//        }
//    }
}
