package com.company;

import java.util.Objects;

/**
 * Report model.
 */
public class Report {
    private final boolean isValid;
    private final int errCnt;
    private static final int ERROR_COUNT_TO_BE_VALID = 1;

    public Report(int errCnt) {
        this.errCnt = errCnt;
        this.isValid = errCnt <= ERROR_COUNT_TO_BE_VALID;
    }

    public boolean isValid() {
        return isValid;
    }

    public int getErrCnt() {
        return errCnt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return isValid == report.isValid &&
                errCnt == report.errCnt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isValid, errCnt);
    }
}
