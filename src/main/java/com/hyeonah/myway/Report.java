package com.hyeonah.myway;

public class Report {

    private ReportStatus status = ReportStatus.DRAFT;

    private int limit;

    public Report(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit은 0 보다 커야 합니다.");
        }
        this.limit = limit;
    }

    public ReportStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }
}
