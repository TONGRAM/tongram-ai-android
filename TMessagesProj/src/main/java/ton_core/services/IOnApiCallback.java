package ton_core.services;

public interface IOnApiCallback<T> {
    void onSuccess(T data);
    void onError(String errorMessage);
}
