package com.marcinmoskala.elpassion

import com.marcinmoskala.elpassion.model.SearchRepoResult
import com.marcinmoskala.elpassion.model.SearchUserResult
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import com.squareup.okhttp.logging.HttpLoggingInterceptor.Level.BODY
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable

class Rest {

  val api = api()

  private fun api(): Api {
      //Turn on logging. TODO: Delete before production
      val logging = HttpLoggingInterceptor();
      logging.setLevel(BODY);

      val httpClient = OkHttpClient();
      httpClient.interceptors().add(logging);

      val retrofit = Retrofit.Builder()
              .baseUrl("https://api.github.com")
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
              .client(httpClient)
              .build()
      return retrofit.create(Api::class.java)
  }

  public interface Api {
      @GET("/search/users")
      fun users(@Query("q") q: String): Observable<SearchUserResult>

      @GET("/search/repositories")
      fun repositories(@Query("q") q: String): Observable<SearchRepoResult>
  }
}

