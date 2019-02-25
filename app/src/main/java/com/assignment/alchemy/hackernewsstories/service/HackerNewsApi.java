package com.assignment.alchemy.hackernewsstories.service;

import com.assignment.alchemy.hackernewsstories.model.Comment;
import com.assignment.alchemy.hackernewsstories.model.Story;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HackerNewsApi {
    @GET("v0/topstories.json?print=pretty")
    Call<List<Integer>> getTopStories();

    @GET("v0/item/{storyid}.json?print=pretty")
    Call<Story> getStory(@Path("storyid") int id);

    @GET("v0/item/{commentid}.json?print=pretty")
    Call<Comment> getComment(@Path("commentid") int id);
}
