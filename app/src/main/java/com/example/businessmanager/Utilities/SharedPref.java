package com.example.businessmanager.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref
{
    private static final String KEY_ACCESS_LEVEL = "access_level";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String PREF_NAME = "welcome";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    public SharedPref(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public String getAccessToken() {
        return pref.getString(KEY_ACCESS_TOKEN, null);
    }
    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getKeyAccessLevel()
    {
        return pref.getString(KEY_ACCESS_LEVEL,null);
    }
    public void setKeyAccessLevel(String accessLevel)
    {
        editor.putString(KEY_ACCESS_LEVEL,accessLevel);
        editor.commit();
    }
}
