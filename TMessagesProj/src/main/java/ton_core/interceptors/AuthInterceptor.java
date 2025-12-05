package ton_core.interceptors;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ton_core.TonTokenManager;

public class AuthInterceptor implements Interceptor {

    private final TonTokenManager tokenManager;

    public AuthInterceptor(TonTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();

        String token = tokenManager.getToken();
        if (token == null || token.isEmpty()) {
            return chain.proceed(original);
        }

        Request newRequest = original.newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();

        return chain.proceed(newRequest);
    }
}
