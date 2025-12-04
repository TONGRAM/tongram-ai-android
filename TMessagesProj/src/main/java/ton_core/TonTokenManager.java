package ton_core;

import android.content.Context;
import android.content.SharedPreferences;

public class TonTokenManager {

    private static final String PREF_NAME = "app_prefs";
    private static final String KEY_ACCESS_TOKEN = "access_token";

    private static TonTokenManager INSTANCE;
    private final SharedPreferences prefs;

    private TonTokenManager(Context context) {
        prefs = context.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized TonTokenManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TonTokenManager(context);
        }
        return INSTANCE;
    }

    public void saveToken(String token) {
        prefs.edit().putString(KEY_ACCESS_TOKEN, token).apply();
    }

    public String getToken() {
        return prefs.getString(KEY_ACCESS_TOKEN, null);
    }

    public void clear() {
        prefs.edit().remove(KEY_ACCESS_TOKEN).apply();
    }
}
