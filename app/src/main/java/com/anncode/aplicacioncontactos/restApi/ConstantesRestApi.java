package com.anncode.aplicacioncontactos.restApi;

/**
 * Created by anahisalgado on 25/05/16.
 */
public final class ConstantesRestApi {

    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "1367162880.dac59ab.036399a65fb44a62b18f0cc941b7e9cf";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_SEARCH_USERS = "users/search";
    public static final String URL_RECENT_MEDIA_USER = "users/{user_id}/media/recent/";

    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    public static final String URL_POST_LIKES = "media/{idMedia}/likes"+KEY_ACCESS_TOKEN+ACCESS_TOKEN;

    // Follow
    //https://api.instagram.com/v1/users/3302875836/relationship?access_token=1367162880.dac59ab.036399a65fb44a62b18f0cc941b7e9cf
    public static final String URL_FOLLOW = "users/{user_id}/relationship?"+KEY_ACCESS_TOKEN+ACCESS_TOKEN;

}
