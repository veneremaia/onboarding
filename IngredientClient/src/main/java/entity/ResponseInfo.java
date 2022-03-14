package entity;


public class ResponseInfo<T> {

    private T result;

    private long time;

    private String readFrom;

    public ResponseInfo(T result, long time, String readFrom){
        this.result = result;
        this.time = time;
        this.readFrom = readFrom;
    }

    public String getReadFrom() {
        return readFrom;
    }

    public T getResult() {
        return result;
    }

    public long getTime() {
        return time;
    }
}
