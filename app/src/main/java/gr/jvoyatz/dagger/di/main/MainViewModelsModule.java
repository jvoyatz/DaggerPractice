package gr.jvoyatz.dagger.di.main;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import gr.jvoyatz.dagger.di.ViewModelKey;
import gr.jvoyatz.dagger.ui.main.posts.PostsViewModel;
import gr.jvoyatz.dagger.ui.main.profile.ProfileViewModel;

@Module
public abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModel(PostsViewModel viewModel);
}
