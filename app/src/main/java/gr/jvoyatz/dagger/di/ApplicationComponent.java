package gr.jvoyatz.dagger.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import gr.jvoyatz.dagger.BaseApplication;
import gr.jvoyatz.dagger.SessionManager;

@Singleton
@Component(modules = {
            AndroidSupportInjectionModule.class,
            ActivityBuilderModule.class,
            AppModule.class,
            ViewModelFactoryModule.class})
public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

    SessionManager sessionManager();

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
