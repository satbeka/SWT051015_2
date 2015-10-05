package model;


public class Upragnenie {
    private String name;
    private String short_name;
    private Sbor group;

    public VidPodgotovki getVidPodgotovki() {
        return vidPodgotovki;
    }

    public void setVidPodgotovki(VidPodgotovki vidPodgotovki) {
        this.vidPodgotovki = vidPodgotovki;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public Sbor getGroup() {
        return group;
    }

    public void setGroup(Sbor group) {
        this.group = group;
    }

    private VidPodgotovki vidPodgotovki;
}
