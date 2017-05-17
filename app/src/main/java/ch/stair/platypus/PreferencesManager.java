package ch.stair.platypus;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static final String PREF_NAME = BuildConfig.APPLICATION_ID + ".PREFS";

    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setValue(KEYS key, long value) {
        mPref.edit()
                .putLong(key.toString(), value)
                .commit();
    }

    public void setValue(KEYS key, String value) {
        mPref.edit()
                .putString(key.toString(), value)
                .commit();
    }

    public long getLongValue(KEYS key) {
        return mPref.getLong(key.toString(), 0);
    }

    public String getStringValue(KEYS key) {
        return mPref.getString(key.toString(), "");
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }


    public enum KEYS {
        LAST_SYNC("LASTSYNC");

        private final String value;

        KEYS(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return BuildConfig.APPLICATION_ID + this.value;
        }
    }
}
