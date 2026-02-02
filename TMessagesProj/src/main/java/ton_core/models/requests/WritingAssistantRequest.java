package ton_core.models.requests;

public class WritingAssistantRequest {
    public String text;
    public String tone;
    public String draft;


    public WritingAssistantRequest(String text, String tone) {
        this.text = text;
        this.tone = tone;
    }

    public WritingAssistantRequest(String draft) {
        this.draft = draft;
    }
}