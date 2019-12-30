package gr.jvoyatz.dagger.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import gr.jvoyatz.dagger.SessionManager;
import gr.jvoyatz.dagger.models.User;
import gr.jvoyatz.dagger.ui.auth.AuthResource;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private SessionManager sessionManager;
    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: viewmodel is ready...");
    }

    public LiveData<AuthResource<User>> getAutheticatedUser(){
        return sessionManager.getAuthUser();
    }
}
