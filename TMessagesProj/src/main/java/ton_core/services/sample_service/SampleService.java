package ton_core.services.sample_service;

import android.content.Context;

import ton_core.TonApiClient;
import ton_core.endpoints.SampleEndpoint;
import ton_core.services.BaseService;
import ton_core.services.IOnApiCallback;

public class SampleService extends BaseService implements ISampleService {

    public SampleService(Context context) {
        this.context = context;
    }

    SampleEndpoint sampleEndpoint = TonApiClient.getInstance(context).create(SampleEndpoint.class);

    @Override
    public void sampleApi(IOnApiCallback<String> onResult) {
        handle(sampleEndpoint.getSampleData(), onResult);
    }
}
