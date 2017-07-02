package br.com.rftemer.githubviewer.search.controler;

import br.com.rftemer.githubviewer.search.network.SearchInterface;
import br.com.rftemer.githubviewer.search.view.SearchView;
import br.com.rftemer.githubviewer.base.BaseNetwork;
import br.com.rftemer.githubviewer.model.GitHubItem;
import br.com.rftemer.githubviewer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {
    private SearchView mView;

    public SearchPresenter(SearchView view) {
        this.mView = view;
    }

    public void requestProjects(String userName) {
        final SearchInterface service = BaseNetwork.createService(SearchInterface.class);
        final Call<GitHubItem[]> call = service.getProjects(userName);

        call.enqueue(new Callback<GitHubItem[]>() {
            @Override
            public void onResponse(Call<GitHubItem[]> call, Response<GitHubItem[]> response) {
                if (response.isSuccessful()) {
                    final GitHubItem[] projects = response.body();
                    mView.onSearchSuccess(projects);
                } else {
                    mView.showMessage(R.string.user_not_found);
                }
            }

            @Override
            public void onFailure(Call<GitHubItem[]> call, Throwable t) {
                mView.showMessage(R.string.error);
            }
        });
    }

}
