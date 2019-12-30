package gr.jvoyatz.dagger.di.auth;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gr.jvoyatz.dagger.models.User;
import gr.jvoyatz.dagger.network.auth.AuthApi;
import retrofit2.Retrofit;

@Module
public abstract class AuthModule {
    @AuthScope
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }

    @AuthScope
    @Provides
    @Named("auth_user")
    static User someUser(){
        return new User();
    }
}
