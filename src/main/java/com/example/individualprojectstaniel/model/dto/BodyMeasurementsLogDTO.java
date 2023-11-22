    package com.example.individualprojectstaniel.model.dto;



    import java.time.LocalDate;

    public class BodyMeasurementsLogDTO {
        private Long id;
        private Long userId;
        private LocalDate date;

        private String bodyPart;
        private int size;
        public BodyMeasurementsLogDTO() {
        }

        public BodyMeasurementsLogDTO(Long id, LocalDate date, String bodyPart, int size) {
            this.id = id;
            this.date = date;
            this.bodyPart = bodyPart;
            this.size = size;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getBodyPart() {
            return bodyPart;
        }

        public void setBodyPart(String bodyPart) {
            this.bodyPart = bodyPart;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
