package com.example.avi.myapplication;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import de.timroes.axmlrpc.XMLRPCClient;
import de.timroes.axmlrpc.XMLRPCException;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ConnectIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.avi.myapplication.action.FOO";
    private static final String ACTION_BAZ = "com.example.avi.myapplication.action.BAZ";

    private final String TAG = "IntentServiceLogs";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.avi.myapplication.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.avi.myapplication.extra.PARAM2";

    public ConnectIntentService() {
        super("ConnectIntentService");
    }

    //TODO начало примера

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    //TODO конец примера

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ConnectIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ConnectIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        HashMap hashMap1;
        int tm = intent.getIntExtra("time", 0);
        String label = intent.getStringExtra("task");
        Log.d(TAG, "onHandleIntent start: " + label);
        try {
            TimeUnit.SECONDS.sleep(tm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onHandleIntent end: " + label);
        // http://192.168.56.103:8000/rpc2

        XMLRPCClient client = null;
        try {
            client = new XMLRPCClient(new URL("http://192.168.56.103:8000/rpc2"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        client.setTimeout(5); //5 sec
        try {
            hashMap1 = (HashMap)client.call("getControlsMap");
            for (Object key : hashMap1.keySet()) {
                System.out.println(key + "=" + hashMap1.get(key));
            }
        } catch (XMLRPCException e) {
            e.printStackTrace();
        }



    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
