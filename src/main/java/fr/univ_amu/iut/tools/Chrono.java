package fr.univ_amu.iut.tools;


public class Chrono {
    private long startTime;
    private long actualTime;
    private long duration;
    private boolean run = true;

    public Chrono(long startTime) {
        this.startTime = startTime;
    }

    public Chrono() {
        this.startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

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

    public long getElapsedTime() {
        actualTime=System.currentTimeMillis();
        duration = actualTime - startTime;
        return duration;
    }

    public long getDurationInSeconds() {
        return duration/1000;
    }

    public void stop() {
        run = false;
    }

    public boolean isRun() {
        return run;
    }

    public String getDurationTxt(long duree) {
        return timeFormat(getDurationInSeconds());
    }

    public String getTimeString() {
        return getDurationTxt(getDurationInSeconds());
    }

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
