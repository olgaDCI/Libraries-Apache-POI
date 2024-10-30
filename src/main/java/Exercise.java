import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Exercise {

  private static final int CHAR_WIDTH = 256;
  private static final List<String> headerNames = List.of("Student Name","Programming grades", "Programming grades AVG",
          "Systems Design grades", "Systems Design grades AVG", "Networking grades", "Networking grades AVG");
  private static final NumberFormat FORMATTER = new DecimalFormat("#0.0");

  public static void main(String[] args) throws IOException {
    List<Student> studentGrades = new ArrayList<Student>();


    Student bob = new Student("Bob");
    studentGrades.add(bob.setProgrammingGrades(Arrays.asList(3.0, 1.5, 2.0)));
    studentGrades.add(bob.setSystemsDesignGrades(Arrays.asList(2.0, 2.5, 2.0)));
    studentGrades.add(bob.setNetworksGrades(Arrays.asList(2.0, 3.5, 4.0)));

    Student alice = new Student("Alice");
    studentGrades.add(alice.setProgrammingGrades(Arrays.asList(1.0, 1.0, 1.5)));
    studentGrades.add(alice.setSystemsDesignGrades(Arrays.asList(2.5, 1.5, 2.0)));
    studentGrades.add(alice.setNetworksGrades(Arrays.asList(4.0, 3.5, 5.0)));

    Student sam = new Student("Sam");
    studentGrades.add(sam.setProgrammingGrades(Arrays.asList(3.0, 3.5, 3.0)));
    studentGrades.add(sam.setSystemsDesignGrades(Arrays.asList(4.0, 3.5, 4.0)));
    studentGrades.add(sam.setNetworksGrades(Arrays.asList(2.0, 1.5, 3.0)));


    // Creating a Workbook
    XSSFWorkbook workbook = createWorkbook();
    addStudentToWorkbook(workbook, bob);
    addStudentToWorkbook(workbook, alice);
    addStudentToWorkbook(workbook, sam);
    saveWorkbook(workbook);
  }

  private static XSSFWorkbook createWorkbook() {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet gradesSheet = workbook.createSheet("Student Grades");
    XSSFRow header = gradesSheet.createRow(0);

    XSSFFont rowFont = workbook.createFont();
    rowFont.setBold(true);
    XSSFCellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setFont(rowFont);
    header.setRowStyle(headerStyle);

    int index = 0;
    for(String headerName: headerNames)
    {
      header.createCell(index).setCellValue(headerName);
      gradesSheet.setColumnWidth(index, headerName.length() * CHAR_WIDTH);
      index++;
    }
    return workbook;
  }


  private static void addStudentToWorkbook(XSSFWorkbook workbook, Student student) {
    XSSFSheet gradesSheet = workbook.getSheet("Student Grades");
    XSSFRow row = gradesSheet.createRow(gradesSheet.getLastRowNum() + 1);

    int cellIndex = 0;
    row.createCell(cellIndex++).setCellValue(student.getName());
    addGradeCells(row, cellIndex, student);
  }

  private static void saveWorkbook(XSSFWorkbook workbook) throws IOException {
    FileOutputStream fo = new FileOutputStream("StudentGrades.xlsx");
    workbook.write(fo);
  }


  private static void addGradeCells(XSSFRow row, int cellIndex, Student student) {
    cellIndex = writeGrades(row, cellIndex, student.getProgrammingGrades(), student.getAverageProgrammingGrade());
    cellIndex = writeGrades(row, cellIndex, student.getSystemsDesignGrades(), student.getAverageSystemsDesignGrade());
    writeGrades(row, cellIndex, student.getNetworksGrades(), student.getAverageNetworkingGrade());
  }

  private static int writeGrades(XSSFRow row, int cellIndex, List<Double> grades, double averageGrade) {
    row.createCell(cellIndex++).setCellValue(grades.toString());
    row.createCell(cellIndex++).setCellValue(FORMATTER.format(averageGrade));
    return cellIndex;
  }
}
