package ton_core.services.sample_service;

import ton_core.TonApiClient;
import ton_core.endpoints.TranslateEndpoint;
import ton_core.models.TranslateRequest;
import ton_core.models.TranslateResponse;
import ton_core.services.BaseService;
import ton_core.services.IOnApiCallback;

public class TranslateService extends BaseService implements ITranslateService {

    TranslateEndpoint translateEndpoint = TonApiClient.getInstance().create(TranslateEndpoint.class);

    @Override
    public void translate(String text, String lang, IOnApiCallback<TranslateResponse> onResult) {
        apiCallExecutor.execute(() -> handleWithoutBaseResponse(translateEndpoint.translate(new TranslateRequest(text, lang)), onResult));
    }
}
