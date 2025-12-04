package ton_core.endpoints;

import retrofit2.Call;
import retrofit2.http.GET;
import ton_core.models.BaseResponse;

public interface SampleEndpoint {
    @GET("/api/sample/endpoint")
    Call<BaseResponse<String>> getSampleData();
}
