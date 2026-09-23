package com.placement.admin.dto.dashboard;

public class DashboardResponseDTO {

    private long totalStudents;
    private long totalCompanies;
    private long totalPlacementDrives;
    private long totalApplications;
    private long placedStudents;
    private long unplacedStudents;
    private long selectedApplications;
    private long rejectedApplications;
    private long shortlistedApplications;
    private long upcomingDrives;
    private long openDrives;

    public DashboardResponseDTO() {
    }

    public DashboardResponseDTO(long totalStudents, long totalCompanies, long totalPlacementDrives,
                                long totalApplications, long placedStudents, long unplacedStudents,
                                long selectedApplications, long rejectedApplications,
                                long shortlistedApplications, long upcomingDrives, long openDrives) {
        this.totalStudents = totalStudents;
        this.totalCompanies = totalCompanies;
        this.totalPlacementDrives = totalPlacementDrives;
        this.totalApplications = totalApplications;
        this.placedStudents = placedStudents;
        this.unplacedStudents = unplacedStudents;
        this.selectedApplications = selectedApplications;
        this.rejectedApplications = rejectedApplications;
        this.shortlistedApplications = shortlistedApplications;
        this.upcomingDrives = upcomingDrives;
        this.openDrives = openDrives;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getTotalCompanies() {
        return totalCompanies;
    }

    public void setTotalCompanies(long totalCompanies) {
        this.totalCompanies = totalCompanies;
    }

    public long getTotalPlacementDrives() {
        return totalPlacementDrives;
    }

    public void setTotalPlacementDrives(long totalPlacementDrives) {
        this.totalPlacementDrives = totalPlacementDrives;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public long getPlacedStudents() {
        return placedStudents;
    }

    public void setPlacedStudents(long placedStudents) {
        this.placedStudents = placedStudents;
    }

    public long getUnplacedStudents() {
        return unplacedStudents;
    }

    public void setUnplacedStudents(long unplacedStudents) {
        this.unplacedStudents = unplacedStudents;
    }

    public long getSelectedApplications() {
        return selectedApplications;
    }

    public void setSelectedApplications(long selectedApplications) {
        this.selectedApplications = selectedApplications;
    }

    public long getRejectedApplications() {
        return rejectedApplications;
    }

    public void setRejectedApplications(long rejectedApplications) {
        this.rejectedApplications = rejectedApplications;
    }

    public long getShortlistedApplications() {
        return shortlistedApplications;
    }

    public void setShortlistedApplications(long shortlistedApplications) {
        this.shortlistedApplications = shortlistedApplications;
    }

    public long getUpcomingDrives() {
        return upcomingDrives;
    }

    public void setUpcomingDrives(long upcomingDrives) {
        this.upcomingDrives = upcomingDrives;
    }

    public long getOpenDrives() {
        return openDrives;
    }

    public void setOpenDrives(long openDrives) {
        this.openDrives = openDrives;
    }
}
