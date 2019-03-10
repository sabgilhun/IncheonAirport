package ado.sabgil.incheonariport.remote;

@FunctionalInterface
public interface OnResponseListener<T> {
    void onResponse(T result);
}
