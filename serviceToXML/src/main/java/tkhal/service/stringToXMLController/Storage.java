package tkhal.service.stringToXMLController;

import java.util.LinkedList;

public class Storage {
    private static LinkedList<Customer> list = new LinkedList<>();

    public static LinkedList<Customer> getList() {
        return list;
    }

    public static void setList(Customer customer) { list.add(customer);
    }

    public static void clear(Customer customer) {
        list.remove(customer);
    }
}
