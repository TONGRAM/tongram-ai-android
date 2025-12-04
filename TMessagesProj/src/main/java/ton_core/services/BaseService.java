package ton_core.services;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ton_core.models.BaseResponse;

public class BaseService {

    protected Context context;

    protected static <T> void handle(Call<BaseResponse<T>> call, IOnApiCallback<T> callback) {
        call.enqueue(new Callback<BaseResponse<T>>() {

            @Override
            public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
                if (!response.isSuccessful()) {
                    callback.onError("HTTP " + response.code());
                    return;
                }

                BaseResponse<T> body = response.body();
                if (body == null) {
                    callback.onError("Empty response body");
                    return;
                }

                if (body.success) {
                    callback.onSuccess(body.data);
                } else {
                    callback.onError(body.message != null ? body.message : "Unknown error");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
