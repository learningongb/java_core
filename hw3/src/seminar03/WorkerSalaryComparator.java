package seminar03;

import java.util.Comparator;
public class WorkerSalaryComparator implements Comparator<Worker> {
    @Override
    public int compare(Worker o1, Worker o2) {
        return o1.getSalary() - o2.getSalary();
    }
}