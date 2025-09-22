package common.presentation.components;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sistema_ventas.R;

public class SearchBarView extends LinearLayout {

    private EditText edtQuery;
    private ImageView ivSearch;

    public interface OnQueryChanged { void onChanged(String text); }

    public SearchBarView(Context c) { this(c, null); }
    public SearchBarView(Context c, AttributeSet a) { this(c, a, 0); }
    public SearchBarView(Context c, AttributeSet a, int defStyleAttr) {
        super(c, a, defStyleAttr);
        LayoutInflater.from(c).inflate(R.layout.view_search_bar, this, true);
        setOrientation(HORIZONTAL);

        ivSearch = findViewById(R.id.ivSearch);
        edtQuery = findViewById(R.id.edtQuery);
    }

    public void setHint(CharSequence hint) { edtQuery.setHint(hint); }
    public String getText() { return edtQuery.getText().toString(); }
    public void setText(String text) { edtQuery.setText(text); }

    public void setOnQueryChanged(OnQueryChanged listener) {
        edtQuery.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (listener != null) listener.onChanged(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }
}