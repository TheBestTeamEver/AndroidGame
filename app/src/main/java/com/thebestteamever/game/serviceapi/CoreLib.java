package com.thebestteamever.game.serviceapi;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;

/**
 * Created by alexss8 on 25.05.2016.
 */
public class CoreLib extends Application {
    private static CoreLib me;

    public CoreLib() {
        me = this;
    }

    public static Context Context() {
        return me;
    }

    public static ContentResolver ContentResolver() {
        return me.getContentResolver();
    }
}