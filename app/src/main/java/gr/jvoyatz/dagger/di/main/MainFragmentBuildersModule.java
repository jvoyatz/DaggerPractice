package gr.jvoyatz.dagger.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import gr.jvoyatz.dagger.ui.main.posts.PostsFragment;
import gr.jvoyatz.dagger.ui.main.profile.ProfileFragment;

@Module
public abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();
}
