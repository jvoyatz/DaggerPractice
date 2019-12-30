package gr.jvoyatz.dagger.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import gr.jvoyatz.dagger.R;
import gr.jvoyatz.dagger.models.User;
import gr.jvoyatz.dagger.ui.auth.AuthResource;
import gr.jvoyatz.dagger.ui.main.MainActivity;
import gr.jvoyatz.dagger.viewmodels.ViewModelProviderFactory;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";

    @Inject
    ViewModelProviderFactory providerFactory;

    private ProfileViewModel viewModel;

    private TextView email, username, website;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Profile fragment", Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: profile fragment was created");
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);
        viewModel = new ViewModelProvider(this, providerFactory).get(ProfileViewModel.class);

        //breaks the mvvm architecture
        //not good its going retrieve data again and again
        //AuthResource<User> value = ((MainActivity) getActivity()).sessionManager.getAuthUser().getValue();
        subscribeObservers();
    }

    private void subscribeObservers(){
        //each fragment has its own lifecycle, it can get destroyed whenever
        //so we must remove the previous observers before adding the new ones
        viewModel.getAutheticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAutheticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null) {
                    switch (userAuthResource.status){
                        case ERROR:
                            setErrorDetails(userAuthResource.message);
                            break;
                        case AUTHENTICATED:
                            setUserDetails(userAuthResource.data);
                            break;
                    }
                }
            }
        });
    }

    private void setUserDetails(User data) {
        email.setText(data.getEmail());
        username.setText(data.getUsername());
        website.setText(data.getWebsite());
    }

    private void setErrorDetails(String message) {
        email.setText(message);
        username.setText("error");
        website.setText("error");
    }
}
