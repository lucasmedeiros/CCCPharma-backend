package br.edu.ufcg.ccc.pharma.exceptions;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ExceptionDetails {

    private String title;
    private int status;
    private String details;
    private long timestamp;
    private String devMessage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public static final class Builder {
        private String title;
        private int status;
        private String details;
        private long timestamp;
        private String devMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder devMessage(String devMessage) {
            this.devMessage = devMessage;
            return this;
        }

        public ExceptionDetails build() {
            ExceptionDetails exceptionDetails = new ExceptionDetails();
            exceptionDetails.setTitle(title);
            exceptionDetails.setStatus(status);
            exceptionDetails.setDetails(details);
            exceptionDetails.setTimestamp(timestamp);
            exceptionDetails.setDevMessage(devMessage);
            return exceptionDetails;
        }
    }
}
