package ton_core.services.chat_service;

import ton_core.models.requests.WritingAssistantRequest;
import ton_core.models.responses.FixGrammarResponse;
import ton_core.models.responses.WritingAssistantResponse;
import ton_core.services.IOnApiCallback;

public interface IChatService {
    void writeAssistant(WritingAssistantRequest request, IOnApiCallback<WritingAssistantResponse> onResult);
    void fixGrammar(WritingAssistantRequest request, IOnApiCallback<FixGrammarResponse> onResult);

}
