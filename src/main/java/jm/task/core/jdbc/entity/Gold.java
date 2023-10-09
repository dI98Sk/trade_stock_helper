package jm.task.core.jdbc.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gold")
public class Gold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ticket")
    private String ticket;

    @Column(name = "per")
    private String per;

    @Column(name = "date_trade")
    private Date date;

    @Column(name = "hour_trade")
    private Integer hour;

    @Column(name = "open_trade")
    private Float open;

    @Column(name = "high")
    private Float high;

    @Column(name = "low")
    private Float low;

    @Column(name = "close_trade")
    private Float close;

    @Column(name = "vol")
    private Integer vol;

    public Gold(String ticket,
                String per, Date date,
                Integer hour, Float open,
                Float high, Float low,
                Float close, Integer vol) {
        this.ticket = ticket;
        this.per = per;
        this.date = date;
        this.hour = hour;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.vol = vol;
    }

    public Gold() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Float getOpen() {
        return open;
    }

    public void setOpen(Float open) {
        this.open = open;
    }

    public Float getHigh() {
        return high;
    }

    public void setHigh(Float high) {
        this.high = high;
    }

    public Float getLow() {
        return low;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Float getClose() {
        return close;
    }

    public void setClose(Float close) {
        this.close = close;
    }

    public Integer getVol() {
        return vol;
    }

    public void setVol(Integer vol) {
        this.vol = vol;
    }

    @Override
    public String toString() {
        return "Gold{" +
                "id=" + id +
                ", ticket='" + ticket + '\'' +
                ", per='" + per + '\'' +
                ", date=" + date +
                ", hour=" + hour +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", vol=" + vol +
                '}';
    }
}
