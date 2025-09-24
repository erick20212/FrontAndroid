package storage;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenStore {
    private static final String PREF = "auth_prefs";
    private static final String KEY  = "jwt";

    public static void save(Context c, String token){
        SharedPreferences sp = c.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(KEY, token).apply();
    }
    public static String get(Context c){
        return c.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getString(KEY, null);
    }
    public static void clear(Context c){
        c.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit().remove(KEY).apply();
    }
}