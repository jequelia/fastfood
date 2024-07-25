package com.challenge.fastfood.entities;

public class Payment {
    private Long idLunch;
    private String status;

    public Payment(Long idLunch) {
        this.idLunch = idLunch;
        this.status = "PENDING";
    }

    public Long getIdLunch() {
        return idLunch;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.status = paymentStatus;
    }

    public String getStatus() {
        return status;
    }
}
