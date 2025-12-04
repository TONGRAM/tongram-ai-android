package ton_core.services.sample_service;

import ton_core.services.IOnApiCallback;

public interface ISampleService {
    void sampleApi(IOnApiCallback<String> onResult);
}
