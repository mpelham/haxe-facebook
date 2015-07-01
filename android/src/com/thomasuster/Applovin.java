package com.thomasuster;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import org.haxe.extension.Extension;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.util.Map;

import java.util.Calendar;

public class Applovin extends Extension {

    static AppLovinIncentivizedInterstitial incent;
    static ApplovinShowModel showModel;

    public Applovin() {}

    public void onCreate(Bundle savedInstanceState) {
    }

    public static void init() {
        AppLovinSdk.initializeSdk(mainContext);
    }

    public static int isReady() {
        if(incent == null)
            return 0;
        if(incent.isAdReadyToDisplay())
            return 1;
        return 0;
    }

    public static void preload() {
        if(incent == null) {
            incent = AppLovinIncentivizedInterstitial.create(mainActivity);
            incent.preload(new AppLovinAdLoadListener() {
                @Override
                public void adReceived(AppLovinAd appLovinAd) {
                }
                @Override
                public void failedToReceiveAd(int errorCode) {
                    incent = null;
                }
            });
        }
    }

    public static void show() {
        showModel = new ApplovinShowModel();
        System.out.println("show");
        incent.show(mainActivity, new AppLovinAdRewardListener() {
            @Override
            public void userRewardVerified(AppLovinAd appLovinAd, Map map)
            {
                System.out.println("userRewardVerified");
                showModel.didSucceed = 1;
                incent = null;
            }

            @Override
            public void userOverQuota(AppLovinAd appLovinAd, Map map)
            {
                System.out.println("userOverQuota");
                showModel.didFailWithError = 1;
                incent = null;
            }

            @Override
            public void userRewardRejected(AppLovinAd appLovinAd, Map map)
            {
                System.out.println("userRewardRejected");
                showModel.wasRejected = 1;
                incent = null;
            }

            @Override
            public void validationRequestFailed(AppLovinAd appLovinAd, int errorCode)
            {
                System.out.println("validationRequestFailed");
                showModel.didFailWithError = 1;
                incent = null;
            }

            @Override
            public void userDeclinedToViewAd(AppLovinAd appLovinAd)
            {
                System.out.println("userDeclinedToViewAd");
                showModel.didFailWithError = 1;
                incent = null;
            }
        }, null, new AppLovinAdDisplayListener() {
            @Override
            public void adDisplayed(AppLovinAd appLovinAd) {
                // A rewarded video is being displayed.
            }
            @Override
            public void adHidden(AppLovinAd appLovinAd) {
                // A rewarded video was closed.  Preload the next video now.  We won't use a load listener.
            }
        });
    }

    public static int didSucceed() {
        return showModel.didSucceed;
    }
    public static int wasRejected() {
        return showModel.wasRejected;
    }
    public static int didFailWithError() {
        return showModel.didFailWithError;
    }

}