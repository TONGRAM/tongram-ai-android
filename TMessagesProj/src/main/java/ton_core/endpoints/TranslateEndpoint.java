package ton_core.endpoints;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ton_core.models.TranslateRequest;
import ton_core.models.TranslateResponse;

public interface TranslateEndpoint {
    @POST("/translate")
    Call<TranslateResponse> translate(@Body TranslateRequest request);
}
