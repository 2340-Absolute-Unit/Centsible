package edu.gatech.cs2340.centsible.model;

public enum UserEntitlements {
    GUEST(true, false, false, false, false),
    USER(true, false, false, false, false),
    ADMIN(true, true, true, true, true);

    private boolean readData;
    private boolean writeShelter;
    private boolean addUser;
    private boolean deleteUser;
    private boolean addAdmin;

    UserEntitlements(boolean readData, boolean writeShelter, boolean addUser,
                     boolean deleteUser, boolean addAdmin) {
        this.readData = readData;
        this.writeShelter = writeShelter;
        this.addUser = addUser;
        this.deleteUser = deleteUser;
        this.addAdmin = addAdmin;
    }

    public boolean canReadData() {
        return readData;
    }

    public boolean canWriteShelter() {
        return writeShelter;
    }

    public boolean canAddUser() {
        return addUser;
    }

    public boolean canDeleteUser() {
        return deleteUser;
    }

    public boolean canAddAdmin() {
        return addAdmin;
    }

}
