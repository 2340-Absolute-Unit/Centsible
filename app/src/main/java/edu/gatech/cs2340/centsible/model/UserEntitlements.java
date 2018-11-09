package edu.gatech.cs2340.centsible.model;

public enum UserEntitlements {
    GUEST(), USER(), ADMIN();

    UserEntitlements() {
        boolean readData = true;
        boolean writeShelter = false;
        boolean addUser = false;
        boolean deleteUser = false;
        boolean addAdmin = false;
    }

// --Commented out by Inspection START (11/8/18, 8:26 PM):
//    public boolean canReadData() {
//        return readData;
//    }
// --Commented out by Inspection STOP (11/8/18, 8:26 PM)

// --Commented out by Inspection START (11/8/18, 8:26 PM):
//    public boolean canWriteShelter() {
//        return writeShelter;
//    }
// --Commented out by Inspection STOP (11/8/18, 8:26 PM)

// --Commented out by Inspection START (11/8/18, 8:26 PM):
//    public boolean canAddUser() {
//        return addUser;
//    }
// --Commented out by Inspection STOP (11/8/18, 8:26 PM)

// --Commented out by Inspection START (11/8/18, 8:26 PM):
//    public boolean canDeleteUser() {
//        return deleteUser;
//    }
// --Commented out by Inspection STOP (11/8/18, 8:26 PM)

// --Commented out by Inspection START (11/8/18, 8:26 PM):
//    public boolean canAddAdmin() {
//        return addAdmin;
//    }
// --Commented out by Inspection STOP (11/8/18, 8:26 PM)

}
