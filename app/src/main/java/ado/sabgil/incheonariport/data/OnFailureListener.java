package ado.sabgil.incheonariport.data;

@FunctionalInterface
public interface OnFailureListener {
    void onFailure(Exception error);
}
