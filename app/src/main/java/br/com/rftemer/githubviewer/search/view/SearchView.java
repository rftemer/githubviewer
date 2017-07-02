package br.com.rftemer.githubviewer.search.view;

import br.com.rftemer.githubviewer.model.GitHubItem;


public interface SearchView {
    void onSearchSuccess(GitHubItem[] items);
    void showMessage(int messageResourceId);
}
