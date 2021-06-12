package com.aumaid.jobskash.Database;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    public static final String USER_LOGIN_SESSION = "userLogInSession";
    public static final String REMEMBER_ME_SESSION = "rememberMeSession";

    private static final String IS_LOGGED_IN = "LOGGED_IN";

    /*
    * REMEMBER ME SESSION VARIABLES
    * */
    private static final String IS_REMEMBER_ME = "REMEMBER_ME";
    public static String REMEMBER_ME_SESSION_KEY_USER_NAME = "userName";
    public static String REMEMBER_ME_SESSION_KEY_PASSWORD = "password";


    /*
    * USER LOG IN SESSION VARIABLES
    * */

    public static String KEY_FULL_NAME = "fullName";
    public static String KEY_USER_NAME = "userName";
    public static String KEY_EMAIL = "email";
    public static String KEY_PASSWORD = "password";
    public static String KEY_GENDER = "gender";
    public static String KEY_DATE_OF_BIRTH = "date";
    public static String KEY_PHONE_NUMBER = "phoneNumber";

    public SessionManager(Context _context, String sessionName) {
        context = _context;
        userSession = context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = userSession.edit();

    }

    /*Log in sessions*/

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

    public HashMap<String, String> getUserDataFromUserLogInSession() {
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

    /*
    * Remember Me session Details
    * */

    public void createRememberMeSession(String userName, String password) {

        editor.putBoolean(IS_REMEMBER_ME, true);

        editor.putString(REMEMBER_ME_SESSION_KEY_USER_NAME, userName);
        editor.putString(REMEMBER_ME_SESSION_KEY_PASSWORD, password);

        editor.commit();
    }

    public HashMap<String, String > getUserDataFromRememberMeSession(){

        HashMap<String, String > userData = new HashMap<>();

        userData.put(REMEMBER_ME_SESSION_KEY_USER_NAME, null);
        userData.put(REMEMBER_ME_SESSION_KEY_PASSWORD, null);

        return userData;

    }

    public boolean checkIsRememberMe() {
        return userSession.getBoolean(IS_REMEMBER_ME, false);
    }
}
