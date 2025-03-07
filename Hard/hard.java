import java.util.concurrent.locks.*;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private int availableSeats = TOTAL_SEATS;
    private final Lock lock = new ReentrantLock();

    public void bookTicket(String customerName) {
        lock.lock();
        try {
            if (availableSeats > 0) {
                System.out.println(customerName + " successfully booked a seat. Remaining seats: " + (--availableSeats));
            } else {
                System.out.println(customerName + " tried to book a seat but none were available.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final String customerName;

    public BookingThread(TicketBookingSystem system, String customerName, int priority) {
        this.system = system;
        this.customerName = customerName;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookTicket(customerName);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();
        
        Thread vip1 = new BookingThread(bookingSystem, "VIP-1", Thread.MAX_PRIORITY);
        Thread vip2 = new BookingThread(bookingSystem, "VIP-2", Thread.MAX_PRIORITY);
        Thread user1 = new BookingThread(bookingSystem, "User-1", Thread.NORM_PRIORITY);
        Thread user2 = new BookingThread(bookingSystem, "User-2", Thread.NORM_PRIORITY);
        Thread user3 = new BookingThread(bookingSystem, "User-3", Thread.NORM_PRIORITY);
        Thread user4 = new BookingThread(bookingSystem, "User-4", Thread.NORM_PRIORITY);
        Thread user5 = new BookingThread(bookingSystem, "User-5", Thread.NORM_PRIORITY);
        
        vip1.start();
        vip2.start();
        user1.start();
        user2.start();
        user3.start();
        user4.start();
        user5.start();
    }
}
