package ton_core.repositories;

import android.content.Context;

import ton_core.repositories.sample_repository.ISampleRepository;
import ton_core.services.IOnApiCallback;
import ton_core.services.sample_service.ISampleService;
import ton_core.services.sample_service.SampleService;

public class SampleRepository implements ISampleRepository {
    private static SampleRepository INSTANCE;
    private final ISampleService sampleService;

    private SampleRepository(Context context) {
        sampleService = new SampleService(context);
    }

    public static synchronized SampleRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SampleRepository(context);
        }
        return INSTANCE;
    }

    @Override
    public void sampleApi(IOnApiCallback<String> onResult) {
        sampleService.sampleApi(onResult);
    }
}
