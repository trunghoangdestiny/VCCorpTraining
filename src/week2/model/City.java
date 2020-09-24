package week2.model;

public class City {
    private int id;
    private String name;
    private int population;
    private String codeCountry;

    public City(int id, String name, int population, String codeCountry) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.codeCountry = codeCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    @Override
    public String toString() { //thuoc ve lop Object
        return "City{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", population = " + population +
                ", codeCountry = '" + codeCountry + '\'' +
                "}\n";
    }
}
