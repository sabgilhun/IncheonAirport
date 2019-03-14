package ado.sabgil.incheonariport.custumview;

import android.content.Context;
import android.util.AttributeSet;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.widget.SearchView;

public class MySearchView extends SearchView {
    private OnSearchViewChangedListener onSearchViewChangedListener;
    private OnDebouncedTextChangeListener onDebouncedTextListener;

    public MySearchView(Context context) {
        super(context);
    }

    public MySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnSearchViewChangedListener(OnSearchViewChangedListener listener) {
        onSearchViewChangedListener = listener;
    }


    public void setOnDebouncedQueryTextListener(OnDebouncedTextChangeListener listener) {
        onDebouncedTextListener = listener;
    }

    public void addDebounceOnListener() {
        setOnQueryTextListener(new OnQueryTextListener() {
            Timer timer;
            int interval = 200;
            int minLength = 2;

            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (timer != null) {
                    timer.cancel();
                }

                if (newText.length() >= minLength) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (onDebouncedTextListener != null) {
                                onDebouncedTextListener.onTextChange(newText);
                            }
                        }
                    }, interval);
                }
                return true;
            }
        });
    }

    @Override
    public void onActionViewCollapsed() {
        super.onActionViewCollapsed();
        if (onSearchViewChangedListener != null) {
            onSearchViewChangedListener.onSearchViewChanged(false);
        }
    }

    @Override
    public void onActionViewExpanded() {
        super.onActionViewExpanded();
        if (onSearchViewChangedListener != null) {
            onSearchViewChangedListener.onSearchViewChanged(true);
        }
    }

    public interface OnSearchViewChangedListener {
        void onSearchViewChanged(boolean isExpand);
    }

    public interface OnDebouncedTextChangeListener {
        void onTextChange(String query);
    }
}
