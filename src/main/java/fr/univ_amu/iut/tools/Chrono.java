package fr.univ_amu.iut.tools;


/**
 * Class containing the Chronometer tool
 */
public class Chrono {
    private long startTime;
    private long actualTime;
    private long duration;
    private boolean run = true;

    /**
     * Chronometer constructor used to restart the chronometer from a certain point
     *
     * @param startTime
     */
    public Chrono(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Normal Chronometer constructor
     */
    public Chrono() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Method used to get the moment when the chronometer started
     *
     * @return the moment when the chronometer started
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Method used to launch the Chronometer
     */
    public void launch() {
        Thread time = new Thread(() -> {
            while (run) {
                try {
                    duration = getElapsedTime();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        time.start();
    }

    /**
     * Method used to get the elapsed time since the beginning
     *
     * @return the elapsed time since the beginning
     */
    public long getElapsedTime() {
        actualTime=System.currentTimeMillis();
        duration = actualTime - startTime;
        return duration;
    }

    /**
     * Method used to transform the duration in seconds
     *
     * @return the duration in seconds
     */
    public long getDurationInSeconds() {
        return duration/1000;
    }

    /**
     * Method used to stop the Chronometer
     */
    public void stop() {
        run = false;
    }

    /**
     * Method used to know if the Chronometer is running
     *
     * @return true if the Chronometer is running, else if not
     */
    public boolean isRun() {
        return run;
    }


    /**
     * Method used to get the duration in a String format
     *
     * @param duree
     * @return the duration in a String format
     */
    public String getDurationTxt(long duree) {
        return timeFormat(getDurationInSeconds());
    }

    /**
     * Method used to get the duration in a String format
     *
     * @return the duration in a String format
     */
    public String getTimeString() {
        return getDurationTxt(getDurationInSeconds());
    }

    /**
     * Method used to return the time of the Chronometer in the format h:m:s
     *
     * @param timeS
     * @return the time of the Chronometer in the format h:m:s
     */
    public static String timeFormat(long timeS) {
        timeS += 1; //On ajoute 1 pour récuperer la seconde liée au sleep dans le Thread

        int h = (int) (timeS / 3600);
        int m = (int) ((timeS % 3600) / 60);
        int s = (int) (timeS % 60);

        String time="";

        if(h>0) {
            time+= h;
        } else {
            time+= "0";
        }
        if(m>=10) {
            time+=":" + m;
        } else if (m > 0 && m < 10) {
            time+=":0" + m;
        } else {
            time+=":00";
        }
        if(s>=10) {
            time+=":" + s;
        } else if (s > 0 && s < 10) {
            time+=":0" + s;
        } else {
            time+= ":00";
        }
        if(h<=0 && m<=0 && s<=0) {
            time="0:00:00";
        }
        return time;
    }
}
