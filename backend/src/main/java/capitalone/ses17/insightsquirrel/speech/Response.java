package capitalone.ses17.insightsquirrel.speech;

public class Response {
    private final long id;
    private final String content;

    public Response(long id, String content){
        this.id = id;
        this.content = content;
    }

    public long getId(){
        return this.id;
    }

    public String getContent(){
        return this.content;
    }
}
