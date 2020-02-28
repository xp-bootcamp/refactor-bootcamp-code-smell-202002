@Getter
public class Activity {

    private final ActivityType type;
    private final int price;

    public Activity(ActivityType type, int price) {
        this.type = type;
        this.price = price;
    }

    public enum ActivityType {WORKSHOP, TDD, SESSION}
}

public class Ticket {

    private final Activity activity;

    public Ticket(Activity activity) {
        this.activity = activity;
    }

    public boolean isSession() {
        return Activity.ActivityType.SESSION.equals(activity.getType()) && isWorkday();
    }

    private boolean isWorkday() {
        return !activity.getDate().getDayOfWeek().equals(DayOfWeek.SATURDAY)
                && !activity.getDate().getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    public int getPrice() {
        return DayOfWeek.FRIDAY.equals(activity.getDate().getDayOfWeek())
                ? activity.getPrice() * 2
                : activity.getPrice();
    }

    public int refund() {
        return getPrice();
    }
}


public class VIPTicket extends Ticket {

    private final boolean supportExtensionalActivities;

    public VIPTicket(Activity activity, boolean supportExtensionalActivities) {
        super(activity);
        this.supportExtensionalActivities = supportExtensionalActivities;
    }

    public boolean isSession() {
        return Activity.ActivityType.SESSION.equals(activity.getType());
    }

    public int getPrice() {
        return super.getPrice() + 100;
    }

    public boolean hasExtensionalActivities() {
        return Activity.ActivityType.TDD.equals(activity.getType()) || supportExtensionalActivities;
    }
}


