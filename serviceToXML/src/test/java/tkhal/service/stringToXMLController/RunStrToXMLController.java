package tkhal.service.stringToXMLController;

public class RunStrToXMLController extends Thread{

    @Override
    public void run() {
        String[] arg = new String[]{"test"};
        StrToXMLController.main(arg);
    }
}
