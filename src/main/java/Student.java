import java.util.List;

public class Student {

  private String name;
  private List<Double> programmingGrades;
  private List<Double> systemsDesignGrades;
  private List<Double> networksGrades;

  public Student(String name) {
    this.name = name;
  }

  public Student(String name, List<Double> programmingGrades,
      List<Double> systemsDesignGrades, List<Double> networksGrades) {
    this.name = name;
    this.programmingGrades = programmingGrades;
    this.systemsDesignGrades = systemsDesignGrades;
    this.networksGrades = networksGrades;
  }

  private double getAverageGrade(List<Double> grades) {
    double sumOfGrades = 0.0;
    for (double grade : grades) {
      sumOfGrades += grade;
    }

    return sumOfGrades / grades.size();
  }

  public String getName() {
    return name;
  }

  public Student setName(String name) {
    this.name = name;
    return this;
  }

  public List<Double> getProgrammingGrades() {
    return programmingGrades;
  }

  public double getAverageProgrammingGrade() {
    return getAverageGrade(this.programmingGrades);
  }

  public Student setProgrammingGrades(List<Double> programmingGrades) {
    this.programmingGrades = programmingGrades;
    return this;
  }

  public List<Double> getSystemsDesignGrades() {
    return systemsDesignGrades;
  }

  public double getAverageSystemsDesignGrade() {
    return getAverageGrade(this.systemsDesignGrades);
  }

  public Student setSystemsDesignGrades(List<Double> systemsDesignGrades) {
    this.systemsDesignGrades = systemsDesignGrades;
    return this;
  }

  public List<Double> getNetworksGrades() {
    return networksGrades;
  }

  public double getAverageNetworkingGrade() {
    return getAverageGrade(this.networksGrades);
  }

  public Student setNetworksGrades(List<Double> networksGrades) {
    this.networksGrades = networksGrades;
    return this;
  }
}
