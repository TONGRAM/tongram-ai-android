package ton_core.models.requests;

import java.util.List;

public class SummaryRequest {
    public String focus;
    public List<String> messages;

    public SummaryRequest(List<String> message) {
        this.focus = "decisions";
        this.messages = message;
    }
}
