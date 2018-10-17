//11.10.18 Tobias Sigel
//Clock class to test Performance

public class Clock {

    long start;
    long end;
    long duration;
    String msg ="unknown source";

    public Clock() {
        start = System.currentTimeMillis();
    }

    public void stop(){
        end = System.currentTimeMillis();
    }

    public void stop(String log_msg) {{
        end = System.currentTimeMillis();
        this.msg = log_msg;
    }}

    public void start(){
        start = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        duration = end - start;
        return msg + " | duration in ms: " +  Long.toString(duration);
    }

}
