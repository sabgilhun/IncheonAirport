package ado.sabgil.incheonariport.data;

@FunctionalInterface
public interface OnResponseListener<T> {
    void onResponse(T result);
}
