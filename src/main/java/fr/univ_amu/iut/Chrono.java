package fr.univ_amu.iut;

public class Chrono {
    private long startTime=0;
    private long actualTime = 0;
    private long duree=0;

    private String timeString = "";

    private boolean run = true;

    public void launch() {
        startTime=System.currentTimeMillis();
        duree=0;

        Thread time = new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    try {
                        timeString = getDurationTxt(getElapsedTime());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        time.start();
    }

    public long getElapsedTime() {
        actualTime=System.currentTimeMillis();
        duree = actualTime - startTime;
        return duree;
    }

    public long getDurationInSeconds() {
        return duree/1000;
    }

    public void stop() {
        run = false;
    }

    public String getDurationTxt(long duree) {
        return timeFormat(getDurationInSeconds());
    }

    public String getTimeString() {
        return timeString;
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
