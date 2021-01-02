package com.hyeonah.myway.junit5;

public class Report {

    private final ReportStatus status = ReportStatus.DRAFT;

    private final int limit;

    public Report(final int limit) {
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
