package Project_7;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//The DataBase class would house all the User inputs from the Controller and store them in a List
//This is also where the Filter and Search feature happens since it searches inside the database already.

public class DataBase {
    public List<Magazine> getMagazines() {
        return magazines;
    }
    public List<Streaming> getStreamings() {
        return streamings;
    }
    public List<Freemium> getFreemiums() {
        return freemiums;
    }

    private List<Magazine> magazines;
    private List<Streaming> streamings;
    private List<Freemium> freemiums;

    public DataBase() {
        magazines = new LinkedList<>();
        streamings = new LinkedList<>();
        freemiums = new LinkedList<>();
    }

    public void add_magazine(Magazine magazine) {
        magazines.add(magazine);
    }

    public void addStreaming(Streaming streaming) {
        streamings.add(streaming);
    }

    public void addFreemium(Freemium freemium) {
        freemiums.add(freemium);
    }

    //Update Methods
    public void updateMagazine(Magazine magazine, int index) {
        if (index >= 0 && index < magazines.size()) {
            magazines.set(index, magazine);
        }
    }

    public void updateStreaming(Streaming streaming, int index) {
        if (index >= 0 && index < streamings.size()) {
            streamings.set(index, streaming);
        }
    }

    public void updateFreemium(Freemium freemium, int index) {
        if (index >= 0 && index < freemiums.size()) {
            freemiums.set(index, freemium);
        }
    }
//Remove Methods
    public void removeMagazine(Magazine magazine) {
        magazines.remove(magazine);
    }

    public void removeStreaming(Streaming streaming) {
        streamings.remove(streaming);
    }

    public void removeFreemium(Freemium freemium) {
        freemiums.remove(freemium);
    }

    //Search Methods
    public Magazine searchMagazine(String name) {
        return magazines.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Streaming searchStreams(String name) {
        return streamings.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Freemium searchFreemium(String name) {
        return freemiums.stream()
                .filter(f -> f.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // Filter methods
    public List<Magazine> filterMagazines(String status) {
        return magazines.stream()
                .filter(m -> m.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public List<Freemium> filterFreemiums(String status) {
        return freemiums.stream()
                .filter(f -> f.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public List<Streaming> filterStreams(String status) {
        return streamings.stream()
                .filter(s -> s.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

}