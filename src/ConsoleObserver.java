public class ConsoleObserver implements Iobserver {

    @Override
    public void update(String msg) {
        System.out.println(msg);
    }
}
