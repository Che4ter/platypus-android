package ch.stair.platypus.repository;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import javax.inject.Inject;

import ch.stair.platypus.BuildConfig;

public class PreferencesRepository {
    private static final String PREF_NAME = BuildConfig.APPLICATION_ID + ".PREFS";

    private final SharedPreferences mPref;

    @Inject
    public PreferencesRepository(final Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public Date getLastSyncDate() {
        return new Date(this.getLongValue(KEYS.LAST_SYNC));
    }

    public void saveLastSyncDate(Date lastSync) {
        this.setValue(KEYS.LAST_SYNC, lastSync.getTime());
    }

    private void setValue(KEYS key, long value) {
        mPref.edit()
                .putLong(key.toString(), value)
                .commit();
    }

    private long getLongValue(KEYS key) {
        return mPref.getLong(key.toString(), 0);
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
