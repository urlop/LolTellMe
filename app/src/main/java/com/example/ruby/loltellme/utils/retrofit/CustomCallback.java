package com.example.ruby.loltellme.utils.retrofit;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.widget.Toast;

import com.example.ruby.loltellme.R;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Ruby on 27/03/2016.
 */
public class CustomCallback<T> implements Callback<T> {

    private final Context context;
    private final ObjectAnimator animator;

    public CustomCallback(Context context, ObjectAnimator animator) {
        this.context = context;
        this.animator = animator;
    }

    @Override
    public void onResponse(Response<T> response, Retrofit retrofit) {
    }

    @Override
    public void onFailure(Throwable t) {
        //END ANIMATION
        animator.setIntValues(0);
        animator.removeAllListeners();
        animator.end();
        animator.cancel();

        if (context != null) {
            Toast.makeText(context, context.getString(R.string.request_error_internet), Toast.LENGTH_LONG).show();
        }
    }
}
