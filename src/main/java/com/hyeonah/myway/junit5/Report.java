package com.hyeonah.myway.junit5;

public class Report {

    private final ReportStatus status = ReportStatus.DRAFT;

    private final int limit;
    private String name;

    public Report(final int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit은 0 보다 커야 합니다.");
        }
        this.limit = limit;
    }

    public Report(final int limit, final String name) {
        this.limit = limit;
        this.name = name;
    }

    public ReportStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "Report{" +
            "status=" + status +
            ", limit=" + limit +
            ", name='" + name + '\'' +
            '}';
    }
}
