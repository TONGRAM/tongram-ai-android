package ton_core.models;

public class BaseResponse<T> {
    public boolean success;
    public String message;
    public T data;
}
