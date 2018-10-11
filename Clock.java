//11.10.18 Tobias Sigel
//Clock class to test Performance

public class Clock {

    long start;
    long end;
    long duration;

    public Clock() {
        start = System.currentTimeMillis();
    }

    public void stop(){
        end = System.currentTimeMillis();
    }

    public void start(){
        start = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        duration = end - start;
        return "duration in ms: " +  Long.toString(duration);
    }

}
