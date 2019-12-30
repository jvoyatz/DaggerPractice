package gr.jvoyatz.dagger;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import gr.jvoyatz.dagger.di.DaggerApplicationComponent;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }
}
