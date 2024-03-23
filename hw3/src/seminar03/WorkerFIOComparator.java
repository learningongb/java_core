package seminar03;

import java.util.Comparator;

public class WorkerFIOComparator implements Comparator<Worker> {


    @Override
    public int compare(Worker o1, Worker o2) {
        int compareResult = o1.getSurname().compareTo(o2.getSurname());
        if (compareResult != 0)
            return compareResult;
        compareResult = o1.getName().compareTo(o2.getName());
        if (compareResult != 0)
            return compareResult;
        return o1.getMiddleName().compareTo(o2.getMiddleName());
    }
}
