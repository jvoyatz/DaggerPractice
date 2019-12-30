package gr.jvoyatz.dagger.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gr.jvoyatz.dagger.R;
import gr.jvoyatz.dagger.models.User;
import gr.jvoyatz.dagger.util.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


    }
    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application){
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }
    @Provides
    static String someString(){
        return "this is a test string";
    }

    @Provides
    static boolean getApp(Application application){
        return application == null;
    }

    @Singleton
    @Provides
    @Named("app_user")
    static User someUser(){
        return new User();
    }
}
