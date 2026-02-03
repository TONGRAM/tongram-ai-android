package ton_core.services.chat_service;

import org.telegram.messenger.BuildConfig;

import ton_core.TonApiClient;
import ton_core.endpoints.ChatEndpoint;
import ton_core.models.requests.WritingAssistantRequest;
import ton_core.models.responses.FixGrammarResponse;
import ton_core.models.responses.WritingAssistantResponse;
import ton_core.services.BaseService;
import ton_core.services.IOnApiCallback;

public class ChatService extends BaseService implements IChatService {

    ChatEndpoint chatEndpoint = TonApiClient.getInstance().create(ChatEndpoint.class);
    @Override
    public void writeAssistant(WritingAssistantRequest request, IOnApiCallback<WritingAssistantResponse> onResult) {
        apiCallExecutor.execute(() -> handleWithoutBaseResponse(chatEndpoint.writeAssistant(BuildConfig.TON_API_KEY, request), onResult));
    }

    @Override
    public void fixGrammar(WritingAssistantRequest request, IOnApiCallback<FixGrammarResponse> onResult) {
        apiCallExecutor.execute(() -> handleWithoutBaseResponse(chatEndpoint.fixGrammar(BuildConfig.TON_API_KEY, request), onResult));
    }
}
