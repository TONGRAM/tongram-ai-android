package ton_core.endpoints;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import ton_core.models.requests.WritingAssistantRequest;
import ton_core.models.responses.FixGrammarResponse;
import ton_core.models.responses.WritingAssistantResponse;

public interface ChatEndpoint {
    @POST("chat/write-assistant")
    Call<WritingAssistantResponse> writeAssistant(@Header("X-API-Key") String apiKey, @Body WritingAssistantRequest request);

    @POST("chat/fix-grammar")
    Call<FixGrammarResponse> fixGrammar(@Header("X-API-Key") String apiKey, @Body WritingAssistantRequest request);
}
