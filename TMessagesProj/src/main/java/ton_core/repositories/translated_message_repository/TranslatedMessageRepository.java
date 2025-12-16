package ton_core.repositories.translated_message_repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import ton_core.daos.TranslatedMessageDao;
import ton_core.database.TongramDatabase;
import ton_core.entities.TranslatedMessageEntity;
import ton_core.models.TranslateResponse;
import ton_core.services.IOnApiCallback;
import ton_core.services.sample_service.ITranslateService;
import ton_core.services.sample_service.TranslateService;

public class TranslatedMessageRepository implements ITranslatedMessageRepository {
    private static TranslatedMessageRepository INSTANCE;
    private final TranslatedMessageDao dao;
    private final ITranslateService translateService;

    private TranslatedMessageRepository(TranslatedMessageDao dao) {
        this.dao = dao;
        this.translateService = new TranslateService();
    }

    public static synchronized TranslatedMessageRepository getInstance(Context context) {
        if (INSTANCE == null) {
            TongramDatabase database = TongramDatabase.getDatabase(context);
            INSTANCE = new TranslatedMessageRepository(database.translatedMessageDao());
        }
        return INSTANCE;
    }

    @Override
    public LiveData<TranslatedMessageEntity> getTranslatedMessage(int messageId) {
        return dao.getTranslatedMessage(messageId);
    }

    @Override
    public void insert(TranslatedMessageEntity translatedMessage) {
        TongramDatabase.databaseWriteExecutor.execute(() -> dao.insert(translatedMessage));
    }

    @Override
    public void updateTranslatedState(int messageId, boolean isShow) {
        TongramDatabase.databaseWriteExecutor.execute(() -> dao.updateTranslatedState(messageId, isShow));
    }

    @Override
    public LiveData<List<TranslatedMessageEntity>> getTranslatedMessages(long accountId) {
        return dao.getChatMessages(accountId);
    }

    @Override
    public void translate(String text, String lang, int messageId, long chatId, int accountId) {
        translateService.translate(text, lang, new IOnApiCallback<TranslateResponse>() {
            @Override
            public void onSuccess(TranslateResponse data) {
                final TranslatedMessageEntity entity = new TranslatedMessageEntity(messageId, accountId, chatId, data.translation, lang, true);
                insert(entity);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
