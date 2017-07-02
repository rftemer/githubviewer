package br.com.rftemer.githubviewer.search.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import br.com.rftemer.githubviewer.model.GitHubItem;

public interface SearchInterface {
    @GET("{userName}/repos")
    Call<GitHubItem[]> getProjects(@Path("userName") String userName);
}
