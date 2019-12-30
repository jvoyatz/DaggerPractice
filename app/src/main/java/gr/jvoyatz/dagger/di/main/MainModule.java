package gr.jvoyatz.dagger.di.main;

import dagger.Module;
import dagger.Provides;
import gr.jvoyatz.dagger.network.main.MainApi;
import gr.jvoyatz.dagger.ui.main.posts.PostRecyclerAdapter;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static PostRecyclerAdapter provideAdapter(){
        return new PostRecyclerAdapter();
    }
    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}
