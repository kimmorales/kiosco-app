package com.example.kioscoapp.Model;

import java.util.List;

public class CreatedAppoitment {
    private boolean created;
    private List<Ticket> data;

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public List<Ticket> getData() {
        return data;
    }

    public void setData(List<Ticket> data) {
        this.data = data;
    }

    public  class Ticket {
        private String Ticket;

        public String getTicket() {
            return Ticket;
        }

        public void setTicket(String ticket) {
            Ticket = ticket;
        }
    }
}
