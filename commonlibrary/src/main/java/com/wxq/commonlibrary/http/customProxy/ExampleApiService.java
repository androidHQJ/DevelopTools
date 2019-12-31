package com.wxq.commonlibrary.http.customProxy;

import com.wxq.commonlibrary.model.ResponseData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 如果请求参数是每个值分开传的才需要这一步（ 例如这里的 likePost 接口）；
 对于请求参数是一个bean类或者Map，不需要这一步（ 例如这里的 savePost 接口）。
 */
public interface ExampleApiService {
    /**
     * 广场发帖
     */
    @FormUrlEncoded
    @POST("square/post/save")
    Call<ResponseData<Object>> savePost(@Body EditPostRequest editPostRequest);

    /**
     * 帖子点赞
     */
    @FormUrlEncoded
    @POST("square/post/like")
    Call<ResponseData<Object>> likePost(@Field("likeType") int likeType, @Field("postId") long postId);

    /**
     * 帖子点赞
     * 带"from"参数的版本
     * 不要删除，动态代理会调用{@link  CustomProxy}
     */
    @FormUrlEncoded
    @POST("square/post/like")
    Call<ResponseData<Object>> likePost(@Field("likeType") int likeType, @Field("postId") long postId, @Field("from") String from);
}
