package com.aumaid.jobskash.Database;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGGED_IN = "LOGGED_IN";

    private static String KEY_FULL_NAME = "fullName";
    private static String KEY_USER_NAME = "userName";
    private static String KEY_EMAIL = "email";
    private static String KEY_PASSWORD = "password";
    private static String KEY_GENDER = "gender";
    private static String KEY_DATE_OF_BIRTH = "date";
    private static String KEY_PHONE_NUMBER = "phoneNumber";

    public SessionManager(Context _context) {
        context = _context;
        userSession = context.getSharedPreferences("userLogInSession", Context.MODE_PRIVATE);
        editor = userSession.edit();

    }

    public void createLogInSession(String fullName, String userName, String email, String password, String gender, String date, String phoneNumber) {

        editor.putBoolean(IS_LOGGED_IN, true);

        editor.putString(KEY_FULL_NAME, fullName);
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_DATE_OF_BIRTH, date);
        editor.putString(KEY_PHONE_NUMBER, phoneNumber);

        editor.commit();
    }

    public HashMap<String, String> getUserDataFromSession() {
        HashMap<String, String> userData = new HashMap<>();

        userData.put(KEY_FULL_NAME, userSession.getString(KEY_FULL_NAME, null));
        userData.put(KEY_USER_NAME, userSession.getString(KEY_USER_NAME, null));
        userData.put(KEY_EMAIL, userSession.getString(KEY_EMAIL, null));
        userData.put(KEY_PASSWORD, userSession.getString(KEY_PASSWORD, null));
        userData.put(KEY_GENDER, userSession.getString(KEY_GENDER, null));
        userData.put(KEY_DATE_OF_BIRTH, userSession.getString(KEY_DATE_OF_BIRTH, null));
        userData.put(KEY_PHONE_NUMBER, userSession.getString(KEY_PHONE_NUMBER, null));

        return userData;
    }

    public boolean checkLogIn() {
        return userSession.getBoolean(IS_LOGGED_IN, false);
    }

    public void logOut() {
        editor.clear();
        editor.commit();
    }
}
