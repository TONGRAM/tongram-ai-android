package ton_core.repositories.translated_message_repository.chat_repository;

import ton_core.models.requests.WritingAssistantRequest;
import ton_core.models.responses.FixGrammarResponse;
import ton_core.models.responses.WritingAssistantResponse;
import ton_core.services.IOnApiCallback;

public interface IChatRepository {
    void writeAssistant(WritingAssistantRequest request, IOnApiCallback<WritingAssistantResponse> onResult);
    void fixGrammar(WritingAssistantRequest request, IOnApiCallback<FixGrammarResponse> onResult);
}
