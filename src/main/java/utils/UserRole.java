package utils;

/**
 *
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public enum UserRole {
    MANAGER("manager", "Quản lý", 2),
    STAFF("staff", "Nhân viên", 1),
    INACTIVE("inactive", "Nghỉ việc", 0);
    private String id, name;
    private int priority;

    UserRole(String id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    public static UserRole getById(String id) {
        for (UserRole e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return STAFF;
    }

    public static UserRole getByName(String name) {
        for (UserRole e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return STAFF;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int compare(UserRole other) {
        return priority - other.priority;
    }

}
