package gr.jvoyatz.dagger.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import gr.jvoyatz.dagger.R;
import gr.jvoyatz.dagger.models.Post;
import gr.jvoyatz.dagger.ui.main.Resource;
import gr.jvoyatz.dagger.util.VerticalSpaceItemDecoration;
import gr.jvoyatz.dagger.viewmodels.ViewModelProviderFactory;

public class PostsFragment extends DaggerFragment {

    private static final String TAG = "PostsFragment";

    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    PostRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);

        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);

        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers(){
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if(listResource != null){
                    Log.d(TAG, "onChanged: " + listResource.data);
                    switch (listResource.status){
                        case ERROR:
                            Log.e(TAG, "onChanged: " + listResource.message);
                            break;
                        case LOADING:
                            Log.d(TAG, "onChanged: loading...");
                            break;
                        case SUCCESS:
                            Log.d(TAG, "onChanged: got the posts");
                            adapter.setPosts(listResource.data);
                            break;
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(10);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }
}




