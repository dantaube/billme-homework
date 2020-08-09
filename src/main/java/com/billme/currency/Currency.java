package com.billme.currency;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.StringJoiner;

@Immutable
@Entity
@Table(name = "CURRENCY")
public class Currency {

    @Id
    @Column(name = "CODE", unique = true, nullable = false, length = 3)
    private String code;

    @Column(name = "NUMBER", unique = true, nullable = false)
    private int number;

    @Column(name = "SCALE", nullable = false)
    private int scale;

    @Column(name = "CODE", nullable = false)
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Currency.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("number=" + number)
                .add("scale=" + scale)
                .add("name='" + name + "'")
                .toString();
    }
}