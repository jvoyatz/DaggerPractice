package gr.jvoyatz.dagger.di.auth;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import gr.jvoyatz.dagger.di.ViewModelKey;
import gr.jvoyatz.dagger.ui.auth.AuthViewModel;

@Module
public abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel model);
}
