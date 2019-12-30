package gr.jvoyatz.dagger.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import gr.jvoyatz.dagger.di.auth.AuthModule;
import gr.jvoyatz.dagger.di.auth.AuthScope;
import gr.jvoyatz.dagger.di.auth.AuthViewModelsModule;
import gr.jvoyatz.dagger.di.main.MainFragmentBuildersModule;
import gr.jvoyatz.dagger.di.main.MainModule;
import gr.jvoyatz.dagger.di.main.MainScope;
import gr.jvoyatz.dagger.di.main.MainViewModelsModule;
import gr.jvoyatz.dagger.ui.auth.AuthActivity;
import gr.jvoyatz.dagger.ui.main.MainActivity;

@Module
public abstract class ActivityBuilderModule {
    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class})
    abstract MainActivity contributeMainActivity();

}
