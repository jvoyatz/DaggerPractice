package gr.jvoyatz.dagger.di;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import gr.jvoyatz.dagger.viewmodels.ViewModelProviderFactory;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);

    /*
    similar as the method declaration above
    @Provides
    static ViewModelProvider.Factory bindFactory(ViewModelProvider.Factory factory){
        return factory;
    }*/
}
