package entity;


public class ResponseInfo<T> {

    private T result;

    private long time;

    private String waiter;

    private Integer queryCount;

    private String readFrom;

    public ResponseInfo(T result, long time, String readFrom, String waiter, Integer queryCount){
        this.result = result;
        this.time = time;
        this.readFrom = readFrom;
        this.waiter = waiter;
        this.queryCount = queryCount;
    }

    public ResponseInfo(T result, long time, String readFrom){
        this(result,time,readFrom,null,null);
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

    public String getWaiter() {
        return waiter;
    }

    public Integer getQueryCount() {
        return queryCount;
    }
}
