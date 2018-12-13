package br.edu.ufcg.ccc.pharma.exceptions;

public class ResourceNotFoundDetails extends ExceptionDetails {

    private ResourceNotFoundDetails(){}

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

        public Builder message(String message) {
            this.devMessage = message;
            return this;
        }

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setTimestamp(this.timestamp);
            resourceNotFoundDetails.setDevMessage(this.devMessage);
            resourceNotFoundDetails.setDetails(this.details);
            resourceNotFoundDetails.setTitle(this.title);
            resourceNotFoundDetails.setStatus(this.status);

            return resourceNotFoundDetails;
        }
    }
}
