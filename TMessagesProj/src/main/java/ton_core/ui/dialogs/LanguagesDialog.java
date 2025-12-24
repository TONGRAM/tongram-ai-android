package ton_core.ui.dialogs;

import android.app.Dialog;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

import java.util.List;

import ton_core.ui.adapters.TongramLanguageAdapter;
import ton_core.ui.models.TongramLanguageModel;

public class LanguagesDialog extends BottomSheetDialogFragment implements TongramLanguageAdapter.ITongramLanguageListener {

    private EditText edtSearch;
    private final List<TongramLanguageModel> tongramLanguages;

    private final Delegate delegate;

    public interface Delegate {
        void onLanguageSelected(TongramLanguageModel language);
    }

    public LanguagesDialog(Delegate delegate, List<TongramLanguageModel> tongramLanguages) {
        this.delegate = delegate;
        this.tongramLanguages = tongramLanguages;
    }

    public synchronized static LanguagesDialog newInstance(Delegate delegate, List<TongramLanguageModel> tongramLanguages) {
        return new LanguagesDialog(delegate, tongramLanguages);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.languages_bottom_sheet_dialog, container, false);

        Drawable background = view.findViewById(R.id.cl_root).getBackground();

        if (background != null) {
            int themeColor = Theme.getColor(Theme.key_windowBackgroundWhite);
            background.setColorFilter(new PorterDuffColorFilter(themeColor, PorterDuff.Mode.SRC_IN));
        }

        TongramLanguageAdapter tongramLanguageAdapter = new TongramLanguageAdapter(tongramLanguages, this);

        RecyclerView rvTongramLanguages = view.findViewById(R.id.rv_languages);
        rvTongramLanguages.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvTongramLanguages.setAdapter(tongramLanguageAdapter);

        view.findViewById(R.id.iv_close).setOnClickListener(v -> dismiss());

        TextView title = view.findViewById(R.id.tv_title);
        title.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));

        edtSearch = view.findViewById(R.id.edt_search);
        edtSearch.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        edtSearch.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));

        return view;
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

    @Override
    public void onLanguageSelected(TongramLanguageModel language) {
        delegate.onLanguageSelected(language);
        dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            FrameLayout bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                behavior.setHideable(false);
            }
        }
    }
}
