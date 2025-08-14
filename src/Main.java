import java.util.*;

public class Main {
    public static void main(String[] args) {
        //2
        TelephoneDirectory directory = new TelephoneDirectory();

        directory.add("Ivanov", "1234567890");
        directory.add("Smirnov", "1234567899");
        directory.add("Smirnov", "1234567888");

        System.out.println("Ivanov: " + directory.get("Ivanov"));
        System.out.println("Smirnov: " + directory.get("Smirnov"));
        System.out.println("Sklodowska : " + directory.get("Sklodowska"));

        //1
        Map<String, Integer> grades1 = new HashMap<>();
        grades1.put("Math", 3);
        grades1.put("Physics", 3);
        grades1.put("Programming", 3);

        Map<String, Integer> grades2 = new HashMap<>();
        grades2.put("Math", 2);
        grades2.put("Physics", 2);
        grades2.put("Programming", 4);

        List<Student> students = new ArrayList<>();
        students.add(new Student("Ivan", "1", 2, grades1));
        students.add(new Student("Boris", "2", 2, grades2));
        students.add(new Student("Egor", "3", 1, grades1));

        removeStudents(students);

        promoteStudents(students);

        Set<Student> studentSet = new HashSet<>(students);
        printStudents(studentSet, 2);
    }

    public static void removeStudents(List<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageGrade() < 3) {
                iterator.remove();
                System.out.println("студент " + student.getName() + " удален");
            }
        }
    }

    public static void promoteStudents(List<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3) {
                student.setCourse(student.getCourse() + 1);
                System.out.println(student.getName() + " переведен на " + student.getCourse());
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        System.out.println("студенты " + course + " курса:");
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println("- " + student.getName());
            }
        }
    }


}
